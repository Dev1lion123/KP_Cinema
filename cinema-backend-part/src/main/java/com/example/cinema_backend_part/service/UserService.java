package com.example.cinema_backend_part.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.cinema_backend_part.model.SpringSecurityUser;
import com.example.cinema_backend_part.model.User;
import com.example.cinema_backend_part.repository.UserRepository;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Метод для регистрации нового пользователя
    public void registerUser(String username, String password, User.Role role) {
        // Шифруем пароль перед сохранением
        String encodedPassword = passwordEncoder.encode(password);
        
        // Создаем нового пользователя
        User user = new User(username, encodedPassword, role);

        // Сохраняем пользователя в базе данных
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.example.cinema_backend_part.model.User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
        return new SpringSecurityUser(user.getUsername(), user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name())));
    }
}
