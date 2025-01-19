package com.example.cinemabackend.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    private Session session;

    private String seatNumber;
    private LocalDateTime purchaseDate;

    // Геттеры и сеттеры
}
