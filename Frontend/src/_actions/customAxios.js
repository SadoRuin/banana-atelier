import axios from "axios";

const customAxios = () => {
    let expiration = +localStorage.getItem("expiration")
    let nowTime = new Date()
    let nowTime_sec = nowTime.getTime()
    let diffTime = (expiration - nowTime_sec)/1000

    axios.defaults.headers.common['Authorization'] = `Bearer ${localStorage.getItem("token")}`

    if (diffTime < 30) {
        axios.post('https://i8a108.p.ssafy.io/api/auth/reissue')
            .then(response => {
                localStorage.setItem("token", response.data.token)
                localStorage.setItem("expiration", response.data.expiration)
        })
    }

    return axios.create({
        headers: {
            'Authorization' : localStorage.getItem("token")
        },
        baseURL: 'https://i8a108.p.ssafy.io/api/'
    })
}

export default customAxios