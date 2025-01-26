package com.example.cinema_backend_part.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import org.springframework.security.core.userdetails.User;
import java.util.Date;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtTokenProvider {

    private final String secretKey = "mySecretKey";  // Секретный ключ для подписи JWT

    // Генерация JWT токена
    @SuppressWarnings("deprecation")
    public String generateToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("role", user.getAuthorities());

        Date now = new Date();
        Date validity = new Date(now.getTime() + 3600000); // 1 час

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact();
    }

    // Валидация JWT токена
    @SuppressWarnings("deprecation")
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build();

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Извлечение пользователя из токена
    @SuppressWarnings("unchecked")
    public User getUserFromToken(String token) {
        @SuppressWarnings("deprecation")
        Claims claims = (Claims) Jwts.parserBuilder()
        .setSigningKey(secretKey)
        .build();
    
        return new User(claims.getSubject(), "", claims.get("role", List.class));
    }

    // Извлечение токена из заголовка
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        return bearerToken != null && bearerToken.startsWith("Bearer ") ? bearerToken.substring(7) : null;
    }
}
