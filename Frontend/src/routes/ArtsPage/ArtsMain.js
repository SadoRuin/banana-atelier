import React from 'react'
import axiosCustom from '../../_actions/axiosCustom';
import { useNavigate, useLocation, useLoaderData } from 'react-router-dom';
import ArtItem from "../../components/commons/artItem";
// import {Category} from "../../components/commons/category";


export async function loader ({request}) {
  const url = new URL(request.url);
  console.log(url);
  const categorySeq = url.searchParams.get("category") || null;
  console.log(categorySeq);


  const artsTrend = await axiosCustom.get('arts/trend')
    .then(response=>response.data)
    .catch(error=>error)

  const artsPopular = await axiosCustom.get('arts/popular')
  .then(response=>response.data)
  .catch(error=>error)

  const artsNew = await axiosCustom.get('arts/new')
    .then(response=>response.data)
    .catch(error=>error)

  
  const artsAll = categorySeq?
    await axiosCustom.get(`arts/category/${categorySeq}`)
      .then(response => response.data)
      .catch(error => error):
    await axiosCustom.get('arts/all')
      .then(response => response.data)
      .catch(error => error)
  console.log(artsAll);

  return [artsTrend, artsPopular, artsNew, artsAll];
}

function ArtsMain() {
  const navigate = useNavigate();
  const location = useLocation();
  console.log(location)
  const [artsTrend, artsPopular, artsNew, artsAll] = useLoaderData();
  return (
    <div>

      {/* 클릭하면 카테고리 변경되게 하자 */}

      {/*<NavLink className={({isActive}) => isActive? 'link nav-active' :'link' } to=".">전체</NavLink><br />*/}
      {/*<NavLink className={({isActive}) => isActive? 'link nav-active' :'link' } to="1">일러스트레이션</NavLink><br />*/}
      {/*<NavLink className={({isActive}) => isActive? 'link nav-active' :'link' } to="2">캐릭터 디자인</NavLink><br />*/}
      {/*<NavLink className={({isActive}) => isActive? 'link nav-active' :'link' } to="3">디지털 아트</NavLink><br />*/}
      {/*<NavLink className={({isActive}) => isActive? 'link nav-active' :'link' } to="4">타이포그래피</NavLink><br />*/}
      {/*<NavLink className={({isActive}) => isActive? 'link nav-active' :'link' } to="5">포토그래피</NavLink><br />*/}
      {/*<NavLink className={({isActive}) => isActive? 'link nav-active' :'link' } to="6">파인아트</NavLink><br />*/}
      {/*<NavLink className={({isActive}) => isActive? 'link nav-active' :'link' } to="7">공예</NavLink>*/}

      <button onClick={() => {navigate('/arts')}}>전체</button>
      <button onClick={() => {navigate({search: '?category=1' })}}>일러스트레이션</button>
      <button onClick={() => {navigate({search: '?category=2' })}}>캐릭터디자인</button>
      <button onClick={() => {navigate({search: '?category=3' })}}>디지털 아트</button>
      <button onClick={() => {navigate({search: '?category=4' })}}>타이포그래피</button>
      <button onClick={() => {navigate({search: '?category=5' })}}>포토그래피</button>
      <button onClick={() => {navigate({search: '?category=6' })}}>파인아트</button>
      <button onClick={() => {navigate({search: '?category=7' })}}>공예</button>





      {/* 정렬 탭도.. */}
      <div className="sort_tab">

        <div>전체 작품</div>
        <div className="grid__main-components">
          {artsAll?.map((art) =>
            <div key={`art-item_${art.artSeq}`}>
              <ArtItem
                nickname={art.nickname}
                profileImg={art.profileImg}
                userSeq={art.userSeq}
                artThumbnail={art.artThumbnail}
                artName={art.artName}
                artSeq={art.artSeq}
                artHit={art.artHit}
                artLikeCount={art.artLikeCount}
              />
            </div>
          )}
        </div>


        <div>요즘 뜨는 작품</div>
        <div className="grid__main-components">
          {artsTrend.map((art) =>
            <div key={`art-item_${art.artSeq}`}>
              <ArtItem
                nickname={art.nickname}
                profileImg={art.profileImg}
                userSeq={art.userSeq}
                artThumbnail={art.artThumbnail}
                artName={art.artName}
                artSeq={art.artSeq}
                artHit={art.artHit}
                artLikeCount={art.artLikeCount}
              />
            </div>
          )}
        </div>


        <div>새로 나온 작품</div>
        <div className="grid__main-components">
          {artsNew.map((art) =>
            <div key={`art-item_${art.artSeq}`}>
              <ArtItem
                nickname={art.nickname}
                profileImg={art.profileImg}
                userSeq={art.userSeq}
                artThumbnail={art.artThumbnail}
                artName={art.artName}
                artSeq={art.artSeq}
                artHit={art.artHit}
                artLikeCount={art.artLikeCount}
              />
            </div>
          )}
        </div>


        <div>인기 작품</div>

        <div className="grid__main-components">
          {artsPopular.map((art) =>
            <div key={`art-item_${art.artSeq}`}>
              <ArtItem
                nickname={art.nickname}
                profileImg={art.profileImg}
                userSeq={art.userSeq}
                artThumbnail={art.artThumbnail}
                artName={art.artName}
                artSeq={art.artSeq}
                artHit={art.artHit}
                artLikeCount={art.artLikeCount}
              />
            </div>
          )}
        </div>
      </div>

      


    </div>
  )
}

export default ArtsMain
