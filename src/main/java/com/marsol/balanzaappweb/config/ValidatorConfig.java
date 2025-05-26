package com.marsol.balanzaappweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidatorConfig {
    @Bean
    public jakarta.validation.Validator validator() {
        return new LocalValidatorFactoryBean();
    }
}
