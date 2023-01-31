import React from 'react'
import { useNavigate, NavLink } from 'react-router-dom'
import logo from '../../assets/글씨X_130.png'
import profileLogo from '../../assets/프로필4.png'

function NavBar() {
  const navigate = useNavigate()
  return (
    <nav>


      <div>
        <img src={logo} alt="" onClick={event => {
          event.preventDefault()
          navigate("/")
        }}/>
      </div>

      <div>
        <NavLink to="/arts">
            작품</NavLink>
      </div>

      <div>
        <NavLink to="/curations">큐레이션</NavLink>
      </div>

      <div>
        <NavLink to="/commissions">커미션</NavLink>
      </div>

      <div>
        <label htmlFor="search"></label>
        <input type="text" name='search' placeholder='search'/>
      </div>

      <div>
        <button>나의 아뜰리에</button>
      </div>

      <div>
        <button>공지사항 아이콘</button>
      </div>

      <div>
        <img src={profileLogo} alt="" onClick={event => {
          event.preventDefault()
        }}/>
      </div>

      <div>
        <NavLink to="/login">로그인</NavLink>
      </div>

      <div>
        <NavLink to="/register">회원가입</NavLink>
      </div>
      
    </nav>
  )
}

export default NavBar
