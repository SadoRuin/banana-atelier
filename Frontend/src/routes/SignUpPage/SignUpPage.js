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
  check_nickname } from '../../_actions/user_action'

  function SignUpPage(props) {
    
    const dispatch = useDispatch()
    const navigate = useNavigate()

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
        {/* <form onSubmit={onSubmit}> */}
          <label htmlFor="nickname">닉네임</label>
          <input name="nickname" placeholder="nickname" {...register('nickname')} />

          <label htmlFor="email">이메일</label>
          <input name="email" placeholder="email" {...register('email')} />
          {errors.email && <p>{errors.email.message}</p>}

          <label htmlFor="password">비밀번호</label>
          <input
            type="password"
            name="password"
            placeholder="password"
            {...register('password')}
          />
          {errors.password && <p>{errors.password.message}</p>}

          <label htmlFor="passwordConfirm">비밀번호 확인</label>
          <input
            type="password"
            name="passwordConfirm"
            placeholder="password"
            {...register('passwordConfirm')}
          />
          {errors.passwordConfirm && <p>{errors.passwordConfirm.message}</p>}

          {/* <input type="submit" vaule="회원가입하기" disabled={errors || watch()} /> */}
          <input type="submit" value="회원가입" />
          <button>회원가입</button>
        </form>
      </div>
    );


///////////////////////////////////2
  //     let body = {
  //       email: Email,
  //       nickname: Nickname,
  //       password: Password,
  //     }
  
  //     dispatch(signUpUser(body))
  //       .then(response => {
  //         // console.log('response', response);
  //         // console.log('response.payload', response.payload);
  //         // if (response.payload.authorized) {
  //         //   alert('회원가입 성공')
  //         //   navigate('/')
  //         // } else {
  //         //   setEmail('')
  //         //   setNickname('')
  //         //   setPassword('')
  //         //   setConfirmPassword('')
  //         //   alert('회원가입 실패')
  //         // }
  //         console.log('response :', response.payload);
  //         alert(response.payload)
  //         navigate("/login")
  //       })
  //       .catch(error => alert(error))
