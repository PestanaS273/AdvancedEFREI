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

const create = async (user) => {
    console.log(user);
    const response = await apiClient.post('/create', user);
    return response.data;
}

const getAllStudents = async () => {
    const response = await apiClient.get('/getAllStudents');
    return response.data;
}

const getAllTeachers = async () => {
    const response = await apiClient.get('/getAllIntervenant');
    return response.data;
}

const getAllAdmin = async () => {
    const response = await apiClient.get('/getAllAdmin');
    return response.data;
}

const getUser = async (email) => {
    const response = await apiClient.get('/getMailUser', {
        params: {
            email: email
        }
    });
    return response.data;
}

const getActiveUsers = async () => {
    const response = await apiClient.get('/getNombreEtudiantActive');
    return response.data;
}



export default {
    checkEmailExist,
    inscrire,
    getAllStudents,
    getAllTeachers,
    getAllAdmin,
    getUser,
    create,
    getActiveUsers,

}