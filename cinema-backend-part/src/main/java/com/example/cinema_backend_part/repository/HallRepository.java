package com.example.cinemabackend.repository;

import com.example.cinemabackend.model.Hall;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Hall, Long> {
}
