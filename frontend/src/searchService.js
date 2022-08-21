import axios from "axios";

export default async function (term) {
    try {
        const response = await axios.get(`/api/search?term=${term}`);
        return response.data;
    } catch (e) {
        return [];
    }
}