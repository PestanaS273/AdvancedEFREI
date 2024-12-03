import apiClient from './api';

export const checkEmailExist = async (email) => {
    const response = await apiClient.get('/checkEmailExist', {
        params: { email }, 
    });
    return response.data; 
};
