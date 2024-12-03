import apiClient from './api';

const checkEmailExist = async (email) => {
    const response = await apiClient.get('/checkEmailExist', {
        params: {
            email: email
        }
    });
    return response.data;
}

const inscrire = async (user) => {
    console.log(user);
    const response = await apiClient.post('/inscrire', user);
    return response.data;
}

export default {
    checkEmailExist,
    inscrire,
}