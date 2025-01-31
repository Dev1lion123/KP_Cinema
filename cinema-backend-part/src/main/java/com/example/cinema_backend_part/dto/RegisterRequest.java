package com.example.cinema_backend_part.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class RegisterRequest {

    @NotBlank(message = "Поле логина обязательно")
    @Size(min = 3, max = 20, message = "Логин должен быть от 3 до 20 символов")
    private String username;
    
    @NotBlank(message = "Поле пароля обязательно")
    @Size(min = 6, message = "Пароль должен быть минимум 6 символов")
    private String password;
    
    @NotBlank(message = "Роль обязательна")
    private String role;
    
}
