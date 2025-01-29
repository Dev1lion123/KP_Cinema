package com.example.cinema_backend_part.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // @Bean
    // public PasswordEncoder passwordEncoder() {
    //     return new BCryptPasswordEncoder();
    // }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(cors -> cors.configurationSource(request -> {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(List.of("http://localhost:3000"));
                config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                config.setAllowedHeaders(List.of("*"));
                config.setAllowCredentials(true);
                return config;
            }))
            .csrf(csrf -> csrf.disable()) // Отключаем CSRF для API
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Без сессий
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/**").permitAll() // Разрешаем доступ к API
                .anyRequest().authenticated()
            );
        
        return http.build();
    }
    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    //     http
    //         .csrf(csrf -> csrf.disable()) // Отключаем CSRF для простоты тестирования
    //         .authorizeHttpRequests(auth -> auth
    //             .requestMatchers("/api/admin/**").hasRole("ADMIN")
    //             .requestMatchers("/api/cashier/**").hasRole("CASHIER")
    //             .requestMatchers("/api/user/**", "/api/films", "/api/sessions").hasAnyRole("CLIENT", "CASHIER", "ADMIN")
    //             .requestMatchers("/api/auth/**").permitAll() // Разрешаем регистрацию и логин
    //             .anyRequest().authenticated()
    //         )
    //         .formLogin(login -> login
    //             .loginPage("/login").permitAll()
    //             .defaultSuccessUrl("/films", true)
    //         )
    //         .logout(logout -> logout
    //             .logoutUrl("/logout")
    //             .logoutSuccessUrl("/login")
    //             .permitAll()
    //         );

    //     return http.build();
    // }
}
