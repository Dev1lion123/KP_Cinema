import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams, useNavigate } from 'react-router-dom';

function UpdateFilm() {
    const { id } = useParams();
    const navigate = useNavigate();
    const [title, setTitle] = useState('');
    const [genre, setGenre] = useState('');
    const [description, setDescription] = useState('');
    const [releaseDate, setReleaseDate] = useState('');
    const [rating, setRating] = useState('');
    const [error, setError] = useState('');

    useEffect(() => {
        const token = localStorage.getItem('token');
        axios.get(`http://localhost:8080/api/films/${id}`, {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        })
        .then(response => {
            const film = response.data;
            setTitle(film.title);
            setGenre(film.genre);
            setDescription(film.description);
            setReleaseDate(film.releaseDate);
            setRating(film.rating);
        })
        .catch(error => {
            console.error('Ошибка при загрузке фильма:', error);
            setError("Ошибка при загрузке данных");
        });
    }, [id]);

    const handleSubmit = async (event) => {
        event.preventDefault();
        setError('');

        try {
            const token = localStorage.getItem('token');
            await axios.put(`http://localhost:8080/api/films/${id}`, {
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

            navigate('/films'); // Перенаправляем на страницу фильмов
        } catch (error) {
            console.error('Ошибка при обновлении фильма:', error);
            setError(error.response?.data?.message || "Ошибка при обновлении фильма");
        }
    };

    return (
        <div>
            <h1>Редактировать фильм</h1>
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
                <button type="submit">Сохранить</button>
            </form>
        </div>
    );
}

export default UpdateFilm;