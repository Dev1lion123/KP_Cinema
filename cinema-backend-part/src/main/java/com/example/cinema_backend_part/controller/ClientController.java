package com.example.cinema_backend_part.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientController {

    @GetMapping
    public String clientDashboard() {
        return "Добро пожаловать в наш кинотеатр!";
    }
}
