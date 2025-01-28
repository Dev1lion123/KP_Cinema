package com.example.cinema_backend_part.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cinema_backend_part.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);  
}
