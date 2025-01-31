import React, { useState } from 'react';
import axios from 'axios';

function Login({ onLoginSuccess }) {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleSubmit = async (event) => {
        event.preventDefault();
        
        try {
            const response = await axios.post("http://localhost:8080/api/auth/login", {
                username,
                password
            });

            localStorage.setItem("token", response.data.token); // Сохраняем токен
            alert("Вы успешно вошли!");
            onLoginSuccess(); // Обновляем состояние приложения
        } catch (error) {
            alert("Ошибка входа: " + error.response?.data?.message || "Неверные данные");
        }
    };

    return (
        <div>
            <h1>Вход</h1>
            <form onSubmit={handleSubmit}>
                <input type="text" placeholder="Логин" value={username} onChange={(e) => setUsername(e.target.value)} />
                <input type="password" placeholder="Пароль" value={password} onChange={(e) => setPassword(e.target.value)} />
                <button type="submit">Войти</button>
            </form>
        </div>
    );
}

export default Login;
