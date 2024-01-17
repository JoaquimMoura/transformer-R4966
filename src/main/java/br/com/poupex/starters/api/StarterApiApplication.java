package br.com.poupex.starters.api;

import br.com.poupex.starters.api.commons.config.StarterApiProperties;
import br.com.poupex.starters.api.commons.mapper.CustomValueReader;
import org.hibernate.Hibernate;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@EnableOAuth2Client
@EnableFeignClients
@SpringBootApplication
@EnableConfigurationProperties(StarterApiProperties.class)
public class StarterApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(StarterApiApplication.class, args);
    }

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:locale/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.getConfiguration().addValueReader(new CustomValueReader());
        modelMapper.getConfiguration().setPropertyCondition(context -> Hibernate.isInitialized(context.getSource()));
        return modelMapper;
    }
}
