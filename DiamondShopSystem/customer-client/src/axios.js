// src/axios.js
import axios from "axios";

const instance = axios.create({
  baseURL: "http://172.104.161.202:8080/api",
  headers: {
    "Content-Type": "application/json",
  },
});

export default instance;
