package com.example.cinema_backend_part.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Разрешаем все эндпоинты
                        .allowedOrigins("http://localhost:3000") // Разрешаем фронт
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Разрешаем основные HTTP-методы
                        .allowedHeaders("*") // Разрешаем все заголовки
                        .allowCredentials(true); // Разрешаем передачу куков при использовании авторизации
            }
        };
    }
}
