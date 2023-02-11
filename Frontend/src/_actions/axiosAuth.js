import axios from "axios";

let expiration = +localStorage.getItem("expiration")
let nowTime = new Date()
let nowTime_sec = nowTime.getTime()
let diffTime = (expiration - nowTime_sec)/1000


if (diffTime < 30) {
    axios.post('https://i8a108.p.ssafy.io/api/auth/reissue',
    { headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`
        }
    })
    .then(response => {
        localStorage.setItem("token", response.data.token)
        localStorage.setItem("expiration", response.data.expiration)
    })
    .catch(error => {
        console.log(error)
        localStorage.removeItem("token")
        localStorage.removeItem("expiration")
        localStorage.removeItem("nickname")
        localStorage.removeItem("profileImg")
        localStorage.removeItem("role")
        localStorage.removeItem("userSeq")
    })
}
const Token = localStorage.getItem("token")

const axiosAuth = axios.create({
    baseURL: 'https://i8a108.p.ssafy.io/api/',
    headers: {
        'Authorization' : `Bearer ${Token}`
    },
})

export default axiosAuth