package com.example.cinema_backend_part.service;

import com.example.cinema_backend_part.dto.LoginRequest;
import com.example.cinema_backend_part.dto.RegisterRequest;
import com.example.cinema_backend_part.model.User;
import com.example.cinema_backend_part.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User authenticateUser(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
    
        System.out.println("Stored hash: " + user.getPassword());
        System.out.println("Plain password: " + loginRequest.getPassword());
    
        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return user;
        } else {
            throw new RuntimeException("Неверный пароль");
        }
    }
    
    

    public User registerUser(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole("CLIENT"); // По умолчанию роль "CLIENT"
        return userRepository.save(user);
    }
}