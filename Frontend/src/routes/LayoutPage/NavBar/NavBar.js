import React from 'react'
import { useNavigate, NavLink, Link } from 'react-router-dom'
import { useSelector } from 'react-redux'
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faBell } from "@fortawesome/free-regular-svg-icons";

import ProfileImg from "../../../components/commons/ProfileImg";
import { YellowBtn, WhiteBtn } from '../../../components/commons/buttons'
import './NavBar.css'
import '../../../index.css'
import logo from '../../../assets/글씨X_470.png'

function NavBar() {

  const userSeq = localStorage.getItem('userSeq');
  const profileImg = localStorage.getItem('profileImg');
  const nickname = localStorage.getItem('nickname')
  const navigate = useNavigate();
  const loginWonder = useSelector(state => state.user.login_status);

  console.log("navbar에서 loginWonder", loginWonder)
  let content = null

  if (loginWonder) {
    content =
      <div className="right-menu-bar">
        <Link className="link link-bold" to={`${nickname}@${userSeq}`}><YellowBtn>나의 아뜰리에</YellowBtn></Link>
        <div style={{ color: '#F9D923', fontSize: '20px' }}><FontAwesomeIcon icon={ faBell }/></div>
        <ProfileImg height="30px" width="30px" url={profileImg} userSeq={userSeq}/>
      </div>
  } else if (!loginWonder) {
    content =
      <div className="right-menu-bar">
        <Link className="link link-bold" to="/login"><WhiteBtn>로그인</WhiteBtn></Link>
        <Link className="link link-bold" to="/signup"><YellowBtn>회원가입</YellowBtn></Link>
      </div>
  }
  return (
    <nav id="nav_bar">

      <div className="left-menu-bar">
        <img src={logo} alt="logo" style={{cursor: 'pointer'}} onClick={ navigate("/")} height='50px' className="logo"/>
        <NavLink className={({isActive}) => isActive? 'link nav-active' : 'link' } to="/arts">작품</NavLink>
        <NavLink className={({isActive}) => isActive? 'link nav-active' : 'link' } to="/curations">큐레이션</NavLink>
        <NavLink className={({isActive}) => isActive? 'link nav-active' : 'link' } to="/commissions">커미션</NavLink>
      </div>

      <div className="search_bar">
        <input type="text" id="search" name='search' placeholder='검색하기'/>
      </div>

      { content }
      
    </nav>
  )
}

export default NavBar
