import React from 'react'
import { useNavigate, NavLink, Link } from 'react-router-dom'
import logo from '../../../assets/글씨X_130.png'
import ProfileImg from "../../../components/ProfileImg";

function NavBar() {
  const navigate = useNavigate();

  return (
    <nav>
      <img src={logo} alt="logo" onClick={event => { navigate("/")} }/>

      <div className="menu_bar">
        <NavLink to="/arts">작품</NavLink>
        <NavLink to="/curations">큐레이션</NavLink>
        <NavLink to="/commissions">커미션</NavLink>
      </div>

      <div className="search_bar">
        <input type="text" id="search" name='search' placeholder='검색하기'/>
      </div>

      {/* 로그인 성공 시 */}
      <div>
        <Link to="mypage/arts">나의 아뜰리에</Link>
        <button>공지사항 아이콘</button>
        <ProfileImg src="https://mindlogic-metaverse-face.s3.ap-northeast-2.amazonaws.com/custom/22592-1634653713945.jpeg" height="30px" />
      </div>

      {/* 로그인 안했을 시*/}
      <div>
        <NavLink to="/login">로그인</NavLink>
        <NavLink to="/signup">회원가입</NavLink>
      </div>
    </nav>
  )
}

export default NavBar
