// src/axios.js
import axios from 'axios';

const instance = axios.create({
    baseURL: 'https://hepheathus.store/api',
    headers: {
        'Content-Type': 'application/json'
    }
});

export default instance;
