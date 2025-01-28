import React, { useEffect, useState } from 'react';
import api from './api';

function TicketList() {
    const [tickets, setTickets] = useState([]);

    useEffect(() => {
        api.get('/tickets')
            .then((response) => setTickets(response.data))
            .catch((error) => console.error('Ошибка загрузки билетов:', error));
    }, []);

    const addTicket = async (ticket) => {
        try {
            const response = await api.post('/tickets', ticket);
            setTickets((prevTickets) => [...prevTickets, response.data]);
        } catch (error) {
            alert('Ошибка при добавлении билета.');
            console.error(error);
        }
    };

    const deleteTicket = async (id) => {
        try {
            await api.delete(`/tickets/${id}`);
            setTickets((prevTickets) => prevTickets.filter((ticket) => ticket.id !== id));
        } catch (error) {
            alert('Ошибка при удалении билета.');
            console.error(error);
        }
    };

    return (
        <div>
            <h1>Список билетов</h1>
            <ul>
                {tickets.map((ticket) => (
                    <li key={ticket.id}>
                        {ticket.details}{' '}
                        <button onClick={() => deleteTicket(ticket.id)}>Удалить</button>
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default TicketList;
