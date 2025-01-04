import apiClient from './api';

const getAllCours = async () => {
    const response = await apiClient.get('/getAllCours');
    return response.data;
}

const getStudentsFromCours = async (idCours) => {
    const response = await apiClient.get('/getAllEtudiantByCours', {
        params: {
            idCours: idCours
        }
    });
    return response.data;
}

export default {
    getAllCours,
    getStudentsFromCours
}