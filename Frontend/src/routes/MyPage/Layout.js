import React, { useState } from 'react';
import { Outlet, NavLink, Form, useLoaderData, redirect, useNavigate } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faEnvelope, faHouse } from "@fortawesome/free-solid-svg-icons";

import { axiosAuth, axiosReissue } from "../../_actions/axiosAuth";
import ProfileImg from "../../components/commons/ProfileImg";
import { YellowBtn, RedBtn } from "../../components/commons/buttons";
import { landingRenderingLogout } from '../../_actions/user_action';
import { logoutCode } from '../../_actions/user_action';

import './Layout.css'
import { useDispatch } from 'react-redux';


export async function loader ({params}) {
  const [nickname, userSeq] = params.nickname_user_seq.split('@');

  // 마이페이지 주인의 정보 가져오기
  const userData = await axiosAuth.get(`users/profile/${userSeq}`)
    .then(response => response.data)
    .catch((error) => error.response.status)
  console.log(userData);

  // 이 주인이 팔로우한 사람 목록 가져오기
  const userFollow = await axiosAuth.get("users/follow")
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
  return [userSeq, userData, userFollow];
}



export default function Layout() {
  const [userSeq, userData, userFollow] = useLoaderData();
  const isMyPage = userSeq === localStorage.getItem('userSeq');
  const isArtist = userData.role === 'ROLE_ARTIST'
  const dispatch = useDispatch()
  const navigate = useNavigate()

  const handleLogout = event => {
    event.preventDefault()
    localStorage.clear()
    dispatch(landingRenderingLogout())
    dispatch(logoutCode())
    navigate('/')
  }

  let isFollow = userFollow?.find(like => +like === +userSeq) || false
  const [wonder, setWonder] = useState(isFollow)

  let content = !wonder? "팔로우": "팔로우 취소"

  return (
    // 얘는 Mypage의 layout임!!! 마이페이지 어디를 가든 변하지 않고 항상 있어야 함 (==사이드바랑 위의 메뉴탭 4개)
    <div className="grid__my-page">
      <div className="my-page__side">
        <ProfileImg url={userData.profileImg} userSeq={userSeq} />
        <h3 className="my-page__profile">{userData.nickname}</h3>
        <div className="my-page__introduction">{userData.artistIntro}</div>

        {/* userSeq가 작가면 sns links 렌더, 아님 말구 */}
        { isArtist &&
          <div className="my-page__sns-links">
            <div id="email"><FontAwesomeIcon icon={faEnvelope} style={{fontSize: "13px"}}/>  {userData.email}</div>
            {userData.blogLink && <div id="blog"><FontAwesomeIcon icon={faHouse} style={{fontSize: "13px"}}/>  {userData.blogLink}</div> }
            {userData.instagranLink && <div id="instagram"><svg style={{width: "15px"}} xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"><path d="M224.1 141c-63.6 0-114.9 51.3-114.9 114.9s51.3 114.9 114.9 114.9S339 319.5 339 255.9 287.7 141 224.1 141zm0 189.6c-41.1 0-74.7-33.5-74.7-74.7s33.5-74.7 74.7-74.7 74.7 33.5 74.7 74.7-33.6 74.7-74.7 74.7zm146.4-194.3c0 14.9-12 26.8-26.8 26.8-14.9 0-26.8-12-26.8-26.8s12-26.8 26.8-26.8 26.8 12 26.8 26.8zm76.1 27.2c-1.7-35.9-9.9-67.7-36.2-93.9-26.2-26.2-58-34.4-93.9-36.2-37-2.1-147.9-2.1-184.9 0-35.8 1.7-67.6 9.9-93.9 36.1s-34.4 58-36.2 93.9c-2.1 37-2.1 147.9 0 184.9 1.7 35.9 9.9 67.7 36.2 93.9s58 34.4 93.9 36.2c37 2.1 147.9 2.1 184.9 0 35.9-1.7 67.7-9.9 93.9-36.2 26.2-26.2 34.4-58 36.2-93.9 2.1-37 2.1-147.8 0-184.8zM398.8 388c-7.8 19.6-22.9 34.7-42.6 42.6-29.5 11.7-99.5 9-132.1 9s-102.7 2.6-132.1-9c-19.6-7.8-34.7-22.9-42.6-42.6-11.7-29.5-9-99.5-9-132.1s-2.6-102.7 9-132.1c7.8-19.6 22.9-34.7 42.6-42.6 29.5-11.7 99.5-9 132.1-9s102.7-2.6 132.1 9c19.6 7.8 34.7 22.9 42.6 42.6 11.7 29.5 9 99.5 9 132.1s2.7 102.7-9 132.1z"/></svg>  {userData.instagramLink}</div> }
            {userData.twitterLink && <div id="twitter"><svg style={{width: "15px"}} xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512"><path d="M459.37 151.716c.325 4.548.325 9.097.325 13.645 0 138.72-105.583 298.558-298.558 298.558-59.452 0-114.68-17.219-161.137-47.106 8.447.974 16.568 1.299 25.34 1.299 49.055 0 94.213-16.568 130.274-44.832-46.132-.975-84.792-31.188-98.112-72.772 6.498.974 12.995 1.624 19.818 1.624 9.421 0 18.843-1.3 27.614-3.573-48.081-9.747-84.143-51.98-84.143-102.985v-1.299c13.969 7.797 30.214 12.67 47.431 13.319-28.264-18.843-46.781-51.005-46.781-87.391 0-19.492 5.197-37.36 14.294-52.954 51.655 63.675 129.3 105.258 216.365 109.807-1.624-7.797-2.599-15.918-2.599-24.04 0-57.828 46.782-104.934 104.934-104.934 30.213 0 57.502 12.67 76.67 33.137 23.715-4.548 46.456-13.32 66.599-25.34-7.798 24.366-24.366 44.833-46.132 57.827 21.117-2.273 41.584-8.122 60.426-16.243-14.292 20.791-32.161 39.308-52.628 54.253z"/></svg>  {userData.twitterLink}</div> }
          </div>
        }

        {/* userSeq가 내가 아니면 남의 버튼 렌더링, 나라면 나의 버튼 렌더링 */}
        { isMyPage ?
          <div className="my-page__profile_buttons">
            <Form action={'edit_profile'}><YellowBtn style={{width: "120px"}} type="submit">정보 수정하기</YellowBtn></Form>
            <Form action={'upload'}><YellowBtn style={{width: "120px"}} type="submit">작품 업로드</YellowBtn></Form>
            { isArtist &&
              <Form action={'curation_register'}><YellowBtn style={{width: "120px"}} type="submit">큐레이션 등록</YellowBtn></Form>
            }
            <RedBtn style={{width: "120px"}} onClick={handleLogout}>로그아웃</RedBtn>
          </div>
          :
          <div className="my-page__profile_buttons">
            <form onSubmit={event => {
              event.preventDefault()
              axiosReissue()

              if (!wonder) {
                let body = {"seq": +userSeq}
                axiosAuth.post("users/follow", body)
                  .then(response => response)
              } else if (wonder) {
                let body = {"seq": +userSeq}
                axiosAuth.delete("users/follow", {data: body})
                  .then(response => response)
              }
              setWonder(prev=>!prev)
            }}>
              <YellowBtn id='follow' style={{width: "150px"}}>{content}</YellowBtn>
            </form>
          </div>
        }
      </div>

      <div className="my-page__content">
        <nav className="my-page__nav">
          <NavLink to='.' className={({isActive}) => isActive? 'link my-page__link my-page__nav-active' : 'link my-page__link' } end>작품</NavLink>
          {/* 공지사항은 내 페이지도 아니고 작가도 아니면 아예 링크를 띄우지 말자 */}
          { (!isMyPage && !isArtist)? null : <NavLink to={ 'notices' } className={({isActive}) => isActive? 'link my-page__link my-page__nav-active' : 'link my-page__link' }>공지사항</NavLink>}
          <NavLink to={ 'curations' } className={({isActive}) => isActive? 'link my-page__link my-page__nav-active' : 'link my-page__link' } >큐레이션</NavLink>
        </nav>

        {/* 자식에게 props 전달 가능함! context를 이용한다. */}
        <Outlet context={[isMyPage, isArtist]} />
      </div>

    </div>
  );
}