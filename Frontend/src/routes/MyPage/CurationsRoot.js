import React from 'react';
import { NavLink, Outlet } from "react-router-dom";

function CurationsRoot(props) {
  const user = {
    isArtist: false,
    isMyPage: true
  }

  return (
    <div>
      { (user.isMyPage && user.isArtist) ?
        <NavLink to="mine">나의 큐레이션</NavLink>
        :
        null
      }
      <NavLink to="following">팔로잉 작가의 큐레이션</NavLink>
      <NavLink to="bookmark">찜해둔 큐레이션</NavLink>

      <Outlet />
    </div>
  );
}

export default CurationsRoot;