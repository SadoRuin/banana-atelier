import React from 'react'
import { useNavigate, NavLink, Link } from 'react-router-dom'
import './NavBar.css'
import '../../../index.css'
import logo from '../../../assets/글씨X_470.png'
import ProfileImg from "../../../components/commons/ProfileImg";

import { YellowBtn, WhiteBtn } from '../../../components/commons/buttons'

function NavBar() {
  const navigate = useNavigate();

  return (
    <nav id="nav_bar">
      <img src={logo} alt="logo" style={{cursor: 'pointer'}} onClick={event => { navigate("/")}} height='50px'/>

      <div className="menu_bar">
        <NavLink className={({isActive}) => isActive? 'navLink navActive' : 'navLink'} to="/arts">작품</NavLink>
        <NavLink className={({isActive}) => isActive? 'navLink navActive' : 'navLink'} to="/curations">큐레이션</NavLink>
        <NavLink className={({isActive}) => isActive? 'navLink navActive' : 'navLink'} to="/commissions">커미션</NavLink>
      </div>

      <div className="search_bar">
        <input type="text" id="search" name='search' placeholder='검색하기'/>
      </div>

      {/* 로그인 성공 시 */}
      <div>
        <Link className="Link" to="mypage/arts">나의 아뜰리에</Link>
        <button>공지사항 아이콘</button>
        <ProfileImg height="30px" width="30px" />
      </div>

      {/* 로그인 안했을 시*/}
      <div>
        <WhiteBtn><Link className="Link" to="/login">로그인</Link></WhiteBtn>
        <YellowBtn><Link className="Link" to="/signup">회원가입</Link></YellowBtn>
      </div>
    </nav>
  )
}

export default NavBar
