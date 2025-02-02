package com.example.cinema_backend_part.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.cinema_backend_part.model.Ticket;
import com.example.cinema_backend_part.service.TicketService;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @GetMapping
    public ResponseEntity<List<Ticket>> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable Long id) {
        Ticket ticket = ticketService.getTicketById(id);
        return ResponseEntity.ok(ticket);
    }

    @PostMapping("/buy")
    public ResponseEntity<Ticket> buyTicket(@RequestParam Long sessionId, @RequestParam Long userId) {
        return ResponseEntity.ok(ticketService.buyTicket(sessionId, userId));
    }

    @PostMapping("/refund/{id}")
    public ResponseEntity<Ticket> refundTicket(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.refundTicket(id));
    }
}