///////////////////



    // const dispatch = useDispatch()
  //   const location = useLocation()
  //   const navigate = useNavigate()
  
  //   const [content, setContent] = useState('')
  //   const [Nickname, setNickname] = useState('')
  //   const [Email, setEmail] = useState('')
  //   const [EmailAuth, setEmailAuth] = useState('')
  //   const [Password, setPassword] = useState('')
  //   const [ConfirmPassword, setConfirmPassword] = useState('')
    
  //   const [check, setCheck] = useState(false)
  //   const [checkEmail, setCheckEmail] = useState(false)
  
  //   // // 닉네임 중복확인
  //   // const onCheckNickname = event => {
  //   //   event.preventDefault()
  
  //   //   let body = {
  //   //     nickname: Nickname
  //   //   }
  //   //   dispatch(check_nickname(body))
  //   //     .then((response) => {
  //   //       if (response.payload.message !== 'fail') {
  //   //         alert("이미 존재하는 닉네임입니다.")
  //   //         setNickname('')
  //   //       } else {
  //   //         alert("사용 가능한 닉네임입니다.")
  //   //         setCheck(true)
  //   //       }
  //   //     })
  //   // }
  
  //   // 이메일 유효성 확인
  //   const isEmail = email => {
  //     const emailRegex = 
  //     /^(([^<>()\[\].,;:\s@"]+(\.[^<>()\[\].,;:\s@"]+)*)|(".+"))@(([^<>()[\].,;:\s@"]+\.)+[^<>()[\].,;:\s@"]{2,})$/i;
      
  //     return emailRegex.test(email)
  //   }

  //   // 비밀번호 유효성 확인
  //   // 8자 이상, 16자 미만이면서 알파벳, 숫자 및 특수문자는 하나 이상 포함
  //   const isPassword = password => {
  //     const passwordRegex = 
  //     /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,16}$/
  //     // /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8, 16}$/

  //     return passwordRegex.test(password)
  //   }
  
  //   // // 이메일 중복확인
  //   // const onCheckEmail = event => {
  //   //   event.preventDefault()
  
  //   //   let body = {
  //   //     email: Email
  //   //   }
  
  //   //   dispatch(check_email(body))
  //   //     .then((response) => {
  //   //       if (response.payload.message !== 'fail') {
  //   //         setContent('사용 가능한 이메일입니다.')
  //   //         setEmail('')
  //   //       } else {
  //   //         setContent('이미 사용중인 이메일입니다.')
  //   //         setCheckEmail(true)
  //   //       }
  //   //     })
  //   // }
  
  //   // Handlers
  //   const onNicknameHandler = event => {
  //     setNickname(event.target.value)
  //   }
    
  //   const onEmailHandler = event => {
  //     setEmail(event.target.value)
  //   }
    
  //   const onEmailAuthHandler = event => {
  //     setEmailAuth(event.target.value)
  //   }

  //   const onPasswordHandler = event => {
  //     setPassword(event.target.value)
  //   }
    
  //   const onConfirmPasswordHandler = event => {
  //     setConfirmPassword(event.target.value)
  //   }
  
  //   const onSubmitHandler = event => {
  //     event.preventDefault()
  
  //     if (Password !== ConfirmPassword) {
  //       return alert('비밀번호와 비밀번호 확인은 같아야 합니다.')
  //     }
  
  //     // if (!check) {
  //     //   return alert('닉네임 중복확인을 진행해 주세요.')
  //     // }
  
  //     // if (!checkEmail) {
  //     //   return alert('이메일 중복확인을 진행해 주세요.')
  //     // }
  
  //     if (!isEmail(Email)) {
  //       setEmail('')
  //       return alert('이메일 형식을 지켜주세요.')
  //     }

  //     if (!isPassword(Password)) {
  //       setPassword('')
  //       setConfirmPassword('')
  //       console.log(Password);
  //       return alert('비밀번호는 알파벳, 숫자 및 특수문자가 포함된 8자 이상 16자 이하로 설정해주세요.')
  //     }
  
  //     let body = {
  //       email: Email,
  //       nickname: Nickname,
  //       password: Password,
  //     }
  
  //     dispatch(signUpUser(body))
  //       .then(response => {
  //         // console.log('response', response);
  //         // console.log('response.payload', response.payload);
  //         // if (response.payload.authorized) {
  //         //   alert('회원가입 성공')
  //         //   navigate('/')
  //         // } else {
  //         //   setEmail('')
  //         //   setNickname('')
  //         //   setPassword('')
  //         //   setConfirmPassword('')
  //         //   alert('회원가입 실패')
  //         // }
  //         console.log('response :', response.payload);
  //         alert(response.payload)
  //         navigate("/login")
  //       })
  //       .catch(error => alert(error))
  
      
  //   }
  //   return (
  //     <div style={{
  //       display: 'flex', justifyContent: 'center', alignItems: 'center',
  //       width: '100%', height: '100vh'
  //     }}>
  
  //       <img src={logo} alt="/" />
  
  //       <form onSubmit = {onSubmitHandler}>
  
  //         <label>닉네임</label>
  //         <input type="text" value={Nickname} onChange = {onNicknameHandler} />
  //         <button>중복확인</button>
  //         {/* <button onClick={onCheckNickname}>중복확인</button> */}
  
  //         <label>이메일</label>
  //         <input type="email" value={Email} onChange = {onEmailHandler} />
  //         <button>인증메일 발송</button>
  //         {/* <button onClick={onCheckEmail}>인증메일 발송</button> */}
  //         <p>인증번호 확인을 위해 사용 중인 이메일을 입력하세요.</p>
  //         <p>{ content }</p>
  
  //         <input type="text" value={EmailAuth} onChange = {onEmailAuthHandler} />
  //         <button>인증번호 확인</button>

  //         <label>비밀번호</label>
  //         <input type="password" value={Password} onChange = {onPasswordHandler} />
  
  //         <label>비밀번호 확인</label>
  //         <input type="password" value={ConfirmPassword} onChange = {onConfirmPasswordHandler} />
  
  //         <br />
  //         <button>회원가입</button>
  //       </form>
        
  //     </div>
  //   )
  }
  
  export default SignUpPage