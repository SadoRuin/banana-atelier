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
  const [pwMessage, setPwMessage] = useState('')

  const handleFindPw = (event) => {
    event.preventDefault()
    let body = {
      "email": Email
    }
    axios.patch('https://i8a108.p.ssafy.io/api/users/find-password', body)
      .then(response => {
        console.log('response', response)
        setPwMessage('임시 비밀번호가 이메일로 발급되었습니다.')
      })
      .catch(error => {
        console.log(error)
        if (error.message === 'Request failed with status code 404') {
          setPwMessage('회원 정보가 없습니다.')
        }
      })
  }
  
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
      .catch(error => {
        console.log(error)
        setPwMessage('Email이나 Password를 확인하세요.')
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
        <br />
        <button>로그인</button>
        <button onClick={event => {
          event.preventDefault()
          navigate("/signup")
        }}>회원가입</button>
        <button onClick={handleFindPw}>비밀번호 재설정</button>
        { pwMessage }
      </form>

    </div>
  )
}