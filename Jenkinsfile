pipeline {
    agent any

    environment {
        JAVA = '17'
        MAVEN = '3'
        // HELM_GL_PID é referente ao identificador do repositório no GITLAB para artefatos helmcharts. Para mais
        // informações veja https://gitlab.poupex.com.br/poupex/infra/k8s/starter/starter-api
        HELM_GL_PID = '761'
        BRANCH = env.BRANCH.replace('origin/', '')
    }

    stages {
        stage('Quality Gate') {
            steps {
                withSonarQubeEnv('default') {
                    withMaven(maven: MAVEN, jdk: JAVA) {
                        sh 'mvn sonar:sonar -Dsonar.scm.disabled=True'
                    }
                }
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: false
                }
            }
        }
		// Início das etapas do Veracode
		stage('[VERACODE] - Pipeline Scan') {
			when { anyOf { branch 'master'; branch 'main'; branch 'hml'; branch 'develop' } }
			agent { docker {
				image 'veracode/pipeline-scan:latest'
				reuseNode true
			} }
			steps {
				script { ARTIFACT_ID = readMavenPom().getArtifactId() }
				withCredentials([usernamePassword(credentialsId: 'veracode-credentials', passwordVariable: 'VERACODE_API_KEY_SECRET', usernameVariable: 'VERACODE_API_KEY_ID')]) {
					sh """
						java -jar /opt/veracode/pipeline-scan.jar  -vid "${VERACODE_API_KEY_ID}" -vkey "${VERACODE_API_KEY_SECRET}" \
						-f ${WORKSPACE}/target/${ARTIFACT_ID}-${VERSION}.jar -p "${JOB_BASE_NAME}" -u "${GIT_URL}" -r "${BRANCH}" \
						--fail_on_severity "${VERACODE_DEFAULT_FAIL_SEV}"
					"""
				}
			}
		}
		stage ('[VERACODE] - SCA') {
          when { anyOf { branch 'master'; branch 'main'; branch 'hml'; branch 'develop' } }
            steps {
                script { SCA_WS = evaluate("SRCCLR_WORKSPACE_SLUG_${VERACODE_TEAM}") }
                withCredentials([string(credentialsId: 'SRCCLR_API_TOKEN', variable: 'SRCCLR_API_TOKEN')]) {
					withMaven(maven: MAVEN, jdk: JAVA) {
						sh "mvn dependency:tree | curl -sSL https://download.sourceclear.com/ci.sh | bash -s scan --update-advisor --ws=${SCA_WS} --ref=${BRANCH} || true"
					}
                }
            }
        }
		stage('[VERACODE] - Policy Scan') {
			when { anyOf { branch 'master'; branch 'main'; branch 'hml'; branch 'develop' } }
			agent { docker { image 'veracode/api-wrapper-java:latest'
				reuseNode true
			} }
			steps {
				withCredentials([usernamePassword(credentialsId: 'veracode-credentials', passwordVariable: 'VERACODE_API_KEY_SECRET', usernameVariable: 'VERACODE_API_KEY_ID')]) {
					sh """
						java -jar /opt/veracode/api-wrapper.jar -action UploadAndScan -createprofile 'true' -deleteincompletescan "2" -vid "${VERACODE_API_KEY_ID}" -vkey "${VERACODE_API_KEY_SECRET}" -policy "${VERACODE_DEFAULT_POLICY}" \
						-teams "CODTI.${VERACODE_TEAM}" \
						-appname "${JOB_BASE_NAME}" \
						-version "${JOB_BASE_NAME}-${BRANCH}-${VERSION}-${BUILD_ID}" \
						-filepath "${WORKSPACE}/target/${ARTIFACT_ID}-${VERSION}.jar"
					"""
				}
			}
		}
		// Fim das etapas do VERACODE
        stage('Release') {
            when { environment name: 'BRANCH', value: 'master' }
            steps {
                withMaven(maven: MAVEN, jdk: JAVA) {
                    sh "mvn git-release:execute"
                }
            }
        }
        stage('Publish image') {
            steps {
                script {
                    VERSION = readMavenPom().getVersion()
                    IMAGE_TAG = "${VERSION}" + ((BRANCH != 'master' ? "-${BRANCH}" : ''))
                }
                withMaven(maven: MAVEN, jdk: JAVA) {
                    sh "mvn package dockerfile:build dockerfile:push -Ddockerfile.tag=${IMAGE_TAG}"
                }
            }
        }
        stage('Deploy') {
            steps {
                script {
                    ARTIFACT_ID = readMavenPom().getArtifactId()
                    KUBECONFIG = BRANCH == 'master' ? "${KUBECONFIG_PROD}" : "${KUBECONFIG_HML}"
                    HELM_RELEASE = "${ARTIFACT_ID}-${BRANCH}"
                    if (BRANCH == 'master') {
                        withCredentials([string(credentialsId: 'jenkins-gitlab-token', variable: 'JNKS_GL_TOKEN')]) {
                            sh "curl -s -H 'PRIVATE-TOKEN: ${JNKS_GL_TOKEN}' https://gitlab.poupex.com.br/api/v4/projects/${HELM_GL_PID}/repository/files/values-prod.yaml/raw?ref=master -o values.yaml"
                        }
                    }
                }
                timeout(time: 5, unit: 'MINUTES') {
                    sh """
						helm repo update --kubeconfig ${KUBECONFIG} && touch values.yaml \
						&& helm upgrade  --install --create-namespace --namespace=${HELM_RELEASE} ${HELM_RELEASE} \
						helmcharts/${ARTIFACT_ID} -f values.yaml --reset-values --kubeconfig ${KUBECONFIG} \
						--set imageTag=${IMAGE_TAG},branch=${BRANCH},springProfilesActive=${env.SPRING_PROFILES_ACTIVE} \
						--wait --timeout 180s
					"""
                }
            }
        }
    }

    post {
        success {
            cleanWs(skipWhenFailed: true)
        }
    }
}
