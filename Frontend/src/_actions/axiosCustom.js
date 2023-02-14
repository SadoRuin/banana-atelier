import axios from "axios";

const axiosCustom = axios.create({
    baseURL: 'https://i8a108.p.ssafy.io/api/'
})

export default axiosCustom