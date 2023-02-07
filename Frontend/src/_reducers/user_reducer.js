import {
    LOGIN_USER, SIGNUP_USER, CHECK_EMAIL, CHECK_EMAIL_CODE, CHECK_NICKNAME, SEND_CODE
} from '../_actions/types'


// eslint-disable-next-line import/no-anonymous-default-export
export default function (state = {}, action) {
    switch (action.type) {
        case LOGIN_USER:
            return { ...state, loginSuccess: action.payload }

        case SIGNUP_USER:
            return { ...state, register: action.payload }

        case CHECK_EMAIL:
            return { ...state, emailValid: action.payload }

            case CHECK_EMAIL_CODE:
            return { ...state, emailValid: action.payload }

        case CHECK_NICKNAME:
            return { ...state, nicknameVaild: action.payload }

        case SEND_CODE:
            return { ...state, sendCode: action.payload }
            
        default:
            return state
    }
}