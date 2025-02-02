package com.example.cinema_backend_part.controller;

import com.example.cinema_backend_part.model.User;
import com.example.cinema_backend_part.dto.JwtResponse;
import com.example.cinema_backend_part.dto.LoginRequest;
import com.example.cinema_backend_part.dto.RegisterRequest;
import com.example.cinema_backend_part.service.AuthService;
import com.example.cinema_backend_part.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            // Аутентификация пользователя
            User user = authService.authenticateUser(loginRequest);
            // Генерация JWT-токена
            String token = jwtUtil.generateToken(user.getUsername());
            return ResponseEntity.ok(new JwtResponse(token));
        } catch (RuntimeException e) {
            // Если аутентификация не удалась, возвращаем 401 Unauthorized
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        User user = authService.registerUser(registerRequest);
        return ResponseEntity.ok("Пользователь успешно зарегистрирован");
    }
}
