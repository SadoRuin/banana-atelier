import React from "react";
import styled from "styled-components";
import { Link } from "react-router-dom";

// 회원가입 혹은 로그인 폼을 보여 줍니다.

const AuthFormBlock = styled.div``
const textMap = {
    login: '로그인',
    register: '회원가입'
}


const AuthForm = ({type}) => {
    const text = textMap[type]
    return (
        <AuthFormBlock>
            <h3>{text}</h3>
            <form>
                {/* 회원가입 시에만 닉네임 입력 보임 */}
                {type === 'register' && 
                <p> <label htmlFor="nickname">닉네임</label>
                    <input type="text" name="nickname" placeholder="닉네임" autoComplete="닉네임"></input>
                    <button htmlFor="nickname">중복확인</button>
                </p>}


                {/* 회원가입/로그인 시 이메일 입력 보임 */}
                <p>
                    <label htmlFor="useremail">이메일</label>
                    <input type="email" autoComplete="useremail" name="useremail" placeholder="이메일" />
                </p>


                {/* 회원가입/로그인 시 비밀번호 입력 보임 */}
                <p><label htmlFor="password">비밀번호</label><input type="text" autoComplete="new-password" name="password" placeholder="비밀번호" /></p>
                

                {/* 로그인 시 유지 버튼, 비밀번호 찾기 */}
                {type === 'login' && 
                    <> <p><input type="checkbox" name="logining"/>
                        <label htmlFor="logining">로그인 유지</label></p>
                        <p><a href="/" onClick={event => {
                        event.preventDefault()
                        }}>비밀번호 찾기</a></p>
                    </>
                }

         
                {/* 회원가입 시에만 비밀번호 확인 입력 보임 */}
                {type === 'register' && 
                <p>
                    <label htmlFor="passwordConfirm">비밀번호 확인</label>
                    <input type="password" name="passwordConfirm" placeholder="비밀번호 확인" autoComplete="비밀번호 확인"></input>
                </p>}
                

                {/* 회원가입 시에는 회원가입, 로그인 시에는 로그인 버튼 */}
                <button>{text}</button>
            </form>


            {/* 로그인 화면에서는 회원가입 링크, 회원가입 화면에서는 로그인 링크 */}
            {type === 'login' ? (<Link to="/register">회원가입</Link>) : (<Link to="/login">로그인</Link>)}
            
        </AuthFormBlock>
    )
}

export default AuthForm