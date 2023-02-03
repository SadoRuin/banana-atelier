import axios from "axios";
import {
    LOGIN_USER,
    SIGNUP_USER,
    CHECK_EMAIL,
    CHECK_EMAIL_CODE,
    CHECK_NICKNAME
} from './types'

export function loginUser(dataTosubmit) {

    // const request = axios.post('https://i8a108.p.ssafy.io/api/users/login', dataTosubmit)
    const request = axios.post('http://localhost:8099/auth/login', dataTosubmit)
        .then(response => response)
        .catch(error => console.log('error', error))
    console.log(request);

    return {
        type: LOGIN_USER,
        payload: request
    }

}


export function signUpUser(dataTosubmit) {
    console.log(dataTosubmit)
    // const request = axios.post('https://i8a108.p.ssafy.io/api/users/signup', dataTosubmit)
    const request = axios.post('http://localhost:8099/users/signup', dataTosubmit)
        .then(response => response)
        .catch(error => console.log("error : ", error))

    return {
        type: SIGNUP_USER,
        payload: request
    }

}


export function check_email(dataTosubmit) {

    const request = axios
        .post('http://localhost:8099/users/verify', dataTosubmit)
        .then(response => response.data)

    return {
        type: CHECK_EMAIL,
        payload: request
    }
}

export function check_email_code(dataTosubmit) {

    const request = axios
        .post('http://localhost:8099/auth/verify', dataTosubmit)
        .then(response => response.data)

    return {
        type: CHECK_EMAIL_CODE,
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
