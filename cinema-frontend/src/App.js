import React, { useState } from 'react';
import FilmList from './components/FilmList';
import AddFilm from './components/AddFilm';

function App() {
    const [films, setFilms] = useState([]);

    const handleFilmAdded = (newFilm) => {
        setFilms([...films, newFilm]); // Добавляем новый фильм в список
    };

    return (
        <div className="App">
            <AddFilm onFilmAdded={handleFilmAdded} />
            <FilmList films={films} />
        </div>
    );
}

export default App;
