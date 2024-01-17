package br.com.poupex.starters.api.commons.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class SwaggerConfiguration {

    private final StarterApiProperties properties;

    @Bean
    public OpenAPI openAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title(properties.getSwagger().getTitle())
                        .description(properties.getSwagger().getDescription())
                        .contact(properties.getSwagger().getContact())
                );
    }
}
