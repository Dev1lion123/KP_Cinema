package com.example.cinema_backend_part.controller;

import com.example.cinema_backend_part.model.Ticket;
import com.example.cinema_backend_part.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable Long id) {
        return ticketService.getTicketById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Ticket createTicket(@RequestBody Ticket ticket) {
        return ticketService.saveTicket(ticket);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable Long id, @RequestBody Ticket updatedTicket) {
        return ticketService.getTicketById(id)
                .map(ticket -> {
                    ticket.setPrice(updatedTicket.getPrice());
                    ticket.setSeatNumber(updatedTicket.getSeatNumber());
                    ticket.setSession(updatedTicket.getSession());
                    Ticket savedTicket = ticketService.saveTicket(ticket);
                    return ResponseEntity.ok(savedTicket);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }
}
