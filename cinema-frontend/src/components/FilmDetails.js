import axios from 'axios';
import React, { useState } from 'react';

const UpdateFilm = ({ film }) => {
  const [updatedFilm, setUpdatedFilm] = useState({ ...film });

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
    <form onSubmit={handleSubmit}>
      <input
        type="text"
        name="title"
        value={updatedFilm.title}
        onChange={handleChange}
        placeholder="Название фильма"
      />
      <input
        type="text"
        name="genre"
        value={updatedFilm.genre}
        onChange={handleChange}
        placeholder="Жанр"
      />
      <textarea
        name="description"
        value={updatedFilm.description}
        onChange={handleChange}
        placeholder="Описание"
      />
      <input
        type="date"
        name="releaseDate"
        value={updatedFilm.releaseDate}
        onChange={handleChange}
      />
      <input
        type="number"
        name="rating"
        value={updatedFilm.rating}
        onChange={handleChange}
        placeholder="Рейтинг"
      />
      <button type="submit">Обновить фильм</button>
    </form>
  );
};

export default UpdateFilm;
