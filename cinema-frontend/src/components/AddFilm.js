import React, { useState } from 'react';
import axios from 'axios';

function AddFilm({ onFilmAdded }) {
    const [film, setFilm] = useState({
        title: "",
        genre: "",
        description: "",
        releaseDate: "",
        rating: ""
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFilm({ ...film, [name]: value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        axios.post("http://localhost:8080/api/films", film)
            .then(response => {
                alert("Фильм успешно добавлен!");
                onFilmAdded(response.data); // Обновляем список фильмов
                setFilm({ title: "", genre: "", description: "", releaseDate: "", rating: "" });
            })
            .catch(error => {
                console.error("Ошибка при добавлении фильма:", error);
            });
    };

    return (
        <form onSubmit={handleSubmit}>
            <h1>Добавить фильм</h1>
            <input
                type="text"
                name="title"
                placeholder="Название"
                value={film.title}
                onChange={handleChange}
                required
            />
            <input
                type="text"
                name="genre"
                placeholder="Жанр"
                value={film.genre}
                onChange={handleChange}
                required
            />
            <textarea
                name="description"
                placeholder="Описание"
                value={film.description}
                onChange={handleChange}
            />
            <input
                type="date"
                name="releaseDate"
                value={film.releaseDate}
                onChange={handleChange}
            />
            <input
                type="number"
                step="0.1"
                name="rating"
                placeholder="Рейтинг"
                value={film.rating}
                onChange={handleChange}
            />
            <button type="submit">Добавить фильм</button>
        </form>
    );
}

export default AddFilm;
