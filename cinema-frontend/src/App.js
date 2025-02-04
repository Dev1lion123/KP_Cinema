import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import FilmList from './components/FilmList';
import AddFilm from './components/AddFilm';
import Register from './components/Register';
import Login from './components/Login';
import HallList from './components/HallList';
import UpdateFilm from './components/UpdateFilm';
import api from './components/api'; // Импортируем instance из api.js
import './App.css';

function App() {
    const [isAuthenticated, setIsAuthenticated] = useState(!!localStorage.getItem('token'));

    // Проверка валидности токена при загрузке приложения
    useEffect(() => {
        const token = localStorage.getItem('token');
        if (token) {
            api.get("/auth/validate", {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            })
            .then(() => setIsAuthenticated(true))
            .catch((err) => {
                console.error('Ошибка проверки токена:', err);
                localStorage.removeItem('token');
                setIsAuthenticated(false);
            });
        }
    }, []);

    // Обработка успешного входа
    const handleLoginSuccess = () => {
        setIsAuthenticated(true);
    };

    return (
        <Router>
            <div className="App">
                {/* Кнопка выхода */}
                {isAuthenticated && (
                    <button onClick={() => {
                        localStorage.removeItem('token');
                        setIsAuthenticated(false);
                        window.location.href = '/login'; // Перенаправление на страницу входа
                    }}>
                        Выйти
                    </button>
                )}

                <Routes>
                    {/* Страница входа */}
                    <Route path="/login" element={<Login onLoginSuccess={handleLoginSuccess} />} />

                    {/* Страница регистрации */}
                    <Route path="/register" element={<Register />} />

                    {/* Защищенные маршруты */}
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

                    {/* Главная страница */}
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