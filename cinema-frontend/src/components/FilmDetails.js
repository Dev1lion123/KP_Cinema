import axios from 'axios';
import React, { useState } from 'react';

const UpdateFilm = ({ film }) => {
  const [updatedFilm, setUpdatedFilm] = useState({ ...film });
  const [selectedFilm, setSelectedFilm] = useState(null);

  const handleChange = (e) => {
    setUpdatedFilm({
      ...updatedFilm,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.put(`http://localhost:8080/api/films/${film.id}`, updatedFilm);
      console.log('Фильм обновлен:', response.data);
    } catch (error) {
      console.error('Ошибка при обновлении фильма:', error);
    }
  };

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
                    <button onClick={() => setSelectedFilm(film)}>Редактировать</button>
                </li>
            ))}
        </ul>
        {selectedFilm && <UpdateFilm film={selectedFilm} />}
    </div>
  );
};

export default UpdateFilm;
