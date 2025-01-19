package com.example.cinema_backend_part.repository;

import com.example.cinema_backend_part.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
