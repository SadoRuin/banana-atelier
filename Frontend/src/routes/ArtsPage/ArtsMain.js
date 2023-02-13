import React from 'react'
import axiosCustom from '../../_actions/axiosCustom';
import { NavLink, Outlet } from 'react-router-dom';
// import ArtItem from "../../components/commons/artItem";
// import {Category} from "../../components/commons/category";


export async function loader () {

  const artsTrend = await axiosCustom.get('/arts/trend')
    .then(response=>response.data)
    .catch(error=>error)

  const artsPopular = await axiosCustom.get('/arts/trend')
  .then(response=>response.data)
  .catch(error=>error)

  const artsNew = await axiosCustom.get('/arts/trend')
    .then(response=>response.data)
    .catch(error=>error)

  return [artsTrend, artsPopular, artsNew];
}

function ArtsMain() {

  // const [artsTrend, artsPopular, artsNew] = useLoaderData();
  return (
    <div>

      {/* 클릭하면 카테고리 변경되게 하자 */}

      <NavLink className={({isActive}) => isActive? 'link nav-active' :'link' } to=".">전체</NavLink><br />
      <NavLink className={({isActive}) => isActive? 'link nav-active' :'link' } to="1">일러스트레이션</NavLink><br />
      <NavLink className={({isActive}) => isActive? 'link nav-active' :'link' } to="2">캐릭터 디자인</NavLink><br />
      <NavLink className={({isActive}) => isActive? 'link nav-active' :'link' } to="3">디지털 아트</NavLink><br />
      <NavLink className={({isActive}) => isActive? 'link nav-active' :'link' } to="4">타이포그래피</NavLink><br />
      <NavLink className={({isActive}) => isActive? 'link nav-active' :'link' } to="5">포토그래피</NavLink><br />
      <NavLink className={({isActive}) => isActive? 'link nav-active' :'link' } to="6">파인아트</NavLink><br />
      <NavLink className={({isActive}) => isActive? 'link nav-active' :'link' } to="7">공예</NavLink>

      {/* 정렬 탭도.. */}
      {/*<div className="sort_tab">*/}
      {/*  <div>요즘 뜨는 작품</div>*/}
      {/*  <div className="grid__main-components">*/}
      {/*    {artsTrend.map((art) =>*/}
      {/*      <div key={`art-item_${art.artSeq}`}>*/}
      {/*        <ArtItem*/}
      {/*          nickname={art.nickname}*/}
      {/*          profileImg={art.profileImg}*/}
      {/*          userSeq={art.userSeq}*/}
      {/*          artThumbnail={art.artThumbnail}*/}
      {/*          artName={art.artName}*/}
      {/*          artSeq={art.artSeq}*/}
      {/*          artHit={art.artHit}*/}
      {/*          artLikeCount={art.artLikeCount}*/}
      {/*        />*/}
      {/*      </div>*/}
      {/*    )}*/}
      {/*  </div>*/}


      {/*  <div>새로 나온 작품</div>*/}
      {/*  <div className="grid__main-components">*/}
      {/*    {artsNew.map((art) =>*/}
      {/*      <div key={`art-item_${art.artSeq}`}>*/}
      {/*        <ArtItem*/}
      {/*          nickname={art.nickname}*/}
      {/*          profileImg={art.profileImg}*/}
      {/*          userSeq={art.userSeq}*/}
      {/*          artThumbnail={art.artThumbnail}*/}
      {/*          artName={art.artName}*/}
      {/*          artSeq={art.artSeq}*/}
      {/*          artHit={art.artHit}*/}
      {/*          artLikeCount={art.artLikeCount}*/}
      {/*        />*/}
      {/*      </div>*/}
      {/*    )}*/}
      {/*  </div>*/}
      {/*  */}
      {/*  */}
      {/*  <div>인기 작품</div>*/}

      {/*  <div className="grid__main-components">*/}
      {/*    {artsPopular.map((art) =>*/}
      {/*      <div key={`art-item_${art.artSeq}`}>*/}
      {/*        <ArtItem*/}
      {/*          nickname={art.nickname}*/}
      {/*          profileImg={art.profileImg}*/}
      {/*          userSeq={art.userSeq}*/}
      {/*          artThumbnail={art.artThumbnail}*/}
      {/*          artName={art.artName}*/}
      {/*          artSeq={art.artSeq}*/}
      {/*          artHit={art.artHit}*/}
      {/*          artLikeCount={art.artLikeCount}*/}
      {/*        />*/}
      {/*      </div>*/}
      {/*    )}*/}
      {/*  </div>*/}
      {/*</div>*/}

      <Outlet />
      


    </div>
  )
}

export default ArtsMain
