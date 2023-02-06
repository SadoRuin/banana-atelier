import React from 'react'
import { useState } from 'react'
import { useDispatch } from 'react-redux'
import './SignUpPage.css'
import logo from '../../assets/글씨_250.png'
import { useNavigate } from 'react-router-dom'
import { useForm } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from 'yup';
import '../../components/commons/commons.css'
import { 
  signUpUser,
  check_email,
  check_email_code,
  check_nickname } from '../../_actions/user_action'

function SignUpPage(props) {
  
  const dispatch = useDispatch()
  const navigate = useNavigate()
  
  // 이메일 인증번호 발송 하단 멘트 (인증번호가 발송되었습니다.)
  const [emailValidMessage, SetEmailValidMessage] = useState('')
  
  // 이메일 인증번호 확인 하단 멘트 (인증이 완료되었습니다.)
  const [emailCodeValidMessage, SetEmailCodeValidMessage] = useState('')
  
  // 닉네임 중복체크 완료라면? true로
  const [nicknameCheck, setNicknameCheck] = useState('')

  // 이메일 인증 완료라면? true로
  const [emailCheck, SetEmailCheck] = useState(false)

  // 닉네임 인증 완료라면? true
  const [nicknameValidCheck, SetNicknameValidCheck] = useState(false)

  const [verifyMessage, SetVerifyMessage] = useState('인증번호 발송')

  const [alertMessage, SetAlertMessage] = useState('')

  const formSchema = yup.object({
    nickname: yup
      .string()
      .required('닉네임을 입력해주세요.'),
    email: yup
      .string()
      .required('이메일을 입력해주세요.')
      .email('이메일 형식이 아닙니다.'),
    password: yup
      .string()
      .required('영문, 숫자, 기호 포함 8자리 이상, 16자 이하를 입력하세요.')
      .min(8, '영문, 숫자, 기호 포함 8자리 이상, 16자 이하를 입력하세요.')
      .max(15, '영문, 숫자, 기호 포함 8자리 이상, 16자 이하를 입력하세요.')
      .matches(
        /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,16}$/,
        '영문, 숫자, 기호 포함 8자리 이상, 16자 이하를 입력하세요.'
      ),
    passwordConfirm: yup
      .string()
      .oneOf([yup.ref('password')], '비밀번호가 다릅니다.')
  })
  

  const {
    register,
    handleSubmit,
    // watch,
    formState: { errors },
  } = useForm({
    mode: 'onChange',
    resolver: yupResolver(formSchema),
  });


  const onCheckNickname = () => {
    const nickname = document.querySelector('#nickname');
    let body = {
      nickname: nickname.value
    }
    console.log(body)

    dispatch(check_nickname(body))
    .then((response) => {
      console.log(response);
      if (response.payload.message === "사용가능한 닉네임입니다.") {
        setNicknameCheck('사용가능한 닉네임입니다.')
        SetNicknameValidCheck(true)
      }
    })
    .catch((error) => {
      setNicknameCheck('중복된 닉네임입니다.')
      SetNicknameValidCheck(false)
    })
  }

  const emailVerify = () => {
    SetVerifyMessage('인증번호 재발송')
    const emailValid = document.querySelector('#email')
    console.log(emailValid);
    let body = {
      email: emailValid.value
    }
    console.log('body :', body);
    dispatch(check_email(body))
      .then((response) => {
        console.log('response는', response);
        
        SetEmailValidMessage('인증번호가 발송되었습니다.')

      })
      .catch((error) => { 
        console.log(error) 
        SetEmailValidMessage('중복된 이메일입니다.')
    })
  }

  const VerifyCheck = () => {
    const emailValid = document.querySelector('#email')
    const verifychecknum = document.querySelector('#verifycheck')
    let body = {
      email: emailValid.value,
      code: verifychecknum.value
    }
    dispatch(check_email_code(body))
      .then(response => {
        console.log(response);
        if (response.payload.code === 'success') {
          SetEmailCodeValidMessage('인증이 완료되었습니다.')
          SetEmailCheck(true)
        } else {
          SetEmailCodeValidMessage('잘못된 인증코드입니다.')
          SetEmailCheck(false)
        }
      })
      .catch(error => {
        SetEmailCodeValidMessage('잘못된 인증코드입니다.')
        SetEmailCheck(false)
      })
  }


  const onSubmit = data => {
    let body = {
      email: data.email,
      nickname: data.nickname,
      password: data.password
    }
    if (!nicknameValidCheck) {
      SetAlertMessage('닉네임 중복체크를 하세요.')
    } else if (!emailCheck) {
      SetAlertMessage('인증코드 확인을 하세요.')
    } else {
      dispatch(signUpUser(body))
        .then(response => {
          console.log('response : ', response);
          alert('회원가입이 완료되었습니다.')
          navigate("/login")
        })
        .catch(error => {
          console.log(error);
          alert(error)
          console.log('signUp dispatch 실패');
      navigate('/login')
    })}
    
  }



  return (
    <div id="sign_up_container" style={{marginTop: '10%'}}>
      <img src={logo} alt="/" id="logo_img" />
      <form onSubmit={handleSubmit(onSubmit)} style={{width: '27%'}}>
        <div style={{
          marginTop: '50px',
          width: '100%'
          }}>
          <div>
            <label htmlFor="nickname">닉네임</label> <br/>
          </div>
          <div style={{display: 'flex', flexWrap: 'wrap', justifyContent: 'space-between', marginTop: '8px'}}>
            <input className='input_box' name="nickname" placeholder="nickname" {...register('nickname')} style={{width: '60%'}} id="nickname"/>
            <button className='yellow_button' onClick={onCheckNickname}>중복확인</button>
          </div>
          <div style={{
          width: '100%',
          fontSize: '13px'
        }}>
            {nicknameCheck}
          </div>
        </div>

        <div style={{
          marginTop: '15px',
          width: '100%'
          }}>
          <div>
            <label htmlFor="email">이메일</label> <br/>
          </div>
          <div style={{display: 'flex', flexWrap: 'wrap', justifyContent: 'space-between', marginTop: '8px'}}>
            <input className='input_box' id="email" name="email" placeholder="email" {...register('email')} style={{width: '60%'}}/>
            <button onClick={emailVerify} className='yellow_button'>{verifyMessage}</button>
            {/* {errors.email && <p style={{width: '100%'}}>{errors.email.message}</p>} */}
          </div>
        </div>
        <div style={{
          width: '100%',
          fontSize: '13px'
        }}>
          { emailValidMessage }
        </div>

        <div style={{display: 'flex', flexWrap: 'wrap', justifyContent: 'space-between', marginTop: '10px', width: '100%'}}>
          <input className='input_box' type="text" placeholder='인증번호를 입력해주세요' style={{width: '60%'}} id="verifycheck"/>
          <button onClick={VerifyCheck} className='yellow_button'>인증번호 확인</button>
        </div>
        <div style={{fontSize: '10px'}}>
          { emailCodeValidMessage }
        </div>

        <div style={{marginTop: '15px', width: "100%"}}>
          <label htmlFor="password" style={{marginTop: '15px'}}>비밀번호</label> <br/>
          <input
            className='input_box'
            type="password"
            name="password"
            placeholder="password"
            {...register('password')}
            style={{marginTop: '8px', width: '100%', boxSizing: 'border-box'}}
          />
        </div>
        <div style={{width: "100%", boxSizing: 'border-box', fontSize: '10px'}}>
          {errors.password && <p>{errors.password.message}</p>}
        </div>

        <div style={{marginTop: '15px'}}>
          <label htmlFor="passwordConfirm">비밀번호 확인</label> <br/>
          <input
            className='input_box'
            type="password"
            name="passwordConfirm"
            placeholder="password"
            {...register('passwordConfirm')}
            style={{marginTop: '8px', width: '100%', boxSizing: 'border-box'}}
          />
        </div>
        <div style={{fontSize: '10px'}}>
          {errors.passwordConfirm && <p>{errors.passwordConfirm.message}</p>}
        </div>

        <div>
          {/* <input type="submit" vaule="회원가입하기" disabled={errors || watch()} /> */}
          <button className='yellow_button' style={{width: '100%', marginTop: '15px'}}>회원가입</button>
          {/* <button disabled={!emailCheck || !nicknameValidCheck} className='yellow_button' style={{width: '100%', marginTop: '15px'}}>회원가입</button> */}
          {/* <button disabled={!emailCheck || !nicknameCheck} >회원가입</button> */}
          <div>
            {alertMessage}
          </div>
        </div>
      </form>
    </div>
  );
}

export default SignUpPage