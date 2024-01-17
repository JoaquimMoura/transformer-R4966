# Arquitetura OIDC com o BIGIP (F5)

**Fluxo de autenticação para os usuários internos (Authorization Code Flow)**

*Produção*:

*  Autenticação do empregado no AD
*  Pesquisa as ROLES do empregado no serviço bigip-roles-api
    * As ROLES são filtradas pelo Código da Aplicação e o CPF do usuário
    * O Código da Aplicação é cadastrado no Controle de Acesso

*Não Produção*:

*  Pesquisa as ROLES do empregado no serviço bigip-roles-api
    *  As ROLES são filtradas pelo Código da Aplicação e o CPF do usuário
    *  O Código da Aplicação é cadastrado no Controle de Acesso

*OBSERVAÇÕES*:

*  O starter-api e starter-frontend estão configurados para trabalhar com o F5
*  Fluxos customizados podem ser montados no F5 por aplicação, onde serviços devem ser construídos com autenticação BASIC para que o F5 consiga integrar
*  Criação do Portal de Aplicações Corporativas, onde todas as verticais de negócio irão publicar o link das aplicações internas


**Private claims do token para a autenticação de usuários internos (empregados) com Authorization Code Flow**

*  Nome (*)
*  Cpf (*)
*  Matrícula
*  Email
*  Ramal
*  UtaId
*  Uta
*  Roles: as roles serão coletadas da base do Controle de Acesso e serão filtrados pelo código da aplicação informado no cadastro da aplicação no Authorization Code Flow, resolvendo o problema do tamanho do token

> (*) são os campos que sempre virão no token, os demais podem não vir.
>
> Para acessar os `claims` do JWT, foi criado a classe `PexPrincipal`, que possui os métodos que retornam as informações padronizadas.

**Padrões que devem ser adotados para o uso adequado da autenticação e autorização**

*  A parte de cadastro de usuários (e todo o fluxo necessário) e alteração de senhas ficará na fronteira da aplicação
*  Cada aplicação deve ter o seu próprio CLIENT_ID e o nome deverá ser igual ao nome do projeto que irá utilizá-lo
*  Os tokens serão gerados no padrão JWT, sendo a implementação o JWE
*  Os escopos devem ser utilizados apenas para validar os acessos entre APIs através do Client Credentials Flow
*  Os escopos devem ficar disponíveis no swagger. Os escopos globais devem ficar na descrição da API e os escopos específicos por métodos devem ficar na descrição do próprio método.
*  O nome do escopo deve seguir o padrão: [NOME_CLIENT_ID]:[NOME_ESCOPO], exemplo: ldap-api:get, ldap-api:usario-bloqueado. O NOME_ESCOPO com nome composto, deve ser separado por hífen. Quando será necessário a criação de um ClientID:
    *  Quando uma API precisar comunicar com outra API. Nesse caso, somente a API que realiza a requisição precisará de um ClientID.
    *  Quando o frontend precisar comunicar com o backend. Nesse caso, somente o frontend precisará de um ClientID

# Migração KC para o BIGIP (F5) para proteger a sua API através de escopos:

