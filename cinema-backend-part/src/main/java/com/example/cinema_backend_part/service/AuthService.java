package com.example.cinema_backend_part.service;

import com.example.cinema_backend_part.dto.LoginRequest;
import com.example.cinema_backend_part.dto.RegisterRequest;
import com.example.cinema_backend_part.model.User;
import com.example.cinema_backend_part.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User authenticateUser(LoginRequest loginRequest) {
        String username = loginRequest.getUsername().trim();
        String password = loginRequest.getPassword().trim();

        logger.info("Попытка авторизации пользователя: {}", username);

        // Поиск пользователя в базе данных
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    logger.warn("Пользователь не найден: {}", username);
                    return new RuntimeException("Пользователь не найден");
                });

        logger.info("Найден пользователь: {}", user.getUsername());
        logger.info("Хэш пароля из базы данных: {}", user.getPassword());

        // Проверка пароля
        if (passwordEncoder.matches(password, user.getPassword())) {
            logger.info("Пароль совпадает для пользователя: {}", username);
            return user;
        } else {
            logger.warn("Пароль не совпадает для пользователя: {}", username);
            throw new RuntimeException("Неверный пароль");
        }
    }

    public User registerUser(RegisterRequest registerRequest) {
        String username = registerRequest.getUsername().trim();
        String password = registerRequest.getPassword().trim();

        logger.info("Попытка регистрации пользователя: {}", username);

        // Проверка, существует ли пользователь с таким именем
        if (userRepository.existsByUsername(username)) {
            logger.warn("Пользователь с таким именем уже существует: {}", username);
            throw new RuntimeException("Пользователь с таким именем уже существует");
        }

        // Создание нового пользователя
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole("CLIENT"); // По умолчанию роль "CLIENT"

        // Сохранение пользователя в базу данных
        User savedUser = userRepository.save(user);
        logger.info("Пользователь успешно зарегистрирован: {}", savedUser.getUsername());

        return savedUser;
    }
}