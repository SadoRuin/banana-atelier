import React from 'react'
import { useState } from 'react'
import { useDispatch } from 'react-redux'
import logo from '../../assets/글씨_250.png'
import { useLocation, useNavigate } from 'react-router-dom'
import { 
  registerUser,
  check_email, 
  check_nickname } from '../../_actions/user_action'


  function RegisterPage(props) {

    const dispatch = useDispatch()
    const location = useLocation()
    const navigate = useNavigate()
  
    const [Email, setEmail] = useState('')
    const [Password, setPassword] = useState('')
    const [Nickname, setNickname] = useState('')
    const [ConfirmPassword, setConfirmPassword] = useState('')
    
    const [check, setCheck] = useState(false)
    const [checkEmail, setCheckEmail] = useState(false)
  
    // // 닉네임 중복확인
    // const onCheckNickname = event => {
    //   event.preventDefault()
  
    //   let body = {
    //     nickname: Nickname
    //   }
    //   dispatch(check_nickname(body))
    //     .then((response) => {
    //       if (response.payload.message !== 'fail') {
    //         alert("이미 존재하는 닉네임입니다.")
    //         setNickname('')
    //       } else {
    //         alert("사용 가능한 닉네임입니다.")
    //         setCheck(true)
    //       }
    //     })
    // }
  
    // 이메일 유효성 확인
    const isEmail = email => {
      const emailRegex = 
      /^(([^<>()\[\].,;:\s@"]+(\.[^<>()\[\].,;:\s@"]+)*)|(".+"))@(([^<>()[\].,;:\s@"]+\.)+[^<>()[\].,;:\s@"]{2,})$/i;
      
      return emailRegex.test(email)
    }

    // 비밀번호 유효성 확인
    // 8자 이상, 16자 미만이면서 알파벳, 숫자 및 특수문자는 하나 이상 포함
    const isPassword = password => {
      const passwordRegex = 
      /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8, 16}$/

      return passwordRegex.test(password)
    }
  
    // // 이메일 중복확인
    // const onCheckEmail = event => {
    //   event.preventDefault()
  
    //   let body = {
    //     email: Email
    //   }
  
    //   dispatch(check_email(body))
    //     .then((response) => {
    //       if (response.payload.message !== 'fail') {
    //         alert("이미 존재하는 이메일입니다.")
    //         setEmail('')
    //       } else {
    //         alert("사용 가능한 이메일입니다.")
    //         setCheckEmail(true)
    //       }
    //     })
    // }
  
    // Handlers
    const onEmailHandler = event => {
      setEmail(event.target.value)
    }
  
    const onNicknameHandler = event => {
      setNickname(event.target.value)
    }
    
    const onPasswordHandler = event => {
      setPassword(event.target.value)
    }
    
    const onConfirmPasswordHandler = event => {
      setConfirmPassword(event.target.value)
    }
  
    const onSubmitHandler = event => {
      event.preventDefault()
  
      if (Password !== ConfirmPassword) {
        return alert('비밀번호와 비밀번호 확인은 같아야 합니다.')
      }
  
      // if (!check) {
      //   return alert('닉네임 중복확인을 진행해 주세요.')
      // }
  
      // if (!checkEmail) {
      //   return alert('이메일 중복확인을 진행해 주세요.')
      // }
  
      if (!isEmail(Email)) {
        setEmail('')
        return alert('이메일 형식을 지켜주세요.')
      }

      if (!isPassword(Password)) {
        setPassword('')
        setConfirmPassword('')
        return alert('비밀번호 형식을 지켜랏!')
      }
  
      let body = {
        email: Email,
        nickname: Nickname,
        password: Password,
      }
  
      dispatch(registerUser(body))
        .then(response => {
          // console.log('response', response);
          // console.log('response.payload', response.payload);
        //   if (response.payload.authorized) {
        //     alert('회원가입 성공')
        //     navigate('/')
        //   } else {
        //     setEmail('')
        //     setNickname('')
        //     setPassword('')
        //     setConfirmPassword('')
        //     alert('회원가입 실패')
        //   }
        })
        .catch(error => alert(error))
  
      
    }
    return (
      <div style={{
        display: 'flex', justifyContent: 'center', alignItems: 'center',
        width: '100%', height: '100vh'
      }}>
  
        <img src={logo} alt="/" />
  
        <form style={{display: 'flex', flexDirection: 'column'}}
          onSubmit = {onSubmitHandler}>
  
          <label>Email</label>
          <input type="email" value={Email} onChange = {onEmailHandler} />
          {/* <button onClick={onCheckEmail}>중복확인</button> */}
  
          <label>Nickname</label>
          <input type="text" value={Nickname} onChange = {onNicknameHandler} />
          {/* <button onClick={onCheckNickname}>중복확인</button> */}
  
          <label>Password</label>
          <input type="password" value={Password} onChange = {onPasswordHandler} />
  
          <label>Confirm Password</label>
          <input type="password" value={ConfirmPassword} onChange = {onConfirmPasswordHandler} />
  
          <br />
          <button>회원가입</button>
        </form>
  
        <hr />
        <p>소셜 회원가입 구현할 위치</p>
        
      </div>
    )
  }
  
  export default RegisterPage