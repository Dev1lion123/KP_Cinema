import axios from "axios";

const instance = axios.create({
    baseURL: "http://localhost:8080/api",
    timeout: 10000, // 10 секунд на запрос
});

// Добавление токена в заголовки для каждого запроса
instance.interceptors.request.use((config) => {
    const token = localStorage.getItem("token");
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
}, (error) => {
    return Promise.reject(error);
});

export default instance;