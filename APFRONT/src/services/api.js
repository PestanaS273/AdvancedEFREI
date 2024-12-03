import axios from 'axios';
const baseURL = import.meta.env.VITE_API_BASE_URL;

const apiClient = axios.create({
    baseURL: 'http://localhost:8095',
    withCredentials: false,
    headers: {
      'Content-Type': 'application/json'
    }
  })



export default apiClient;