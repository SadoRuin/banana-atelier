import React from 'react'
import { useState } from 'react'
import { useDispatch } from 'react-redux'
import { loginUser } from '../../_actions/user_action'
import { useNavigate } from 'react-router-dom'
import logo from '../../assets/글씨_250.png'
import axios from 'axios'

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
        localStorage.setItem("token", response.payload.data.token)
        localStorage.setItem("expiration", response.payload.data.expiration)
        localStorage.setItem("nickname", response.payload.data.nickname)
        localStorage.setItem("role", response.payload.data.role)
        localStorage.setItem("profileImg", response.payload.data.profileImg)
        let token = localStorage.getItem("token")
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
        navigate('/')
      })
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

    </div>
  )
}