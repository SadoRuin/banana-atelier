import React from 'react';
import { Outlet, NavLink, Form } from "react-router-dom";

import ProfileImg from "../../components/ProfileImg";

export default function Layout(props) {
  const user = {
    isArtist: false,
    isMyPage: true
  }


  return (
    // 얘는 Mypage의 layout임!!! 마이페이지 어디를 가든 변하지 않고 항상 있어야 함 (==사이드바랑 위의 메뉴탭 4개)
    <div>
      <h1>MyPage</h1>

      <h3>프로필 사이드바</h3>
      <div className="profile_bar">
        <ProfileImg src="https://mindlogic-metaverse-face.s3.ap-northeast-2.amazonaws.com/custom/22592-1634653713945.jpeg" />
        <div className="artistName">빠꾸시현</div>
        <div className="introduction">한 줄 자기소개</div>

        {/* userSeq가 작가면 sns links 렌더, 아님 말구 */}
        { user.isArtist?
          <div className="snsLinks">
            <div className="email">이메일</div>
            <div className="instagram">인스타그램</div>
            <div className="twitter">트위터</div>
            <div className="youtube">유튜브</div>
          </div> : null }

          {/* userSeq가 내가 아니면 남의 버튼 렌더링, 나라면 나의 버튼 렌더링 */}
          { user.isMyPage?
            <div className="profile_buttons">
              {/*<Link to='edit_profile'><button className='edit_profile'>정보 수정하기</button></Link>*/}
              {/*<Link to='upload'><button className='upload_art'>작품 업로드</button></Link>*/}
              <Form action='edit_profile'><button type="submit">정보 수정하기</button></Form>
              <Form action="upload"><button type="submit">작품 업로드</button></Form>
            </div>
            :
            <div className="profile_buttons">
              <button className='follow'>팔로우버튼</button>
            </div>
          }
      </div>

      <hr/>

      <nav>
        <NavLink to='arts'>작품</NavLink>

        <NavLink to={ (user.isMyPage && !user.isArtist) ? 'notices/following' : 'notices/mine' }>공지사항</NavLink>
        <NavLink to={ (user.isMyPage && !user.isArtist) ? 'curations/following' : 'curations/mine' }>큐레이션</NavLink>
        <NavLink to='commissions'>커미션</NavLink>
      </nav>

      {/* 여기가 진짜 렌더링 해야하는 곳인데..... */}
      <Outlet />

    </div>
  );
}