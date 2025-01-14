import apiClient from "./api";

const saveResponse = async (answer) => {
    const response = await apiClient.post('/saveResponse', answer);
    return response.data;
}


export default {
    saveResponse
}