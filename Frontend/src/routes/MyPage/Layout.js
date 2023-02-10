import React from 'react';
import {Outlet, NavLink, Form, useLoaderData} from "react-router-dom";
import axios from 'axios';

import ProfileImg from "../../components/commons/ProfileImg";
import './Layout.css'

export async function loader ({params}) {
  const [nickname, userSeq] = params.nickname_user_seq.split('@');
  const TOKEN = localStorage.getItem('token')
  const userData = await axios.get(`https://i8a108.p.ssafy.io/api/users/profile/${userSeq}`,
    {
      headers: {
        Authorization: `Bearer ${TOKEN}`
      }
    }
  )
    // const artData = await customAxios().get(`arts/detail/${+artSeq}`)
    .then(response => response.data)
    .catch(error => console.log(error))

  return [nickname, userSeq, userData];
}


export default function Layout() {
  const [nickname, userSeq, userData] = useLoaderData();
  console.log(userData);

  const isMyPage = nickname === localStorage.getItem('nickname')
  const isArtist = userData.role === 'USER_ARTIST'

  return (
    // 얘는 Mypage의 layout임!!! 마이페이지 어디를 가든 변하지 않고 항상 있어야 함 (==사이드바랑 위의 메뉴탭 4개)
    <div id="my_page__layout">
      <div id="my_page__profile">
        <ProfileImg url={userData.profileImg} userSeq={userSeq} />
        <h3 id="artist_name">{userData.nickname}</h3>
        <div id="introduction">한 줄 자기소개.. 아직 구현 안된듯</div>

        {/* userSeq가 작가면 sns links 렌더, 아님 말구 */}
        { isArtist ?
          <div id="sns_links">
            <div id="email">이메일</div>
            <div id="instagram">인스타그램</div>
            <div id="twitter">트위터</div>
            <div id="youtube">유튜브</div>
          </div> : null }

          {/* userSeq가 내가 아니면 남의 버튼 렌더링, 나라면 나의 버튼 렌더링 */}
          { isMyPage ?
            <div id="profile_buttons">
              <button type="submit"><Form>정보 수정하기</Form></button>
              <button type="submit"><Form>작품 업로드</Form></button>
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

          <NavLink to={ ( isMyPage && !isArtist) ? 'notices/following' : 'notices/mine' }>공지사항</NavLink>
          <NavLink to={ ( isMyPage && !isArtist) ? 'curations/following' : 'curations/mine' }>큐레이션</NavLink>
          <NavLink to='commissions'>커미션</NavLink>
        </nav>

        {/* 여기가 진짜 렌더링 해야하는 곳인데..... */}
        <Outlet />

      </div>

    </div>
  );
}