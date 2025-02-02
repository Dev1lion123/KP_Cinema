package com.example.cinema_backend_part.service;

import com.example.cinema_backend_part.model.Session;
import com.example.cinema_backend_part.model.Ticket;
import com.example.cinema_backend_part.model.User;
import com.example.cinema_backend_part.repository.SessionRepository;
import com.example.cinema_backend_part.repository.TicketRepository;
import com.example.cinema_backend_part.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id).orElse(null);
    }

    public Ticket buyTicket(Long sessionId, Long userId) {
        Session session = sessionRepository.findById(sessionId).orElse(null);
        User user = userRepository.findById(userId).orElse(null);

        if (session == null || user == null) {
            throw new RuntimeException("Session or User not found");
        }

        Ticket ticket = new Ticket();
        ticket.setSession(session);
        ticket.setUser(user);
        ticket.setPurchaseTime(LocalDateTime.now());
        ticket.setStatus("active");

        return ticketRepository.save(ticket);
    }

    public Ticket refundTicket(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElse(null);
        if (ticket == null) {
            throw new RuntimeException("Ticket not found");
        }

        if (ticket.getSession().getScreeningTime().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Cannot refund ticket after session has started");
        }

        ticket.setStatus("refunded");
        return ticketRepository.save(ticket);
    }
}