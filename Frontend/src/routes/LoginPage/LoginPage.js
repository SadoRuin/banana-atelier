import React from 'react'
import { useState } from 'react'
import { useDispatch } from 'react-redux'
import { loginUser } from '../../_actions/user_action'
import { useNavigate } from 'react-router-dom'
import logo from '../../assets/글씨_250.png'


export default function LoginPage(props) {
  const dispatch = useDispatch()
  const navigate = useNavigate()


  const [Email, setEmail] = useState('')
  const [Password, setPassword] = useState('')

  const onEmailHandler = event => {
    setEmail(event.target.value)
  }

  const onPasswordHandler = event => {
    setPassword(event.target.value)
  }

  const onSubmitHandler = event => {
    event.preventDefault()

    let body = {
      email: Email,
      password: Password
    }

    dispatch(loginUser(body))
      .then(response => {
        console.log('response는 어떤 모습일까?', response.payload);
        localStorage.setItem("token", response.payload.token)
        })

    // 이거 token 잘 가져와짐
    // const token = localStorage.getItem('token')

      // .catch(error => {
      //   let error_code = error.response.data.message
      //   if (error_code === "Invalid ID") {
      //     alert("잘못된 아이디입니다.")
      //   } else if (error_code === "Invalid Password") {
      //     alert("잘못된 패스워드입니다.")
      //   } else {
      //     alert("이메일 인증을 진행해주세요.")
      //   }
      // })
  }
  return (
    <div>

      <img src={logo} alt="/" />

      <form style={{display: 'flex', flexDirection: 'column'}}
        onSubmit = {onSubmitHandler}>
        <label>Email</label>
        <input type="email" value={Email} onChange = {onEmailHandler} />
        <label>Password</label>
        <input type="password" value={Password} onChange = {onPasswordHandler} />
        <input type="checkbox" name="로그인 유지"/>
        <label htmlFor="로그인 유지">로그인 유지</label>
        <a href="https://i8a108.p.ssafy.io/api/users/find-pwd">비밀번호 찾기</a>
        <br />
        <button>로그인</button>
        <button onClick={event => {
          event.preventDefault()
          navigate("/signup")
        }}>회원가입</button>
      </form>
      <hr />
      <p>소셜 로그인 구현할 위치</p>
      
    </div>
  )
}