As configurações realizadas abaixo foram realizadas com base na documentação do Spring Security 5 (https://docs.spring.io/spring-security/site/docs/5.3.4.RELEASE/reference/html5/#oauth2)

* Atualize o spring boot para versão `2.3.3.RELEASE` ou superior
* Atualize o `spring-cloud-dependencies` para a versão `Hoxton.SR7` ou superior
* Atualize o `poupex-starter-bom` para a versão `2.0.0` ou superior
* Remova a dependência `org.springframework.cloud:spring-cloud-starter-bus-amqp`, pois não está sendo utilizado o recurso de atualização automática de variáveis de ambiente sem reiniciar o POD
* Remover a configuração do Zipinkin (tracing de requisições), pois não existe equipe oficial suportando a ferramenta e as equipes não estão fazendo uso.
	* Remover a dependência `org.springframework.cloud:spring-cloud-starter-sleuth`
	* Remover a dependência `org.springframework.cloud:spring-cloud-starter-zipkin`
	* Caso não esteja utilizando o RabbitMQ, remova a dependência `org.springframework.amqp:spring-rabbit`
> Estamos sem uma ferramenta para identificar as requisições entre os microserviceses
* Remova as dependências do `keycloak` e `cors` do POM do projeto
* Adicione a dependência `org.springframework.boot:spring-boot-starter-oauth2-resource-server`
* No application-local.yml, remova todas as configurações do keycloak e adicione a configuração do Spring Security conforme o exemplo abaixo:

```
	spring:
		...
		security:
		    oauth2:
		      resourceserver:
		        jwt:
		          issuer-uri: https://[dev | teste | hml | prod]sso.poupex.com.br/f5-oauth2/v1
```

> Nos applications genéricos no cloud-config já foi colocado a configuração do ResourceServer adequado por ambiente, não sendo necessário a configuração

* Configure a classe de segurança que irá exigir a autenticação nos endpoints e controlar os acessos através dos escopos, conforme o exemplo abaixo (https://docs.spring.io/spring-security/site/docs/5.3.4.RELEASE/reference/html5/#oauth2resourceserver-jwt-authorization):

```
	@Configuration
	public class SecurityConfig extends WebSecurityConfigurerAdapter {
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                .headers().frameOptions().disable()
                .and().csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).anonymous()
                .antMatchers(HttpMethod.HEAD).anonymous()
                .antMatchers("/actuator/**", "/v2/api-docs/**", "/webjars/**", "/configuration/**", "/swagger-resources/**", "/swagger-ui.html").anonymous()
                .antMatchers(HttpMethod.GET).hasAuthority("SCOPE_starter-api:get")
                .antMatchers(HttpMethod.PATCH).hasAuthority("SCOPE_starter-api:patch")
                .anyRequest().authenticated()
                .and().oauth2ResourceServer().jwt();
	    }

        @Bean
        public CorsFilter corsFilter() {
            CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", config);
            return new CorsFilter(source);
        }
	}
```

# Migrar do KC para o BIGIP (F5) para o consumo de API para API:

As configurações realizadas abaixo foram realizadas com base na documentação do Spring Security 5 (https://docs.spring.io/spring-security/site/docs/5.3.4.RELEASE/reference/html5/#oauth2)

* Soliciente a criação do client_id do tipo `Client Credentials Flow` no GLPI para o time de segurança
* Atualize o spring boot para versão `2.3.3.RELEASE` ou superior
* Atualize o `spring-cloud-dependencies` para a versão `Hoxton.SR7` ou superior
* Atualize o `poupex-starter-bom` para a versão `2.0.0` ou superior
* Remova a dependência `org.springframework.cloud:spring-cloud-starter-bus-amqp`, pois não está sendo utilizado o recurso de atualização automática de variáveis de ambiente sem reiniciar o POD
* Remover a configuração do Zipinkin (tracing de requisições), pois não existe equipe oficial suportando a ferramenta e as equipes não estão fazendo uso.
	* Remover a dependência `org.springframework.cloud:spring-cloud-starter-sleuth`
	* Remover a dependência `org.springframework.cloud:spring-cloud-starter-zipkin`
	* Caso não esteja utilizando o RabbitMQ, remova a dependência `org.springframework.amqp:spring-rabbit`
> Estamos sem uma ferramenta para identificar as requisições entre os microserviceses
* Remova as dependências do `keycloak` e `cors` do POM e remova todas as configurações do Keycloak do application-local.yml (não esquecer do cloud-config)
* Com o client_id e o secret em mãos, configure a depedência `org.springframework.cloud:spring-cloud-starter-oauth2` no POM
* Configure a autenticação confome a propriedade `SECURITY` do exemplo feito no starter-api (https://gitlab.poupex.com.br/poupex/arquitetura/starters/spring-boot/starter-api/blob/master/src/main/resources/application-local.yml)
* Nesse passo a passo é utilizado o `FeignClient` para consumo de outra API, com isso configure o interceptador conforme o exemplo no starter-api (https://gitlab.poupex.com.br/poupex/arquitetura/starters/spring-boot/starter-api/blob/master/src/main/java/br/com/poupex/starters/api/util/feign/OAuth2FeignRequestInterceptor.java e https://gitlab.poupex.com.br/poupex/arquitetura/starters/spring-boot/starter-api/blob/master/src/main/java/br/com/poupex/starters/api/StarterApiApplication.java)
> Essa configuração foi retirada desse link https://developers.redhat.com/blog/2017/01/05/spring-boot-and-oauth2-with-keycloak/

# Permitir que sua API aceite tokens do KC e BIGIP (F5)

Esse tipo de configuração é chamado de _multi-tenant_ e pode ser usado no período de migração (aceitar tokens do KC e BIGIP).

Para detalhes da configuração acesse o link da documentação: https://docs.spring.io/spring-security/site/docs/5.3.4.RELEASE/reference/html5/#oauth2resourceserver-multitenancy

Você pode ver um exemplo funciona no *endereco-api*: https://gitlab.poupex.com.br/poupex/internos/endereco-api
