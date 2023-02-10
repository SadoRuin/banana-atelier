import React from 'react';
import {NavLink, Outlet} from "react-router-dom";

import '../../index.css'

function ArtsRoot() {

  return (
    <div>
      <NavLink to='.' className={({isActive}) => isActive? 'link nav-active' : 'link' } >내 작업</NavLink><br/>
      <NavLink to='favorite' className={({isActive}) => isActive? 'link nav-active' : 'link' } >좋아하는 작품</NavLink><br/>

      {/*내 소장작품은 내 페이지일 때만 렌더링하기 */}
      <NavLink to='owns' className={({isActive}) => isActive? 'link nav-active' : 'link' }>내 소장 작품</NavLink>
      <Outlet />
    </div>
  );
}

export default ArtsRoot;