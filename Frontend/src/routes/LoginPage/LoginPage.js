import React, {useState} from 'react'
import {useDispatch} from 'react-redux'
import './LoginPage.css'
import {landingRendering, loginCode, loginUser} from '../../_actions/user_action'
import {useNavigate} from 'react-router-dom'
import logo from '../../assets/글씨_250.png'
import axios from 'axios'
// import { useSelector } from 'react-redux'
import '../../components/commons/commons.css'

export default function LoginPage(props) {
  const dispatch = useDispatch()
  const navigate = useNavigate()
  const [Email, setEmail] = useState('')
  const [Password, setPassword] = useState('')
  const [pwMessage, setPwMessage] = useState('')
  // const userExample = useSelector(state => state.user)
  // console.log("useSelector 해서 state 가져온 것", userExample)
  // const loginWonder = useSelector(state => state.user.login_status)
  // console.log("로그인 했나요?", loginWonder)
  // console.log("겁나 빡세네", loginWonder === true)

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
        dispatch(landingRendering())
        localStorage.setItem("token", response.payload.data.token)
        localStorage.setItem("expiration", response.payload.data.expiration)
        localStorage.setItem("nickname", response.payload.data.nickname)
        localStorage.setItem("role", response.payload.data.role)
        localStorage.setItem("profileImg", response.payload.data.profileImg)
        localStorage.setItem("userSeq", response.payload.data.userSeq)
        let token = localStorage.getItem("token")
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
        navigate(-1)
      })
      .catch(error => {
        console.log(error)
        setPwMessage('Email이나 Password를 확인하세요.')
      })
    dispatch(loginCode())
  }
  return (
    <div className="login_container">
      <div className='login_box'>
        <div>
          <img src={logo} alt="/" id="logo_img"/>
        </div>
        <div id="div-form">
          <form style={{display: 'flex', flexDirection: 'column'}}
                onSubmit={onSubmitHandler}>
            <div className="label-input">
              <label className="form-label">Email</label>
              <input type="email" value={Email} onChange={onEmailHandler}/>
            </div>
            <div className="label-input">
              <label className="form-label">Password</label>
              <input type="password" value={Password} onChange={onPasswordHandler}/>
            </div>
            <br/>
            <button className="form-btn">로그인</button>
            <button onClick={event => {
              event.preventDefault()
              navigate("/signup")
            }} className="form-btn">회원가입
            </button>
            <button onClick={handleFindPw} className="form-btn">비밀번호 재설정</button>
          </form>
          <div className="hidden-msg">
            {pwMessage}
          </div>
        </div>
      </div>
    </div>

  )
}