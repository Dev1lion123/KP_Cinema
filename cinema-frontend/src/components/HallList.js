import React, { useEffect, useState } from 'react';
import axios from 'axios';

const HallList = () => {
    const [halls, setHalls] = useState([]);

    useEffect(() => {
        const fetchHalls = async () => {
            try {
                const token = localStorage.getItem('token');
                const response = await axios.get('http://localhost:8080/api/halls', {
                    headers: {
                        Authorization: `Bearer ${token}`,
                    },
                });
                setHalls(response.data);
            } catch (error) {
                console.error('Ошибка при загрузке залов:', error);
            }
        };
        fetchHalls();
    }, []);

    return (
        <div>
            <h1>Список залов</h1>
            <ul>
                {halls.map((hall) => (
                    <li key={hall.id}>
                        {hall.name} - {hall.capacity} мест
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default HallList;