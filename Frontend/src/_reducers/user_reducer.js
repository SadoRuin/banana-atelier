import {
  SIGNUP_USER,
  LOGIN_USER,
  CHECK_EMAIL,
  CHECK_EMAIL_CODE,
  SEND_CODE,
  CHECK_NICKNAME,
  LOGIN_CODE,
  LOGOUT_CODE,
  SIGNUP_LOGIN,
  SIGNUP_LOGIN_RESET,
  LANDING_RENDERING,
  LANDING_RENDERING_LOGOUT,
  LANDING_RENDERING_RESET,
} from "../_actions/types";

// login_status
//    true : login 상태
//    false : logout 상태

// landing_status
//    1 : default
//    2 : login 후 상태
//    3 : logout 후 상태

// sign_login
//    true : 회원가입 직후 로그인 화면
//    false : 그 외 경우

const initialState = {
  login_status: false,
  landing_status: 1,
  sign_login: false,
};

export default function user(state = initialState, action) {
  switch (action.type) {
    case LOGIN_USER:
      return { ...state, payload: action.payload };

    case SIGNUP_USER:
      return { ...state, payload: action.payload };

    case CHECK_EMAIL:
      return { ...state, payload: action.payload };

    case CHECK_EMAIL_CODE:
      return { ...state, payload: action.payload };

    case CHECK_NICKNAME:
      return { ...state, payload: action.payload };

    case SEND_CODE:
      return { ...state, payload: action.payload };

    case LOGIN_CODE:
      return { ...state, login_status: true };

    case LOGOUT_CODE:
      return { ...state, login_status: false };

    case LANDING_RENDERING:
      return { ...state, landing_status: 2 };

    case LANDING_RENDERING_LOGOUT:
      return { ...state, landing_status: 3 };

    case LANDING_RENDERING_RESET:
      return { ...state, landing_status: 1 };

    case SIGNUP_LOGIN:
      return { ...state, sign_login: true };

    case SIGNUP_LOGIN_RESET:
      return { ...state, sign_login: false };

    default:
      return state;
  }
}
