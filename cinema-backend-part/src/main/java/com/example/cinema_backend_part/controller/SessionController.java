package com.example.cinema_backend_part.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.cinema_backend_part.model.Session;
import com.example.cinema_backend_part.service.SessionService;

import java.util.List;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {
    @Autowired
    private SessionService sessionService;

    @GetMapping
    public ResponseEntity<List<Session>> getAllSessions() {
        return ResponseEntity.ok(sessionService.getAllSessions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Session> getSessionById(@PathVariable Long id) {
        Session session = sessionService.getSessionById(id);
        return ResponseEntity.ok(session);
    }

    @PostMapping
    public ResponseEntity<Session> addSession(@RequestBody Session session) {
        return ResponseEntity.ok(sessionService.addSession(session));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable Long id) {
        sessionService.deleteSession(id);
        return ResponseEntity.noContent().build();
    }
}