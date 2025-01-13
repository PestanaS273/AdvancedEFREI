import apiClient from "./api";

const saveResponse = async (response) => {
    const response = await apiClient.post('/saveResponse', response);
    return response.data;
}


export default {
    saveResponse
}