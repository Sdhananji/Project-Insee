import axios from "axios";

const API = axios.create({
    baseURL:"http://localhost:8080/api",
});

// Request interceptor
API.interceptors.request.use((config)=>{
    const token = localStorage.getItem("token");
    if(token){
        config.headers.Authorization = `Bearer ${token}`;
    }
    config.headers['Content-Type'] = 'application/json';
    config.headers['Accept'] = 'application/json';
    return config;
});

// Response interceptor to debug
API.interceptors.response.use(
    (response) => {
        console.log("Interceptor - Response:", response);
        console.log("Interceptor - Data:", response.data);
        console.log("Interceptor - Headers:", response.headers);
        return response;
    },
    (error) => {
        console.error("Interceptor - Error:", error);
        return Promise.reject(error);
    }
);

export default API;