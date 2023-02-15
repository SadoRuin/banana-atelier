import axios from "axios";
import {
    LOGIN_USER,
    SIGNUP_USER,
    CHECK_EMAIL,
    CHECK_EMAIL_CODE,
    CHECK_NICKNAME,
    SEND_CODE,
    LOGIN_CODE,
    LOGOUT_CODE,
    LANDING_RENDERING,
    LANDING_RENDERING_LOGOUT,
    LANDING_RENDERING_RESET,
    SIGNUP_LOGIN,
    SIGNUP_LOGIN_RESET
} from './types'

export function signup_login() {
    return {
        type: SIGNUP_LOGIN,
        payload: ""
    }
}

export function signup_login_reset() {
    return {
        type: SIGNUP_LOGIN_RESET,
        payload: ""
    }
}

export function loginUser(dataTosubmit) {
    // console.log("dataTosubmit", dataTosubmit)
    const request = axios.post('https://i8a108.p.ssafy.io/api/auth/login', dataTosubmit)
        .then(response => response)
        .catch(error => console.log('error', error))

    return {
        type: LOGIN_USER,
        payload: request
    }

}

export function loginCode() {
    return {
        type: LOGIN_CODE,
        payload: ""
    }
}

export function logoutCode() {
    return {
        type: LOGOUT_CODE,
        payload: ""
    }
}

export function signUpUser(dataTosubmit) {

    const request = axios.post('https://i8a108.p.ssafy.io/api/users/signup', dataTosubmit)
        .then(response => response)
        .catch(error => console.log("error : ", error))

    return {
        type: SIGNUP_USER,
        payload: request
    }

}


export function check_email(dataTosubmit) {

    const request = axios
        .get(`https://i8a108.p.ssafy.io/api/users/check/email/${dataTosubmit.email}`)
        .then(response => response.data)

    return {
        type: CHECK_EMAIL,
        payload: request
    }
}


export function check_email_code(dataTosubmit) {

    const request = axios
        .post('https://i8a108.p.ssafy.io/api/auth/verify', dataTosubmit)
        .then(response => response.data)

    return {
        type: CHECK_EMAIL_CODE,
        payload: request
    }
}


export function check_nickname(dataTosubmit) {

    const request = axios
        .get(`https://i8a108.p.ssafy.io/api/users/check/nickname/${dataTosubmit.nickname}`)
        .then(response => response.data)

    return {
        type: CHECK_NICKNAME,
        payload: request
    }
}


export function send_code(dataTosubmit) {

    const request = axios
        .get(`https://i8a108.p.ssafy.io/api/users/verify/${dataTosubmit.email}`)
        .then(response=>{console.log(response)})

    return {
        type: SEND_CODE,
        payload: request
    }
}

export function landingRendering() {
    return {
        type: LANDING_RENDERING,
        payload: ""
    }
}

export function landingRenderingLogout() {
    return {
        type: LANDING_RENDERING_LOGOUT,
        payload: ""
    }
}

export function landingRenderingReset() {
    return {
        type: LANDING_RENDERING_RESET,
        payload: ""
    }
}