import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

function FilmList() {
    const [films, setFilms] = useState([]);
    const token = localStorage.getItem('token');

    useEffect(() => {
        axios.get("http://localhost:8080/api/films", {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        })
        .then(response => {
            setFilms(response.data);
        })
        .catch(error => {
            console.error("Ошибка при загрузке фильмов:", error);
        });
    }, [token]);

    return (
        <div>
            <h1>Список фильмов</h1>
            <ul>
                {films.map(film => (
                    <li key={film.id}>
                        <h2>{film.title}</h2>
                        <p>{film.genre}</p>
                        <p>{film.description}</p>
                        <p>Дата выхода: {film.releaseDate}</p>
                        <p>Рейтинг: {film.rating}</p>
                        <Link to={`/update-film/${film.id}`}>Редактировать</Link>
                    </li>
                ))}
            </ul>
        </div>
    );
}

export default FilmList;