import React from 'react'
import './Footer.css'
import logo from '../../../assets/가로글씨X_700.png'

function Footer() {
  return (
    <footer>
      <img src={logo} alt="logo" width='100px' />

      <div>
        @SSAFY 8기 A108
      </div>
    </footer>
  )
}

export default Footer