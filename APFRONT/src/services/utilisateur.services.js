import apiClient from './api';

const checkEmailExist = async (email) => {
    const response = await apiClient.get('/checkEmailExist', {
        params: {
            email: email
        }
    });
    return response.data;
}

const inscrire = async (email, password) => {
    const response = await apiClient.post('/inscrire', {
        params: {
            email: email,
            password: password,
        }
    });
    return response.data;
}

export default {
    checkEmailExist,
    inscrire,
}