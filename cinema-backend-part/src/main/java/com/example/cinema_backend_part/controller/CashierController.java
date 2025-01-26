package com.example.cinema_backend_part.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cashier")
public class CashierController {

    @GetMapping
    public String cashierDashboard() {
        return "Добро пожаловать в панель кассира!";
    }
}
