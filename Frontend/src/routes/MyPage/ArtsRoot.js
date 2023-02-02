import React from 'react';

import {NavLink, Outlet} from "react-router-dom";

function ArtsRoot(props) {

  return (
    <div>
      <NavLink to='.'>내 작업</NavLink><br/>
      <NavLink to='favorite'>좋아하는 작품</NavLink><br/>

      {/*내 소장작품은 내 페이지일 때만 렌더링하기 */}
      <NavLink to='owns'>내 소장 작품</NavLink>
      <Outlet />
    </div>
  );
}

export default ArtsRoot;