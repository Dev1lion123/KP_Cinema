import React, { useState } from 'react';
import axios from 'axios';

function AuthForm({ onAuthSuccess }) {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [role, setRole] = useState('CLIENT');
    const [isRegister, setIsRegister] = useState(false);
    const [error, setError] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const endpoint = isRegister ? "/api/auth/register" : "/login";
            await axios.post(`http://localhost:8080${endpoint}`, { username, password, role });
            onAuthSuccess(username, role);
        } catch (err) {
            setError("Ошибка: " + err.response?.data || "Неизвестная ошибка");
        }
    };

    return (
        <div>
            <h2>{isRegister ? "Регистрация" : "Вход"}</h2>
            <form onSubmit={handleSubmit}>
                <input type="text" placeholder="Имя пользователя" value={username} onChange={e => setUsername(e.target.value)} required />
                <input type="password" placeholder="Пароль" value={password} onChange={e => setPassword(e.target.value)} required />
                {isRegister && (
                    <select value={role} onChange={e => setRole(e.target.value)}>
                        <option value="CLIENT">Клиент</option>
                        <option value="CASHIER">Кассир</option>
                        <option value="ADMIN">Администратор</option>
                    </select>
                )}
                <button type="submit">{isRegister ? "Зарегистрироваться" : "Войти"}</button>
                <button type="button" onClick={() => setIsRegister(!isRegister)}>
                    {isRegister ? "Уже есть аккаунт?" : "Создать новый аккаунт"}
                </button>
            </form>
            {error && <p style={{ color: 'red' }}>{error}</p>}
        </div>
    );
}

export default AuthForm;
