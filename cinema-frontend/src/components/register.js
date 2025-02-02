import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function Register() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await axios.post("http://localhost:8080/api/auth/register", 
            { 
                username, 
                password 
            });

            alert("Регистрация успешна! Теперь войдите в систему.");
            navigate('/login');
        } catch (err) {
            console.error("Ошибка регистрации:", err);
            setError(err.response?.data?.message || "Ошибка при регистрации");
        }
    };

    return (
        <div>
            <h1>Регистрация</h1>
            {error && <p style={{ color: "red" }}>{error}</p>}
            <form onSubmit={handleSubmit}>
                <div>
                    <label htmlFor="username">Логин:</label>
                    <input
                        type="text"
                        id="username"
                        placeholder="Введите логин"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label htmlFor="password">Пароль:</label>
                    <input
                        type="password"
                        id="password"
                        placeholder="Введите пароль"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </div>
                <button type="submit">Зарегистрироваться</button>
            </form>
        </div>
    );
}

export default Register;