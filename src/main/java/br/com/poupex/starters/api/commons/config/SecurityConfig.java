package br.com.poupex.starters.api.commons.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

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
            .antMatchers("/actuator/**", 
            			 "/v3/api-docs/**", 
            			 "/swagger-ui/**", 
            			 "/javainuse-openapi/**",
                         "/swagger-ui.html", 
                         "/h2-console/**").permitAll()
            // Validação de escopos conforme a documentação de referência https://docs.spring.io/spring-security/site/docs/5.3.4.RELEASE/reference/html5/#oauth2resourceserver-jwt-authorization
            // A ordem de configuração desses métodos influencia na validação da segurança
            .antMatchers(HttpMethod.DELETE, "/**").permitAll()  //.hasAuthority("SCOPE_starter-api:delete")
            .antMatchers(HttpMethod.GET, "/**").permitAll()     //.hasAuthority("SCOPE_starter-api:get")
            .antMatchers(HttpMethod.PATCH).hasAuthority("SCOPE_starter-api:patch")
            .antMatchers(HttpMethod.POST, "/**").permitAll()    //.hasAuthority("SCOPE_starter-api:post")
            .antMatchers(HttpMethod.PUT, "/**").permitAll()     //.hasAuthority("SCOPE_starter-api:put")
            .anyRequest().authenticated()
            //            .anyRequest().permitAll()
            .and().oauth2ResourceServer().jwt();
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
        config.setAllowedMethods(java.util.List.of("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
