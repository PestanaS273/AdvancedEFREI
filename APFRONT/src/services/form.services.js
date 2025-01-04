import apiClient from './api';

const getAllForms = async () => {
    const response = await apiClient.get('/getAllFoms');
    return response.data;
}

const getFormsForCourse = async (idCours) => {
    const response = await apiClient.get('/getAllFromsfromCours', {
        params: {
            idCours: idCours
        }
    });
    return response.data;
}

const getFormsForUser = async (id) => {
    const response = await apiClient.get('/getFormsForUser', {
        params: {
            id: id
        }
    });
    return response.data;
}

const getForm = async (id) => {
    const response = await apiClient.get('/getForm', {
        params: {
            id: id
        }
    });
    return response.data;
}

const createForm = async (form) => {
    const response = await apiClient.post('/createForm', form);
    return response.data;
}

const getBaseFormTemplate = async () => {
    const response = await apiClient.get('/getBaseFormTemplate');
    return response.data;
}

const addForm = async (form) => {
    const response = await apiClient.post('/addForme', form);
    return response.data;
}

export default {
    getAllForms,
    getFormsForCourse,
    getForm,
    createForm,
    getBaseFormTemplate,
    getFormsForUser,
    addForm
}