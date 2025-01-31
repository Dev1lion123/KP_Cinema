import React, { useState } from 'react';

function Register({ onRegisterSuccess }) {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [role, setRole] = useState('CLIENT');
    const [error, setError] = useState(''); // Для отображения ошибок

    const handleSubmit = async (e) => {
        e.preventDefault();
    
        try {
            const response = await fetch("http://localhost:8080/api/auth/register", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    username: username.trim(),
                    password: password.trim(),
                    role: role,
                }),
            });
    
            const contentType = response.headers.get("content-type");
    
            if (!response.ok) {
                const errorText = contentType && contentType.includes("application/json")
                    ? (await response.json()).message
                    : await response.text();
                throw new Error(errorText);
            }
    
            const data = contentType && contentType.includes("application/json")
                ? await response.json()
                : { message: await response.text() };
    
            alert(`Успех: ${data.message}`);
        } catch (err) {
            console.error("Ошибка регистрации:", err);
            alert(err.message);
        }
    };
    

    return (
        <div>
            <h1>Регистрация</h1>
            {error && <p style={{ color: "red" }}>{error}</p>} {/* Отображение ошибок */}
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
                <div>
                    <label htmlFor="role">Роль:</label>
                    <select
                        id="role"
                        value={role}
                        onChange={(e) => setRole(e.target.value)}
                        required
                    >
                        <option value="CLIENT">Клиент</option>
                        <option value="CASHIER">Кассир</option>
                        <option value="ADMIN">Администратор</option>
                    </select>
                </div>
                <button type="submit">Зарегистрироваться</button>
            </form>
        </div>
    );
}

export default Register;
