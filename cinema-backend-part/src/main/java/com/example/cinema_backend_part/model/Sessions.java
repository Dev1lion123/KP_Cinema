package com.example.cinemabackend.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false)
    private Film film;

    private String hall;
    private LocalDate sessionDate;
    private LocalTime sessionTime;
    private BigDecimal price;

    // Геттеры и сеттеры
}
