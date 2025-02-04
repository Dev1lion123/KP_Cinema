import React from 'react';
import { useNavigate } from 'react-router-dom';

function LogoutButton({ onLogout }) {
    const navigate = useNavigate();

    const handleLogoutClick = () => {
        onLogout();
        navigate('/login'); // Перенаправление на страницу входа
    };

    return (
        <button onClick={handleLogoutClick}>Выйти</button>
    );
}

export default LogoutButton;