import axios from "axios";

// baseURL을 가진 axios 인스턴스
const axiosCustom = axios.create({
    baseURL: 'https://i8a108.p.ssafy.io/api/'
})

export default axiosCustom