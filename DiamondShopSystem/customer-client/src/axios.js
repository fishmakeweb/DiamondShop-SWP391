// src/axios.js
import axios from 'axios';

const instance = axios.create({
    baseURL: 'http://139.162.39.187:8080/api',
    headers: {
        'Content-Type': 'application/json'
    }
});

export default instance;
