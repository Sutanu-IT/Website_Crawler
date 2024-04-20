package com.example.crawler.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    // Create a bean of type RestTemplate to be used for making HTTP requests
    @Bean
    public RestTemplate restTemplate() {
        // Return a new instance of RestTemplate
        return new RestTemplate();
    }
}
