import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams, useNavigate } from 'react-router-dom';

const UpdateFilm = () => {
    const { id } = useParams();
    const navigate = useNavigate();
    const [film, setFilm] = useState({
        title: '',
        genre: '',
        description: '',
        releaseDate: '',
        rating: 0,
    });

    useEffect(() => {
        const fetchFilm = async () => {
            try {
                const token = localStorage.getItem('token');
                const response = await axios.get(`http://localhost:8080/api/films/${id}`, {
                    headers: {
                        Authorization: `Bearer ${token}`,
                    },
                });
                setFilm(response.data);
            } catch (error) {
                console.error('Ошибка при загрузке фильма:', error);
            }
        };
        fetchFilm();
    }, [id]);

    const handleChange = (e) => {
        setFilm({
            ...film,
            [e.target.name]: e.target.value,
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const token = localStorage.getItem('token');
            const response = await axios.put(`http://localhost:8080/api/films/${id}`, film, {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            });
            console.log('Фильм обновлен:', response.data);
            alert('Фильм успешно обновлен!');
            navigate('/films');
        } catch (error) {
            console.error('Ошибка при обновлении фильма:', error);
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <input
                type="text"
                name="title"
                value={film.title}
                onChange={handleChange}
                placeholder="Название фильма"
            />
            <input
                type="text"
                name="genre"
                value={film.genre}
                onChange={handleChange}
                placeholder="Жанр"
            />
            <textarea
                name="description"
                value={film.description}
                onChange={handleChange}
                placeholder="Описание"
            />
            <input
                type="date"
                name="releaseDate"
                value={film.releaseDate}
                onChange={handleChange}
            />
            <input
                type="number"
                name="rating"
                value={film.rating}
                onChange={handleChange}
                placeholder="Рейтинг"
            />
            <button type="submit">Обновить фильм</button>
        </form>
    );
};

export default UpdateFilm;