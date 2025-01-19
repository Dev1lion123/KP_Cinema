package com.example.cinemabackend.service;

import com.example.cinemabackend.model.Hall;
import com.example.cinemabackend.repository.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HallService {
    @Autowired
    private HallRepository hallRepository;

    public List<Hall> getAllHalls() {
        return hallRepository.findAll();
    }

    public Hall saveHall(Hall hall) {
        return hallRepository.save(hall);
    }
    
    
}
