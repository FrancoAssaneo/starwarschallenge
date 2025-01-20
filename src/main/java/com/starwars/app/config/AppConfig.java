package com.starwars.app.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configuration class for the application.
 * This class provides the necessary configuration for beans used in the application.
 * It defines the {@link RestTemplate} bean to facilitate HTTP requests.
 */
@Configuration
public class AppConfig {

    /**
     * Creates and configures a {@link RestTemplate} bean.
     * The {@link RestTemplate} is used for making HTTP requests to external APIs.
     * This method utilizes the {@link RestTemplateBuilder} to build a default instance of {@link RestTemplate}.
     *
     * @param builder the {@link RestTemplateBuilder} used to configure and build the {@link RestTemplate} bean
     * @return a configured {@link RestTemplate} instance
     */
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}