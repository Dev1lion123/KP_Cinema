package com.example.cinema_backend_part.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc  // Включаем поддержку CORS в MVC
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Разрешаем CORS для всех путей
                        .allowedOrigins("http://localhost:3000") // Разрешаем фронтенд
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Разрешенные методы
                        .allowedHeaders("*") // Разрешаем все заголовки
                        .allowCredentials(true); // Разрешаем передачу cookies и токенов
            }
        };
    }
}
