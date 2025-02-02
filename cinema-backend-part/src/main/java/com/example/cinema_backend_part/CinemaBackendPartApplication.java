package com.example.cinema_backend_part;

import com.example.cinema_backend_part.model.User;
import com.example.cinema_backend_part.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class CinemaBackendPartApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(CinemaBackendPartApplication.class, args);
    }

    @Override
    public void run(String... args) {
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin123"))
                .role("ADMIN")
                .build();

            userRepository.save(admin);
        }
    }
}
