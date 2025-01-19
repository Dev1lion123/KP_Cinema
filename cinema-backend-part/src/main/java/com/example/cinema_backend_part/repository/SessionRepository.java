package com.example.cinema_backend_part.repository;

import com.example.cinema_backend_part.model.Sessions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Sessions, Long> {
}
