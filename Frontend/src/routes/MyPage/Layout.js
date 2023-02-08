import React from 'react';
import { Outlet, NavLink, Form } from "react-router-dom";

import ProfileImg from "../../components/commons/ProfileImg";
import './Layout.css'

export default function Layout(props) {
  const user = {
    isArtist: false,
    isMyPage: true
  }

  return (
    // 얘는 Mypage의 layout임!!! 마이페이지 어디를 가든 변하지 않고 항상 있어야 함 (==사이드바랑 위의 메뉴탭 4개)
    <div id="my_page__layout">
      <div id="my_page__profile">
        <ProfileImg src="https://mindlogic-metaverse-face.s3.ap-northeast-2.amazonaws.com/custom/22592-1634653713945.jpeg" />
        <h3 id="artist_name">빠꾸시현</h3>
        <div id="introduction">한 줄 자기소개</div>

        {/* userSeq가 작가면 sns links 렌더, 아님 말구 */}
        { user.isArtist?
          <div id="sns_links">
            <div id="email">이메일</div>
            <div id="instagram">인스타그램</div>
            <div id="twitter">트위터</div>
            <div id="youtube">유튜브</div>
          </div> : null }

          {/* userSeq가 내가 아니면 남의 버튼 렌더링, 나라면 나의 버튼 렌더링 */}
          { user.isMyPage?
            <div id="profile_buttons">
              <button type="submit"><Form action='arts/edit_profile'>정보 수정하기</Form></button>
              <button type="submit"><Form action="arts/upload">작품 업로드</Form></button>
            </div>
            :
            <div id="profile_buttons">
              <button id='follow'>팔로우버튼</button>
            </div>
          }
      </div>

      <div id="my_page__content">
        <nav>
          <NavLink to='arts'>작품</NavLink>

          <NavLink to={ (user.isMyPage && !user.isArtist) ? 'notices/following' : 'notices/mine' }>공지사항</NavLink>
          <NavLink to={ (user.isMyPage && !user.isArtist) ? 'curations/following' : 'curations/mine' }>큐레이션</NavLink>
          <NavLink to='commissions'>커미션</NavLink>
        </nav>

        {/* 여기가 진짜 렌더링 해야하는 곳인데..... */}

        <Outlet />
      </div>

    </div>
  );
}