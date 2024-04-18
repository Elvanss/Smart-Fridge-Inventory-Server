package com.smart.inventory.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Enable CORS for the whole application.
                .allowedMethods("*") // Allow all HTTP methods.
                .allowedOrigins("*") // Allow all origins.
                .allowCredentials(true) // Allow credentials.
                .maxAge(3600) // Set the maximum age of the CORS configuration.
                .exposedHeaders("Authorization") // Expose the Authorization header.
                .allowedHeaders("Authorization"); // Allow the Authorization header.
            }
        };
    }

}
