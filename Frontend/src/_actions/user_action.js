import axios from "axios";
import {
    LOGIN_USER,
    REGISTER_USER,
    CHECK_EMAIL,
    CHECK_NICKNAME
} from './types'

export function loginUser(dataTosubmit) {

    // const request = axios.post('https://i8a108.p.ssafy.io/api/users/login', dataTosubmit)
    const request = axios.post('http://localhost:8099/auth/login', dataTosubmit)
        .then(response => response.data)
        .catch(error => console.log('error', error))
    console.log(request);
    // action을 reducer에 넘겨주자
    return {
        type: LOGIN_USER,
        payload: request
    }

}


export function registerUser(dataTosubmit) {

    // const request = axios.post('https://i8a108.p.ssafy.io/api/users/signup', dataTosubmit)
    const request = axios.post('http://localhost:8099/users/signup', dataTosubmit)
        .then(response => response)
        .catch(error => console.log("error : ", error))
    // action을 reducer에 넘겨주자
    return {
        type: REGISTER_USER,
        payload: request
    }

}


export function check_email(dataTosubmit) {

    const request = axios
        .post("https://i8a108.p.ssafy.io/api/users/email-check/" + dataTosubmit.email , dataTosubmit)
        .then(response => response.data)

    return {
        type: CHECK_EMAIL,
        payload: request
    }
}


export function check_nickname(dataTosubmit) {

    const request = axios
        .post("https://i8a108.p.ssafy.io/api/users/nickname-check/" + dataTosubmit.nickname, dataTosubmit)
        .then(response => response.data)

    return {
        type: CHECK_NICKNAME,
        payload: request
    }
}
