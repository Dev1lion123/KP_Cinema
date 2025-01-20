package com.cinema_backend_part.controller;

import com.cinema_backend_part.model.Sessions;
import com.cinema_backend_part.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sessions")
@CrossOrigin(origins = "http://localhost:3000")
public class SessionController {
    @Autowired
    private SessionService sessionService;

    @GetMapping
    public List<Sessions> getAllSessions() {
        return sessionService.getAllSessions();
    }

    @PostMapping
    public Sessions addSession(@RequestBody Sessions session) {
        return sessionService.saveSession(session);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sessions> updateSession(@PathVariable Long id, @RequestBody Sessions updatedSession) {
        return sessionService.getSessionById(id)
                .map(session -> {
                    session.setFilm(updatedSession.getFilm());
                    session.setHall(updatedSession.getHall());
                    session.setSessionDate(updatedSession.getSessionDate());
                    session.setSessionTime(updatedSession.getSessionTime());
                    Sessions savedSession = sessionService.saveSession(session);
                    return ResponseEntity.ok(savedSession);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable Long id) {
        sessionService.deleteSession(id);
        return ResponseEntity.noContent().build();
    }
}