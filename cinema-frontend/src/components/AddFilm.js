import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function AddFilm() {
    const [title, setTitle] = useState('');
    const [genre, setGenre] = useState('');
    const [description, setDescription] = useState('');
    const [releaseDate, setReleaseDate] = useState('');
    const [rating, setRating] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleSubmit = async (event) => {
        event.preventDefault();
        setError('');

        try {
            const token = localStorage.getItem('token');
            const response = await axios.post("http://localhost:8080/api/films", {
                title,
                genre,
                description,
                releaseDate,
                rating
            }, {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            });

            console.log('Фильм добавлен:', response.data);
            navigate('/films'); // Перенаправляем на страницу фильмов
        } catch (error) {
            console.error('Ошибка при добавлении фильма:', error);
            setError(error.response?.data?.message || "Ошибка при добавлении фильма");
        }
    };

    return (
        <div>
            <h1>Добавить фильм</h1>
            {error && <p style={{ color: 'red' }}>{error}</p>}
            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    placeholder="Название"
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}
                    required
                />
                <input
                    type="text"
                    placeholder="Жанр"
                    value={genre}
                    onChange={(e) => setGenre(e.target.value)}
                    required
                />
                <textarea
                    placeholder="Описание"
                    value={description}
                    onChange={(e) => setDescription(e.target.value)}
                    required
                />
                <input
                    type="date"
                    placeholder="Дата выхода"
                    value={releaseDate}
                    onChange={(e) => setReleaseDate(e.target.value)}
                    required
                />
                <input
                    type="number"
                    placeholder="Рейтинг"
                    value={rating}
                    onChange={(e) => setRating(e.target.value)}
                    required
                />
                <button type="submit">Добавить</button>
            </form>
        </div>
    );
}

export default AddFilm;