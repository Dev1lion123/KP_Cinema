import React, { useState } from 'react';
import axios from 'axios';

const AddFilm = () => {
    const [film, setFilm] = useState({
        title: '',
        genre: '',
        description: '',
        releaseDate: '',
        rating: 0,
    });

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
            const response = await axios.post('http://localhost:8080/api/films', film, {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            });
            console.log('Фильм добавлен:', response.data);
            alert('Фильм успешно добавлен!');
        } catch (error) {
            console.error('Ошибка при добавлении фильма:', error);
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
            <button type="submit">Добавить фильм</button>
        </form>
    );
};

export default AddFilm;