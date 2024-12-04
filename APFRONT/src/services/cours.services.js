import apiClient from './api';

const getAllCours = async () => {
    const response = await apiClient.get('/getAllCours');
    return response.data;
}

export default {
    getAllCours,
}