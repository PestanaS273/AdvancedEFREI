import apiClient from "./api";

const saveResponse = async (id, reponse) => {
    const response = await apiClient.put('/setAnswers', null, {
        params:{
            id: id,
            reponse: reponse
        }
    });
    return response.data;
}


export default {
    saveResponse
}