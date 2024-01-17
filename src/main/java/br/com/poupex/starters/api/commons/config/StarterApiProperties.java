package br.com.poupex.starters.api.commons.config;

import io.swagger.v3.oas.models.info.Contact;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("starter-api")
public class StarterApiProperties {

    private String ldapApiRootPath;
    private Swagger swagger = new Swagger();

    @Getter
    @Setter
    public static class Swagger {
        private String title;
        private String description;
        private Contact contact = new Contact();
    }
}


