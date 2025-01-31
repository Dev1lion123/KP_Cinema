package com.example.cinema_backend_part.controller;

import com.example.cinema_backend_part.dto.RegisterRequest;
import com.example.cinema_backend_part.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*") // Разрешаем CORS
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Эндпоинт регистрации нового пользователя
     */
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequest request) {
        // Преобразуем `User.Role` в `String`
        userService.registerUser(request.getUsername(), request.getPassword(), request.getRole().toUpperCase());
        return ResponseEntity.ok("Пользователь зарегистрирован успешно");
    }
}
