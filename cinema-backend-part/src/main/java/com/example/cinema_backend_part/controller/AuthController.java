package com.example.cinema_backend_part.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.cinema_backend_part.model.User;
import com.example.cinema_backend_part.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    // Конструктор для инъекции зависимости UserService
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // Метод для регистрации пользователя
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestParam String username,
                                           @RequestParam String password,
                                           @RequestParam User.Role role) {
        // Вызов сервиса для регистрации пользователя
        userService.registerUser(username, password, role);
        // Возвращаем успешный ответ
        return ResponseEntity.ok("User registered successfully!");
    }
}
