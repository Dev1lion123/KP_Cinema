package com.example.cinema_backend_part;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.cinema_backend_part.model.User;
import com.example.cinema_backend_part.repository.UserRepository;

import java.util.List;

@SpringBootApplication
public class CinemaBackendPartApplication {

    public static void main(String[] args) {
        SpringApplication.run(CinemaBackendPartApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findAll().isEmpty()) {
                userRepository.saveAll(List.of(
                    User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin123"))
                        .role(User.Role.ADMIN)
                        .build(),
                    User.builder()
                        .username("cashier")
                        .password(passwordEncoder.encode("cashier123"))
                        .role(User.Role.CASHIER)
                        .build(),
                    User.builder()
                        .username("client")
                        .password(passwordEncoder.encode("client123"))
                        .role(User.Role.CLIENT)
                        .build()
                ));
            }
        };
    }

}

