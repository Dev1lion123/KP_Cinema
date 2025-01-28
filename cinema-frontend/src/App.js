import React, { useRef } from 'react';
import FilmList from './components/FilmList';
import AddFilm from './components/AddFilm';

function App() {
    const filmListRef = useRef(); // Используем реф для доступа к методу обновления в FilmList

    const handleFilmAdded = () => {
        if (filmListRef.current) {
            filmListRef.current.refreshFilms(); // Обновляем список фильмов
        }
    };

    return (
        <div className="App">
            <AddFilm onFilmAdded={handleFilmAdded} />
            <FilmList ref={filmListRef} />
        </div>
    );
}

export default App;
