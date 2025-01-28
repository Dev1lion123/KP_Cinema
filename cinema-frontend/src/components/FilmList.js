import React, { useEffect, useState, forwardRef, useImperativeHandle } from "react";
import axios from "axios";

const FilmList = forwardRef((props, ref) => {
    const [films, setFilms] = useState([]);

    const loadFilms = () => {
        axios.get("http://localhost:8080/api/films")
            .then((response) => {
                setFilms(response.data);
            })
            .catch((error) => {
                console.error("Ошибка при загрузке фильмов:", error.message || error);
            });
    };

    // Экспортируем метод refreshFilms наружу
    useImperativeHandle(ref, () => ({
        refreshFilms: loadFilms,
    }));

    useEffect(() => {
        loadFilms();
    }, []);

    return (
        <div>
            <h1>Список фильмов</h1>
            <ul>
                {films.map((film) => (
                    <li key={film.id}>
                        <h2>{film.title}</h2>
                        <p>{film.genre}</p>
                        <p>{film.description}</p>
                        <p>Дата выхода: {film.releaseDate}</p>
                        <p>Рейтинг: {film.rating}</p>
                    </li>
                ))}
            </ul>
        </div>
    );
});

export default FilmList;
