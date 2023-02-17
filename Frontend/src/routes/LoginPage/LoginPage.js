import React, {useState} from "react";
import {useDispatch, useSelector} from "react-redux";
import "./LoginPage.css";
import {landingRendering, loginCode, loginUser, signup_login_reset,} from "../../_actions/user_action";
import {useNavigate} from "react-router-dom";
import logo from "../../assets/글씨_250.png";
import axios from "axios";

export default function LoginPage(props) {
    const dispatch = useDispatch();
    const navigate = useNavigate();

    const [Email, setEmail] = useState("");
    const [Password, setPassword] = useState("");
    const [pwMessage, setPwMessage] = useState("");
    const afterSignup = useSelector((state) => state.user.sign_login);
    console.log("afterSignup ===>>>", afterSignup);

    const handleFindPw = (event) => {
        event.preventDefault();
        let body = {
            email: Email,
        };
        axios
            .patch("https://i8a108.p.ssafy.io/api/users/find-password", body)
            .then((response) => {
                console.log("response", response);
                setPwMessage("임시 비밀번호가 이메일로 발급되었습니다.");
            })
            .catch((error) => {
                console.log(error);
                if (error.message === "Request failed with status code 404") {
                    setPwMessage("회원 정보가 없습니다.");
                }
            });
    };

    const onEmailHandler = (event) => {
        setEmail(event.target.value);
    };

    const onPasswordHandler = (event) => {
        setPassword(event.target.value);
    };

    const onSubmitHandler = (event) => {
        event.preventDefault();

        let body = {
            email: Email,
            password: Password,
        };

        dispatch(loginUser(body))
            .then((response) => {
                dispatch(landingRendering());
                localStorage.setItem("token", response.payload.data.token);
                localStorage.setItem("expiration", response.payload.data.expiration);
                localStorage.setItem("nickname", response.payload.data.nickname);
                localStorage.setItem("role", response.payload.data.role);
                localStorage.setItem("profileImg", response.payload.data.profileImg);
                localStorage.setItem("userSeq", response.payload.data.userSeq);
                let token = localStorage.getItem("token");
                axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;
                if (afterSignup) {
                    dispatch(signup_login_reset());
                    navigate("/");
                } else {
                    navigate(-1);
                }
            })
            .catch((error) => {
                console.log(error);
                setPwMessage("Email이나 Password를 확인하세요.");
            });
        dispatch(loginCode());
    };
    return (
        <div className="login-container">
            <div className="login-box">
                <div>
                    <img src={logo} alt="/" id="logo-img" style={{cursor: 'pointer'}} onClick={()=>
                        navigate("/")
                    } />
                </div>
                <div id="div-on-form">
                    <form onSubmit={onSubmitHandler}>
                        <div className="label-input">
                            <label className="label-of-form">Email</label>
                            <input
                                type="email"
                                value={Email}
                                onChange={onEmailHandler}
                                className="input-of-form"
                            />
                        </div>
                        <div className="label-input">
                            <label className="label-of-form">Password</label>
                            <input
                                type="password"
                                value={Password}
                                onChange={onPasswordHandler}
                                className="input-of-form"
                            />
                        </div>
                        <br/>
                        <div className="all-buttons">
                            <div className="buttons">
                                <button
                                    style={{marginRight: "5px", width: "165px"}}
                                    className="form-btn"
                                >
                                    로그인
                                </button>
                                <button
                                    style={{marginLeft: "5px", width: "165px"}}
                                    onClick={(event) => {
                                        event.preventDefault();
                                        navigate("/signup");
                                    }}
                                    className="form-btn"
                                >
                                    회원가입
                                </button>
                            </div>
                            <div className="buttons">
                                <button
                                    onClick={handleFindPw}
                                    className="form-btn"
                                    style={{width: "340px"}}
                                >
                                    비밀번호 재설정
                                </button>
                            </div>
                        </div>
                    </form>
                    <div className="hidden-msg">{pwMessage}</div>
                </div>
            </div>
        </div>
    );
}
