import React, { useState } from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import FilmList from './components/FilmList';
import AddFilm from './components/AddFilm';
import Register from './components/Register';
import Login from './components/Login';
import HallList from './components/HallList';
import UpdateFilm from './components/UpdateFilm';
import './App.css';

function App() {
    const [isAuthenticated, setIsAuthenticated] = useState(!!localStorage.getItem('token'));

    const handleLoginSuccess = () => {
        setIsAuthenticated(true);
    };

    const handleLogout = () => {
        localStorage.removeItem('token');
        setIsAuthenticated(false);
    };

    return (
        <Router>
            <div className="App">
                {isAuthenticated && <button onClick={handleLogout}>Выйти</button>}
                <Routes>
                    <Route path="/login" element={<Login onLoginSuccess={handleLoginSuccess} />} />
                    <Route path="/register" element={<Register />} />
                    <Route
                        path="/films"
                        element={isAuthenticated ? <FilmList /> : <Navigate to="/login" />}
                    />
                    <Route
                        path="/add-film"
                        element={isAuthenticated ? <AddFilm /> : <Navigate to="/login" />}
                    />
                    <Route
                        path="/update-film/:id"
                        element={isAuthenticated ? <UpdateFilm /> : <Navigate to="/login" />}
                    />
                    <Route
                        path="/halls"
                        element={isAuthenticated ? <HallList /> : <Navigate to="/login" />}
                    />
                    <Route
                        path="/"
                        element={
                            !isAuthenticated ? (
                                <div>
                                    <h1>Добро пожаловать в кинотеатр!</h1>
                                    <p>Пожалуйста, зарегистрируйтесь или войдите в систему.</p>
                                    <Register />
                                    <p>Уже есть аккаунт? <a href="/login">Войти</a></p>
                                </div>
                            ) : (
                                <Navigate to="/films" />
                            )
                        }
                    />
                </Routes>
            </div>
        </Router>
    );
}

export default App;