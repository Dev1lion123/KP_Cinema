package com.example.cinema_backend_part.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cinema_backend_part.model.Sessions;
import com.example.cinema_backend_part.repository.SessionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SessionService {
    @Autowired
    private SessionRepository sessionRepository;

    public List<Sessions> getAllSessions() {
        return sessionRepository.findAll();
    }

    public Optional<Sessions> getSessionById(Long id) {
        return sessionRepository.findById(id);
    }

    public Sessions saveSession(Sessions session) {
        return sessionRepository.save(session);
    }

    public void deleteSession(Long id) {
        sessionRepository.deleteById(id);
    }
}