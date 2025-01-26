package com.example.cinema_backend_part.controller;

import com.example.cinema_backend_part.model.User;
import com.example.cinema_backend_part.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestParam String username,
                                           @RequestParam String password,
                                           @RequestParam User.Role role) {
        userService.registerUser(username, password, role);
        return ResponseEntity.ok("User registered successfully!");
    }
}
