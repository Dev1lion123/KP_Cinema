import React, { useState } from 'react';
import FilmList from './components/FilmList';
import AddFilm from './components/AddFilm';
import Register from './components/Register';
import Login from './components/Login';

function App() {
    const [isAuthenticated, setIsAuthenticated] = useState(!!localStorage.getItem('token'));
    const [isRegistering, setIsRegistering] = useState(false); // Переключатель между входом и регистрацией

    const handleLoginSuccess = () => {
        setIsAuthenticated(true);
    };

    const handleLogout = () => {
        localStorage.removeItem('token');
        setIsAuthenticated(false);
    };

    return (
        <div className="App">
            {!isAuthenticated ? (
                <div>
                    {isRegistering ? (
                        <>
                            <Register onRegisterSuccess={() => setIsRegistering(false)} />
                            <p>Уже есть аккаунт? <button onClick={() => setIsRegistering(false)}>Войти</button></p>
                        </>
                    ) : (
                        <>
                            <Login onLoginSuccess={handleLoginSuccess} />
                            <p>Нет аккаунта? <button onClick={() => setIsRegistering(true)}>Зарегистрироваться</button></p>
                        </>
                    )}
                </div>
            ) : (
                <div>
                    <button onClick={handleLogout}>Выйти</button>
                    <AddFilm />
                    <FilmList />
                </div>
            )}
        </div>
    );
}

export default App;
