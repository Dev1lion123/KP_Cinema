package com.example.cinema_backend_part.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.cinema_backend_part.model.Hall;
import com.example.cinema_backend_part.service.HallService;

import java.util.List;

@RestController
@RequestMapping("/api/halls")
public class HallController {
    @Autowired
    private HallService hallService;

    @GetMapping
    public ResponseEntity<List<Hall>> getAllHalls() {
        return ResponseEntity.ok(hallService.getAllHalls());
    }

    @PostMapping
    public ResponseEntity<Hall> addHall(@RequestBody Hall hall) {
        return ResponseEntity.ok(hallService.addHall(hall));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHall(@PathVariable Long id) {
        hallService.deleteHall(id);
        return ResponseEntity.noContent().build();
    }
}