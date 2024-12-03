import axios from 'axios';
const baseURL = import.meta.env.VITE_API_BASE_URL;

const apiClient = axios.create({
    baseURL: 'http://localhost:8095',
    withCredentials: false,
    headers: {
      'Content-Type': 'application/json'
    }
  });

  apiClient.interceptors.request.use(config => {
    const token = localStorage.getItem('token');
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    console.log('Request:', config);
    return config;
}, error => {
    return Promise.reject(error);
});



export default apiClient;