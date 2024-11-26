import apiClient from './api';

export const getAllCours = async () => {
    const response = await apiClient.get('/cours');
    return response.data;
}