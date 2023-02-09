import React from 'react';
import {Link, useLoaderData} from 'react-router-dom';
import axios from 'axios'
import { logoutCode } from '../../_actions/user_action';

import ArtItem from "../../components/commons/artItem";
import { useDispatch, useSelector } from 'react-redux';

export async function loader () {
  const arts = await axios.get('https://i8a108.p.ssafy.io/api/arts' )
    .then(response=>response)
    .catch(error=>console.log(error))
  return { arts };
}

function LandingPage() {
  const dispatch = useDispatch()
  const loginWonder = useSelector(state => state.user.login_status)
  const handleLogOut = event => {
    event.preventDefault()
    dispatch(logoutCode())
    console.log("로그인 했나요?", loginWonder)
  }
  const {arts} = useLoaderData();

  return (
    <div className="content__container" >
      <button onClick={handleLogOut}>로그아웃</button>
      <div>
        <h1><Link className='Link' to="curations">큐레이션🍌</Link></h1>
        <p>현재 진행중인 큐레이션 -> 진행 예정 큐레이션 보여주기</p>
      </div>

      <div>
        <h1><Link className='Link' to="commissions">요즘 뜨는 커미션 작가💰</Link></h1>
        <p>이 곳에는 요즘 뜨는 커미션 작가가 들어간다</p>
      </div>

      <div >
        <h1><Link className='Link' to="arts">트렌딩🔥</Link></h1>
        <div className="grid__for-components">
          {arts.data.map((art) => <div key={`art_item_${art.art_seq}`}>
            <ArtItem
              nickname={art.nickname}
              profile_img={art.profile_img}
              art_name={art.art_name}
              art_seq={art.art_seq}
              art_hit={art.art_hit}
              art_like_count={art.art_like_count}
              art_thumbnail={art.art_thumbnail}
            />
          </div>)}
        </div>
      </div>
      
    </div>
  );
}

export default LandingPage
