import React from 'react';
import {Outlet, NavLink, Form, useLoaderData} from "react-router-dom";
import { axiosAuth } from "../../_actions/axiosAuth";

import ProfileImg from "../../components/commons/ProfileImg";
import { YellowBtn } from "../../components/commons/buttons";
import './Layout.css'

export async function loader ({params}) {
  const [nickname, userSeq] = params.nickname_user_seq.split('@');

  const userData = await axiosAuth.get(`users/profile/${userSeq}`)
    .then(response => response.data)
    .catch(error => console.log(error))

  return [nickname, userSeq, userData];
}


export default function Layout() {
  const [nickname, userSeq, userData] = useLoaderData();
  console.log(userData);
  const isMyPage = nickname === localStorage.getItem('nickname')
  const isArtist = userData.role === 'ROLE_ARTIST'


  return (
    // 얘는 Mypage의 layout임!!! 마이페이지 어디를 가든 변하지 않고 항상 있어야 함 (==사이드바랑 위의 메뉴탭 4개)
    <div className="grid_my-page-layout">
      <div className="my-page__side">
        <ProfileImg url={userData.profileImg} userSeq={userSeq} />
        <h3 id="artist_name">{userData.nickname}</h3>
        <div id="introduction">{userData.artistIntro}</div>

        {/* userSeq가 작가면 sns links 렌더, 아님 말구 */}
        { isArtist ?
          <div id="sns_links">
            <div id="email">이메일 : {userData.email}</div>
            {userData.instagranLink? <div id="instagram">인스타그램 : {userData.instagramLink}</div> : null }
            {userData.twitterLink? <div id="twitter">트위터 : {userData.twitterLink}</div> : null }
            {userData.blogLink? <div id="blog">블로그 : {userData.blogLink}</div> : null }
          </div> : null
        }

        {/* userSeq가 내가 아니면 남의 버튼 렌더링, 나라면 나의 버튼 렌더링 */}
        { isMyPage ?
          <div id="profile_buttons">
            <Form action={'edit_profile'}><YellowBtn type="submit">정보 수정하기</YellowBtn></Form>
            <Form action={'upload'}><YellowBtn type="submit">작품 업로드</YellowBtn></Form>
          </div>
          :
          <div id="profile_buttons">
            <YellowBtn id='follow'>팔로우버튼</YellowBtn>
          </div>
        }
      </div>

      <div className="my-page__content">
        <nav>
          <NavLink to='.' className={({isActive}) => isActive? 'link nav-active' : 'link' } end>작품</NavLink>

          {/* 공지사항은 내 페이지도 아니고 작가도 아니면 아예 링크를 띄우지 말자 */}
          { (!isMyPage && !isArtist)? null : <NavLink to={ 'notices' } className={({isActive}) => isActive? 'link nav-active' : 'link' }>공지사항</NavLink>}
          <NavLink to={ ( isMyPage && !isArtist) ? 'curations/following' : 'curations/mine' } className={({isActive}) => isActive? 'link nav-active' : 'link' } >큐레이션</NavLink>
          {/*<NavLink to='commissions' className={({isActive}) => isActive? 'link nav-active' : 'link' } >커미션</NavLink>*/}
        </nav>

        {/* 여기가 진짜 렌더링 해야하는 곳인데..... */}
        <Outlet />

      </div>

    </div>
  );
}