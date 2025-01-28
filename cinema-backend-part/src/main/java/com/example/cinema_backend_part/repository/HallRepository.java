package com.example.cinema_backend_part.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cinema_backend_part.model.Hall;

@Repository
public interface HallRepository extends JpaRepository<Hall, Long> {
}
