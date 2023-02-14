import React from 'react';
import {Outlet, NavLink, Form, useLoaderData, redirect} from "react-router-dom";
import { axiosAuth, axiosReissue } from "../../_actions/axiosAuth";
import { useState } from 'react';
import ProfileImg from "../../components/commons/ProfileImg";
import { YellowBtn } from "../../components/commons/buttons";
import './Layout.css'

export async function loader ({params}) {
  const [nickname, userSeq] = params.nickname_user_seq.split('@');

  const userData = await axiosAuth.get(`users/profile/${userSeq}`)
    .then(response => response.data)
    .catch((error) => error.response.status)
  console.log(userData);

  const likeList = await axiosAuth.get("users/follow")
    .then(response => response.data)
    .catch(() => null)

  // 로그아웃 된 경우
  if(userData === 401) {
    console.log('권한이 없습니다');
    return redirect("/login");
  }
  // 사용자를 찾을 수 없는 경우(userSeq가 없는 경우)
  if(userData === 404) {
    throw new Error ("", {
      status: 404,
      message: "사용자를 찾을 수 없습니다",
    });
  }
  // userSeq를 이용해 사용자를 받아왔지만, 앞의 nickname이 다른 경우
  if (userData.nickname !== nickname) {
    throw new Error("", {
      status: 404,
      statusText: "사용자를 찾을 수 없습니다",
    });
  }
  return [nickname, userSeq, userData, likeList];
}



export default function Layout() {
  const [nickname, userSeq, userData, likeList] = useLoaderData();
  const isMyPage = userSeq === localStorage.getItem('userSeq');
  const isArtist = userData.role === 'ROLE_ARTIST'
  
  // nickname 선언만 해서 빌드 오류날까봐 한 것. 삭제해도 괜찮음
  console.log(nickname)
  
  let wonderValue = likeList?.find(like => +like === +userSeq) || false
  const [wonder, setWonder] = useState(wonderValue)

  let content = !wonder? "팔로우": "팔로우 취소"

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
              <Form action={'edit_profile'}><button type="submit">정보 수정하기</button></Form>
              <Form action={'upload'}><button type="submit">작품 업로드</button></Form>
              <Form action={'curation_register'}><button type="submit">큐레이션 등록</button></Form>
            </div>
            :
            <div id="profile_buttons">
              <form onSubmit={event => {
                event.preventDefault()

                if (!wonder) {
                  let body = {"seq": +userSeq}
                  axiosReissue()
                  axiosAuth.post("users/follow", body)
                    .then(response => response)
                } else if (wonder) {
                  let body = {"seq": +userSeq}
                  axiosReissue()
                  axiosAuth.delete("users/follow", {data: body})
                    .then(response => response)
                }
                setWonder(prev=>!prev)
              }}>
                <YellowBtn id='follow'>{content}</YellowBtn>
              </form>
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
        {/* 자식에게 props 전달 가능함! context를 이용한다. */}
        <Outlet context={[isMyPage, isArtist]} />

      </div>

    </div>
  );
}