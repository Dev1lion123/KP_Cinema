package com.example.cinema_backend_part.service;

import com.example.cinema_backend_part.model.Hall;
import com.example.cinema_backend_part.model.Movie;
import com.example.cinema_backend_part.model.Session;
import com.example.cinema_backend_part.repository.HallRepository;
import com.example.cinema_backend_part.repository.MovieRepository;
import com.example.cinema_backend_part.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private HallRepository hallRepository;

    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }

    public Session getSessionById(Long id) {
        return sessionRepository.findById(id).orElse(null);
    }

    public Session addSession(Session session) {
        Movie movie = movieRepository.findById(session.getMovie().getId()).orElse(null);
        Hall hall = hallRepository.findById(session.getHall().getId()).orElse(null);

        if (movie == null || hall == null) {
            throw new RuntimeException("Movie or Hall not found");
        }

        session.setMovie(movie);
        session.setHall(hall);
        return sessionRepository.save(session);
    }

    public void deleteSession(Long id) {
        sessionRepository.deleteById(id);
    }
}