package com.example.cinemabackend.controller;

import com.example.cinemabackend.model.Hall;
import com.example.cinemabackend.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/halls")
public class HallController {
    @Autowired
    private HallService hallService;

    @GetMapping
    public List<Hall> getAllHalls() {
        return ticketService.getAllHalls();
    }

    @PostMapping
    public Hall createHall(@RequestBody Hall hall) {
        return hallService.saveHall(hall);
    }
    
}
