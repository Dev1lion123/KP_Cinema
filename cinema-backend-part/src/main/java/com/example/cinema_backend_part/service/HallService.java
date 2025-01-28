package com.example.cinema_backend_part.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cinema_backend_part.model.Hall;
import com.example.cinema_backend_part.repository.HallRepository;

import java.util.List;
import java.util.Optional;

@Service
public class HallService {
    @Autowired
    private HallRepository hallRepository;

    public List<Hall> getAllHalls() {
        return hallRepository.findAll();
    }

    public Optional<Hall> getHallById(Long id) {
        return hallRepository.findById(id);
    }

    public Hall saveHall(Hall hall) {
        return hallRepository.save(hall);
    }

    public void deleteHall(Long id) {
        hallRepository.deleteById(id);
    }
}
