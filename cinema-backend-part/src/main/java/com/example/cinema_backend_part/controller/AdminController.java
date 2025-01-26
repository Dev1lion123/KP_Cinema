package com.example.cinema_backend_part.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    public String adminDashboard() {
        return "Добро пожаловать в панель администратора!";
    }
}
