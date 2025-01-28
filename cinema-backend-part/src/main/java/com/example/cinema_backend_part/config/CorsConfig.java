package com.example.cinema_backend_part.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                    .allowedOrigins("http://localhost:3000") // Разрешить запросы с фронтенда
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Методы, которые мы разрешаем
                    .allowedHeaders("*") // Разрешенные заголовки
                    .allowCredentials(true); // Разрешить cookie
            }
        };
    }
}
