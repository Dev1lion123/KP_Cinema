import React, { useState } from 'react';
import api from './api.js'; // Импортируем instance из api.js
import { useNavigate } from 'react-router-dom';

function Login({ onLoginSuccess }) {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleSubmit = async (event) => {
        event.preventDefault();
        setError('');
        if (!username || !password) {
            setError("Заполните все поля.");
            return;
        }
        try {
            const response = await api.post("/auth/login", { username, password });
            console.log('Успешный вход:', response.data);
            localStorage.setItem("token", response.data.token); // Сохраняем токен
            onLoginSuccess(); // Обновляем состояние приложения
            navigate('/films'); // Перенаправляем на страницу фильмов
        } catch (error) {
            console.error('Ошибка входа:', error);
            if (error.response) {
                setError(error.response.data.message || "Неверные данные");
            } else if (error.request) {
                setError("Сервер не отвечает. Проверьте подключение.");
            } else {
                setError("Произошла ошибка. Попробуйте позже.");
            }
        }
    };

    return (
        <div>
            <h1>Вход</h1>
            {error && <p style={{ color: 'red' }}>{error}</p>}
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