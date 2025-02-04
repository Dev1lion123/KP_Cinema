package com.example.cinema_backend_part.controller;

import com.example.cinema_backend_part.model.User;
import com.example.cinema_backend_part.dto.JwtResponse;
import com.example.cinema_backend_part.dto.LoginRequest;
import com.example.cinema_backend_part.dto.RegisterRequest;
import com.example.cinema_backend_part.service.AuthService;
import com.example.cinema_backend_part.config.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.cinema_backend_part.config.InvalidPasswordException;
import com.example.cinema_backend_part.config.UserNotFoundException;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private AuthService authService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        logger.info("Получен запрос на вход для пользователя: {}", loginRequest.getUsername());

        try {
            User user = authService.authenticateUser(loginRequest);
            String token = jwtTokenProvider.generateToken(user.getUsername());
            logger.info("Пользователь успешно авторизован: {}", user.getUsername());
            return ResponseEntity.ok(new JwtResponse(token));
        } catch (UserNotFoundException e) {
            logger.warn("Ошибка входа: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InvalidPasswordException e) {
            logger.warn("Ошибка входа: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Неожиданная ошибка при входе: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла ошибка");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        logger.info("Получен запрос на регистрацию для пользователя: {}", registerRequest.getUsername());

        try {
            authService.registerUser(registerRequest);
            return ResponseEntity.ok("Пользователь успешно зарегистрирован");
        } catch (RuntimeException e) {
            logger.warn("Ошибка регистрации: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}