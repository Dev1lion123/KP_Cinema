package com.example.cinemabackend.repository;

import com.example.cinemabackend.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Ticket, Long> {
}
