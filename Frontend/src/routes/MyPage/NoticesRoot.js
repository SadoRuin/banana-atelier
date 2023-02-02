import React from 'react';
import {NavLink, Outlet } from "react-router-dom";

function NoticesRoot(props) {
  const user = {
    isArtist: true,
    isMyPage: false
  }

  return (
    <div>
      { (user.isMyPage && user.isArtist) ?
        <>
          <NavLink to='mine'>나의 공지</NavLink>
          <NavLink to='following'>팔로잉 작가의 공지</NavLink>
        </> :
        null
      }

      <Outlet />
    </div>
  );
}

export default NoticesRoot;