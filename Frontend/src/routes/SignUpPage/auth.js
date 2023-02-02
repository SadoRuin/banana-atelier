import React from 'react'
import logo from '../../../assets/글씨_250.png'

function auth() {
  return (
    <div>

        <img src={logo} alt="/" />

        <div>
            <h2>바나나 공방에 오신 것을 환영합니다!</h2>
            이메일 인증을 위한 메일이 발송되었습니다.
            회원가입 완료를 위한 이메일 인증을 진행해 주세요.
        </div>

        <div>가입 이메일 주소 : {}</div>

        <div>
            이메일을 받지 못하셨나요?
            <button onClick={event => {
                event.preventDefault()
            }}>이메일 다시 보내기</button>
        </div>

    </div>
  )
}

export default auth
