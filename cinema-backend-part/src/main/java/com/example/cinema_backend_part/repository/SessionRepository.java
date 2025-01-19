package com.example.cinemabackend.repository;

import com.example.cinemabackend.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Session, Long> {
}
