import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function Login({ onLoginSuccess }) {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleSubmit = async (event) => {
        event.preventDefault();

        try {
            const response = await axios.post("http://localhost:8080/api/auth/login", {
                username,
                password
            })
            .then(response => {
                console.log('Успешный вход:', response.data);
                localStorage.setItem("token", response.data.token); // Сохраняем токен
                onLoginSuccess(); // Обновляем состояние приложения
                navigate('/films'); // Перенаправляем на страницу фильмов
            })
            .catch(error => {
                console.error('Ошибка входа:', error);
            });
            
            
        } catch (error) {
            console.error("Ошибка входа:", error.response?.data?.message || error.message);
            alert("Ошибка входа: " + (error.response?.data?.message || "Неверные данные"));
        }
    };

    return (
        <div>
            <h1>Вход</h1>
            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    placeholder="Логин"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    required
                />
                <input
                    type="password"
                    placeholder="Пароль"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    required
                />
                <button type="submit">Войти</button>
            </form>
        </div>
    );
}

export default Login;