import {
    LOGIN_USER, SIGNUP_USER, CHECK_EMAIL, CHECK_EMAIL_CODE, 
    CHECK_NICKNAME, SEND_CODE, LOGIN_CODE, LOGOUT_CODE, LANDING_RENDERING,
    LANDING_RENDERING_LOGOUT, LANDING_RENDERING_RESET, SIGNUP_LOGIN, SIGNUP_LOGIN_RESET
} from '../_actions/types'

// login_status : 로그인 상태 시 true, 로그아웃 상태 시 false
// landing_status : 1, 2, 3 의 상태가 있으며 로그인, 로그아웃 시 상태 변함
// sign_login : 회원가입 직후 로그인 화면인 경우만 true
const initialState = {
    login_status: false,
    landing_status: 1,
    sign_login: false
}

// eslint-disable-next-line import/no-anonymous-default-export
export default function user (state = initialState, action) {
    switch (action.type) {
        case LOGIN_USER:
            return { ...state, payload: action.payload }

        case SIGNUP_USER:
            return { ...state, payload: action.payload }

        case CHECK_EMAIL:
            return { ...state, payload: action.payload }

        case CHECK_EMAIL_CODE:
            return { ...state, payload: action.payload }

        case CHECK_NICKNAME:
            return { ...state, payload: action.payload }

        case SEND_CODE:
            return { ...state, payload: action.payload }

        case LOGIN_CODE:
            return { ...state, login_status: true }

        case LOGOUT_CODE:
            return { ...state, login_status: false }

        case LANDING_RENDERING:
            return { ...state, landing_status: 2 }

        case LANDING_RENDERING_LOGOUT:
            return { ...state, landing_status: 3 }
        
        case LANDING_RENDERING_RESET:
            return { ...state, landing_status: 1 }

        case SIGNUP_LOGIN:
            return { ...state, sign_login: true }
        
        case SIGNUP_LOGIN_RESET:
            return { ...state, sign_login: false }
            
        default:
            return state
    }
}