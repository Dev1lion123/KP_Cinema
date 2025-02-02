package com.example.cinema_backend_part.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter 
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String role;

    public void setRole(String role) {
        this.role = role;
    }
    
    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}

