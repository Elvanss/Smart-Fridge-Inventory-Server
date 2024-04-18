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
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000") // Allow 'http://localhost:3000' as an origin
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS") // Specify the allowed methods
                        .allowedHeaders("Authorization")
                        .allowCredentials(true); // Allow credentials
            }
        };
    }

}
