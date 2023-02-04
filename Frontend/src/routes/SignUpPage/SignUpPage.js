import React from 'react'
import { useState } from 'react'
import { useDispatch } from 'react-redux'
import logo from '../../assets/글씨_250.png'
import { useLocation, useNavigate } from 'react-router-dom'
import { useForm } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from 'yup';
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
    const [nicknameCheck, setNicknameCheck] = useState(false)

    // 이메일 인증 완료라면? true로
    const [emailCheck, SetEmailCheck] = useState(false)

    const [verifyMessage, SetVerifyMessage] = useState('인증번호 발송')

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
      watch,
      formState: { errors },
    } = useForm({
      mode: 'onChange',
      resolver: yupResolver(formSchema),
    });


    // const onCheckNickname = () => {
    //   const nickname = document.querySelector('#nickname');
    //   let body = {
    //     nickname: nickname.value
    //   }
    //   console.log(body)

    //   dispatch(check_nickname(body))
    //   .then((response) => {
    //     console.log(response);
    //     setNicknameCheck(true)
    //   })
      
    // }

    const emailVerify = () => {
      SetVerifyMessage('인증번호 재발송')
      const emailValid = document.querySelector('#email')
      let body = {
        email: emailValid.value
      }
      console.log('body :', body);
      dispatch(check_email(body))
        .then((response) => {
          console.log('response는', response);
          if (response.payload === '사용가능한 이메일입니다.') {
          SetEmailValidMessage('인증번호가 발송되었습니다.')
          } else {
            SetEmailValidMessage('중복된 이메일입니다.')
          }
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
          if (response.payload === '인증되었습니다.') {
            SetEmailCodeValidMessage('인증이 완료되었습니다.')
            SetEmailCheck(true)
          } else {
            SetEmailCodeValidMessage('잘못된 인증코드입니다.')
          }
        })
        .catch(error => {
          SetEmailCodeValidMessage('잘못된 인증코드입니다.')
        })
    }


    const onSubmit = data => {
      let body = {
        email: data.email,
        nickname: data.nickname,
        password: data.password
      }
      dispatch(signUpUser(body))
        .then(response => {
          console.log('response : ', response);
          alert(response.payload.data)
          navigate("/login")
        })
        .catch(error => {
          console.log(error);
          alert(error)
          console.log('signUp dispatch 실패');
        })
    }


    return (
      <div>
        <img src={logo} alt="/" />
        <form onSubmit={handleSubmit(onSubmit)}>
          <label htmlFor="nickname">닉네임</label>
          <input id="nickname" name="nickname" placeholder="nickname" {...register('nickname')} />
          {/* <button htmlFor="nickname" onClick={onCheckNickname}>중복확인</button> */}

          <br />

          <label htmlFor="email">이메일</label>
          <input id="email" name="email" placeholder="email" {...register('email')} />
          {errors.email && <p>{errors.email.message}</p>}
          <button onClick={emailVerify}>{verifyMessage}</button>
          
          { emailValidMessage }
          <br />

          <input id="verifycheck" type="text" placeholder='인증번호를 입력해주세요' />
          <button onClick={VerifyCheck}>인증번호 확인</button>
          
          { emailCodeValidMessage }
          <br />

          <label htmlFor="password">비밀번호</label>
          <input
            type="password"
            name="password"
            placeholder="password"
            {...register('password')}
          />
          {errors.password && <p>{errors.password.message}</p>}

          <br />

          <label htmlFor="passwordConfirm">비밀번호 확인</label>
          <input
            type="password"
            name="passwordConfirm"
            placeholder="password"
            {...register('passwordConfirm')}
          />
          {errors.passwordConfirm && <p>{errors.passwordConfirm.message}</p>}

          <br />

          {/* <input type="submit" vaule="회원가입하기" disabled={errors || watch()} /> */}
          <button disabled={!emailCheck} >회원가입</button>
          {/* <button disabled={!emailCheck || !nicknameCheck} >회원가입</button> */}
        </form>
      </div>
    );
  }
  
  export default SignUpPage