import React, { useEffect, useState } from 'react';
import axios from 'axios';

function HallList() {
    const [halls, setHalls] = useState([]);
    const [isLoading, setIsLoading] = useState(true);
    const [error, setError] = useState('');
    const token = localStorage.getItem('token');

    useEffect(() => {
        if (!token) {
            setError("Токен не найден");
            setIsLoading(false);
            return;
        }

        axios.get("http://localhost:8080/api/halls", {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        })
        .then(response => {
            setHalls(response.data);
            setIsLoading(false);
        })
        .catch(error => {
            console.error("Ошибка при загрузке залов:", error);
            setError("Ошибка при загрузке данных");
            setIsLoading(false);
            if (error.response?.status === 401) {
                localStorage.removeItem('token');
                window.location.href = '/login'; // Перенаправление на страницу входа
            }
        });
    }, [token]);

    return (
        <div>
            <h1>Список залов</h1>
            {error && <p style={{ color: 'red' }}>{error}</p>}
            {isLoading ? (
                <p>Загрузка...</p>
            ) : (
                <ul>
                    {halls.map(hall => (
                        <li key={hall.id}>
                            <h2>{hall.name}</h2>
                            <p>Вместимость: {hall.capacity}</p>
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
}

export default HallList;