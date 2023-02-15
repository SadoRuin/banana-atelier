import React from 'react';
import {Link, useLoaderData } from 'react-router-dom';
import { useSelector, useDispatch } from 'react-redux'

import axiosCustom from "../../_actions/axiosCustom";
import { landingRenderingReset } from '../../_actions/user_action'
import ArtComponent from "../../components/commons/ArtComponent";

export async function loader () {
  const artsList = await axiosCustom.get('arts/all' )
    .then(response=>response.data)
    .catch(error=>console.log(error))

  const curationOnList = await axiosCustom.get('curations/on' )
    .then(response=>response.data)
    .catch(error=>console.log(error))
  const curationInitList = await axiosCustom.get('curations/init' )
    .then(response=>response.data)
    .catch(error=>console.log(error))

  return [artsList, curationOnList, curationInitList];
}

function LandingPage() {
  const dispatch = useDispatch()
  const landingStatus = useSelector(state => state.user.landing_status);
  if (landingStatus === 2) {
    dispatch(landingRenderingReset())
      .then(window.location.reload())
  } else if (landingStatus === 3) {
    dispatch(landingRenderingReset())
      .then(window.location.reload())
  }

  const [artsList, curationOnList, curationInitList] = useLoaderData();

  const curationsList = [...curationOnList, ...curationInitList];
  console.log(curationsList)
  return (
    <div>

      <div>
        <h1><Link className='link' to="curations">큐레이션🍌</Link></h1>
        <p>현재 진행중인 큐레이션 {`->`} 진행 예정 큐레이션 보여주기</p>
      </div>

      {/*<div>*/}
      {/*  <h1><Link className='link' to="commissions">요즘 뜨는 커미션 작가💰</Link></h1>*/}
      {/*  <p>이 곳에는 요즘 뜨는 커미션 작가가 들어간다</p>*/}
      {/*</div>*/}

      <div>
        <h1>
          <Link className='link' to="arts">트렌딩🔥</Link>
        </h1>
        <div className="grid__main-components">
          {artsList.map((art) =>
            <ArtComponent
              key={`art-item_${art.artSeq}`}
              nickname={art.nickname}
              profileImg={art.profileImg}
              userSeq={art.userSeq}
              artThumbnail={art.artThumbnail}
              artName={art.artName}
              artSeq={art.artSeq}
              artHit={art.artHit}
              artLikeCount={art.artLikeCount}
            />
          )}
        </div>
      </div>
      
    </div>
  );
}

export default LandingPage
