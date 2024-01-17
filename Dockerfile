FROM registry.poupex.com.br/app/eqlab/java:17

EXPOSE 8080

RUN mkdir "/opt/app"
COPY "target/*.jar" "/opt/app/app.jar"
ENTRYPOINT java $APM_OPTS \
    -Djava.security.egd=file:/dev/./urandom \
    -Duser.timezone=America/Sao_Paulo \
    -XX:+PrintCommandLineFlags \
    -Xss512k \
    -Dspring.output.ansi.enabled=always \
    -jar "/opt/app/app.jar"
