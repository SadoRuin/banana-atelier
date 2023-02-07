import axios from "axios";

let token = localStorage.getItem("token")
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`

const customAxios = axios.create({
    headers: {
        'Authorization' : token
    },
    baseURL: 'https://i8a108.p.ssafy.io/api/'
})

export default customAxios