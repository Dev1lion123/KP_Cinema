import React, { useState } from 'react';
import axios from 'axios';

function EditFilm({ film, onFilmUpdated }) {
    const [updatedFilm, setUpdatedFilm] = useState({ ...film });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setUpdatedFilm({ ...updatedFilm, [name]: value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        axios.put(`http://localhost:8080/api/films/${film.id}`, updatedFilm)
            .then(response => {
                alert("Фильм успешно обновлён!");
                onFilmUpdated(response.data);
            })
            .catch(error => {
                console.error("Ошибка при обновлении фильма:", error);
            });
    };

    return (
        <form onSubmit={handleSubmit}>
            <h1>Редактировать фильм</h1>
            <input
                type="text"
                name="title"
                value={updatedFilm.title}
                onChange={handleChange}
                required
            />
            <input
                type="text"
                name="genre"
                value={updatedFilm.genre}
                onChange={handleChange}
                required
            />
            <textarea
                name="description"
                value={updatedFilm.description}
                onChange={handleChange}
            />
            <input
                type="date"
                name="releaseDate"
                value={updatedFilm.releaseDate}
                onChange={handleChange}
            />
            <input
                type="number"
                step="0.1"
                name="rating"
                value={updatedFilm.rating}
                onChange={handleChange}
            />
            <button type="submit">Сохранить изменения</button>
        </form>
    );
}

export default EditFilm;
