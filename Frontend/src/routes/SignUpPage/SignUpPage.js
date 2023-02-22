import React, { useState } from "react";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import { useForm } from "react-hook-form";

import { yupResolver } from "@hookform/resolvers/yup";
import styles from "./SignUpPage.module.css";
import "../../components/commons/commons.css";
import logo from "../../assets/글씨_250.png";
import * as yup from "yup";
import {
    check_email,
    check_email_code,
    check_nickname,
    send_code,
    signup_login,
    signUpUser,
} from "../../_actions/user_action";


function SignUpPage(props) {
    const dispatch = useDispatch();
    const navigate = useNavigate();

    // 이메일 인증번호 발송 하단 멘트 (인증번호가 발송되었습니다.)
    const [emailValidMessage, SetEmailValidMessage] = useState("");

    // 이메일 인증번호 확인 하단 멘트 (인증이 완료되었습니다.)
    const [emailCodeValidMessage, SetEmailCodeValidMessage] = useState("");

    // 닉네임 중복체크 완료라면? true로
    const [nicknameCheck, setNicknameCheck] = useState("");

    // 이메일 인증 완료라면? true로
    const [emailCheck, SetEmailCheck] = useState(false);

    // 닉네임 인증 완료라면? true
    const [nicknameValidCheck, SetNicknameValidCheck] = useState(false);

    // 이메일 인증 하단 멘트
    const [verifyMessage, SetVerifyMessage] = useState("인증번호 발송");

    // 로그인 관련 경고 문구
    const [alertMessage, SetAlertMessage] = useState("");

    // yup 라이브러리 이용
    const formSchema = yup.object({
        nickname: yup.string().required("닉네임을 입력해주세요."),
        email: yup.string().required("이메일을 입력해주세요.").email("이메일 형식이 아닙니다."),
        password: yup
            .string()
            .required("영문, 숫자, 기호 포함 8자리 이상, 16자 이하를 입력하세요.")
            .min(8, "영문, 숫자, 기호 포함 8자리 이상, 16자 이하를 입력하세요.")
            .max(15, "영문, 숫자, 기호 포함 8자리 이상, 16자 이하를 입력하세요.")
            .matches(
                /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,16}$/,
                "영문, 숫자, 기호 포함 8자리 이상, 16자 이하를 입력하세요."
            ),
        passwordConfirm: yup.string().oneOf([yup.ref("password")], "비밀번호가 다릅니다."),
    });
    const {
        register,
        handleSubmit,
        formState: {errors},
    } = useForm({
        mode: "onChange",
        resolver: yupResolver(formSchema),
    });

    // 닉네임 중복체크
    const onCheckNickname = () => {
        const nickname = document.querySelector("#nickname");
        let body = {
            nickname: nickname.value,
        };
        dispatch(check_nickname(body))
            .then((response) => {
                if (response.payload.message === "사용가능한 닉네임입니다.") {
                    setNicknameCheck("사용가능한 닉네임입니다.");
                    SetNicknameValidCheck(true);
                }
            })
            .catch(() => {
                setNicknameCheck("중복된 닉네임입니다.");
                SetNicknameValidCheck(false);
            });
    };

    // 이메일 인증
    const emailVerify = () => {
        SetVerifyMessage("인증번호 재발송");
        const emailValid = document.querySelector("#email");
        let body = {
            email: emailValid.value,
        };
        dispatch(check_email(body))
            .then((response) => {
                SetEmailValidMessage("사용 가능한 이메일입니다.");
                dispatch(send_code(body)).then((response) => {
                    SetEmailValidMessage("인증번호가 발송되었습니다.");
                });
            })
            .catch((error) => {
                console.log(error);
                SetEmailValidMessage("중복된 이메일입니다.");
            });
    };

    // 인증코드 유효성 검사
    const VerifyCheck = () => {
        const emailValid = document.querySelector("#email");
        const verifychecknum = document.querySelector("#verifycheck");
        let body = {
            email: emailValid.value,
            code: verifychecknum.value,
        };
        dispatch(check_email_code(body))
            .then((response) => {
                if (response.payload.code === "success") {
                    SetEmailCodeValidMessage("인증이 완료되었습니다.");
                    SetEmailCheck(true);
                } else {
                    SetEmailCodeValidMessage("잘못된 인증코드입니다.");
                    SetEmailCheck(false);
                }
            })
            .catch((error) => {
                SetEmailCodeValidMessage("잘못된 인증코드입니다.");
                SetEmailCheck(false);
            });
    };

    // 회원가입 버튼 클릭 후 실행되는 함수
    const onSubmit = (data) => {
        let body = {
            email: data.email,
            nickname: data.nickname,
            password: data.password,
        };
        if (!nicknameValidCheck) {
            SetAlertMessage("닉네임 중복체크를 하세요.");
        } else if (!emailCheck) {
            SetAlertMessage("인증코드 확인을 하세요.");
        } else {
            dispatch(signUpUser(body))
                .then(() => {
                    dispatch(signup_login());
                    alert("회원가입이 완료되었습니다.");
                    navigate("/login");
                })
                .catch((error) => {
                    alert(error);
                    navigate("/login");
                });
        }
    };


    return (
        <div className={styles["sign-up-container"]}>
            <div className={styles["sign-up-box"]}>
                <div className={styles["inner-box"]}>
                    <div>
                        <img
                            src={logo}
                            alt="/"
                            id={styles["logo-img"]}
                            style={{cursor: "pointer"}}
                            onClick={() => navigate("/")}
                        />
                    </div>

                    <div id={styles["div-on-form"]}>
                        <form onSubmit={handleSubmit(onSubmit)}>
                            <div className={styles["label-input"]}>
                                <label htmlFor="nickname" className={styles["label-of-form"]}>
                                    닉네임
                                </label>
                                <div className={styles["input-button"]}>
                                    <input
                                        name="nickname"
                                        placeholder="nickname"
                                        {...register("nickname")}
                                        id="nickname"
                                        className={styles["input-of-form"]}
                                    />
                                    <button className={styles["form-btn"]} onClick={onCheckNickname}>
                                        중복확인
                                    </button>
                                </div>
                            </div>
                            <div className={styles["hidden-msg"]}>{nicknameCheck}</div>

                            <div className={styles["label-input"]}>
                                <label htmlFor="email" className={styles["label-of-form"]}>
                                    이메일
                                </label>
                                <div className={styles["input-button"]}>
                                    <input
                                        id="email"
                                        name="email"
                                        placeholder="email"
                                        {...register("email")}
                                        className={styles["input-of-form"]}
                                    />
                                    <button onClick={emailVerify} className={styles["form-btn"]}>
                                        {verifyMessage}
                                    </button>
                                    {/* {errors.email && <p style={{width: '100%'}}>{errors.email.message}</p>} */}
                                </div>
                                <div className={styles["hidden-msg"]}>{emailValidMessage}</div>
                            </div>

                            <div className={styles["input-button"]}>
                                <input
                                    className={styles["input-of-form"]}
                                    type="text"
                                    placeholder="인증번호를 입력해주세요"
                                    id="verifycheck"
                                />
                                <button onClick={VerifyCheck} className={styles["form-btn"]}>
                                    인증번호 확인
                                </button>
                            </div>
                            <div className={styles["hidden-msg"]}>{emailCodeValidMessage}</div>

                            <div className={styles["label-input"]}>
                                <label htmlFor="password" className={styles["label-of-form"]}>
                                    비밀번호
                                </label>
                                <input
                                    className={styles["input-of-form"]}
                                    type="password"
                                    name="password"
                                    placeholder="password"
                                    {...register("password")}
                                />
                            </div>
                            <div className={styles["hidden-msg"]}>{errors.password && errors.password.message}</div>

                            <div className={styles["label-input"]}>
                                <label htmlFor="passwordConfirm" className={styles["label-of-form"]}>
                                    비밀번호 확인
                                </label>
                                <input
                                    className={styles["input-of-form"]}
                                    type="password"
                                    name="passwordConfirm"
                                    placeholder="password"
                                    {...register("passwordConfirm")}
                                />
                            </div>

                            <div className={styles["hidden-msg"]}>
                                {errors.passwordConfirm && errors.passwordConfirm.message}
                            </div>

                            <div className={styles["full-btn"]}>
                                <button className={styles["form-btn"]}>회원가입</button>
                            </div>
                            <div className={styles["hidden-msg"]}>{alertMessage}</div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default SignUpPage;
