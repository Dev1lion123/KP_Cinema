import React, { useState } from "react";
import axios from "axios";

function AddFilm({ onFilmAdded }) {
    const [title, setTitle] = useState("");
    const [genre, setGenre] = useState("");
    const [description, setDescription] = useState("");
    const [rating, setRating] = useState("");
    const [release_date, setReleaseDate] = useState("");

    const handleSubmit = (e) => {
        e.preventDefault();
        const newFilm = { title, genre, description, rating, release_date };

        axios.post("http://localhost:8080/api/films", newFilm)
            .then(() => {
                if (onFilmAdded) {
                    onFilmAdded(); // Вызываем обратный вызов после добавления фильма
                }
            })
            .catch((error) => {
                console.error("Ошибка при добавлении фильма:", error.message || error);
            });

        // Сбрасываем поля формы
        setTitle("");
        setGenre("");
        setDescription("");
        setRating("");
        setReleaseDate("");
    };

    return (
        <form onSubmit={handleSubmit}>
            <h2>Добавить фильм</h2>
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
             <textarea
                placeholder="Рейтинг"
                value={rating}
                onChange={(e) => setRating(e.target.value)}
                required
            />
             <textarea
                placeholder="Дата выхода"
                value={release_date}
                onChange={(e) => setReleaseDate(e.target.value)}
                required
            />
            <button type="submit">Добавить</button>
        </form>
    );
}

export default AddFilm;
