import React , { useState } from 'react'
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate, useLoaderData, useSearchParams } from 'react-router-dom';

import { landingRenderingReset } from '../../_actions/user_action'
import axiosCustom from '../../_actions/axiosCustom';
import ArtComponent from "../../components/commons/ArtComponent";
import TabMenuComponent from "../../components/commons/TabMenuComponent";
import { Category } from "../../components/commons/Category";
import './ArtsMain.css'


export async function loader ({request}) {
  const url = new URL(request.url);
  const categorySeq = url.searchParams.get("category") || null;

  const artsTrendAll = await axiosCustom.get('arts/trend')
    .then(response=> response.data)
    .catch(() => null)

  const artsPopularAll = await axiosCustom.get('arts/popular')
    .then(response => response.data)
    .catch(() => null)

  const artsNewAll = await axiosCustom.get('arts/new')
    .then(response => response.data)
    .catch(() => null)
  
  const artsAll = categorySeq ?
    await axiosCustom.get(`arts/category/${categorySeq}`)
      .then(response => response.data)
      .catch(() => null) :
    await axiosCustom.get('arts/all')
      .then(response => response.data)
      .catch(() => null)

  const artsTrend = categorySeq && artsTrendAll? (artsTrendAll.filter((trend) => trend.artCategory.id === +categorySeq)) : artsTrendAll;
  const artsPopular = categorySeq && artsPopularAll? (artsPopularAll.filter((popular) => popular.artCategory.id === +categorySeq)) : artsPopularAll;
  const artsNew = categorySeq && artsNewAll? (artsNewAll.filter((artNew) => artNew.artCategory.id === +categorySeq)) : artsNewAll;

  return [artsTrend, artsPopular, artsNew, artsAll];
}

function ArtsMain() {
  const [artsTrend, artsPopular, artsNew, artsAll] = useLoaderData();
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const [searchParams] = useSearchParams();
  const categorySeq = +searchParams.get('category');

  const landingStatus = useSelector(state => state.user.landing_status);
  if (landingStatus === 2) {
    dispatch(landingRenderingReset())
      .then(()=>window.location.reload())
  } else if (landingStatus === 3) {
    dispatch(landingRenderingReset())
      .then(()=>window.location.reload())
  }

  const [artsMainIndex, setArtsMainIndex] = useState(0);
  const artsMainMenuData = [
    {
      name: "트렌드 작품",
      content:
        <div className="grid__main-components">
          { artsTrend?.map((art) =>
            <ArtComponent
              key={`art-main__trend-${art.artSeq}`}
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
    },
    {
      name: "전체 작품",
      content:
        <div className="grid__main-components">
          { artsAll?.map((art) =>
            <ArtComponent
              key={`art-main__all-${art.artSeq}`}
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
    },
    {
      name: "새로 나온 작품",
      content:
        <div className="grid__main-components">
          { artsNew.map((art) =>
            <ArtComponent
              key={`art-main__new-${art.artSeq}`}
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
    },
    {
      name: "인기 많은 작품",
      content:
        <div className="grid__main-components">
          { artsPopular.map((art) =>
            <ArtComponent
              key={`art-main__popular-${art.artSeq}`}
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
    }
  ]

  return (
    <div>
      <div className="art-main__category" style={{display: 'flex', width: '75%', justifyContent: 'space-between', marginTop: '30px'}}>
        <Category className={categorySeq === 0? 'navCategory' : ''} onClick={() => {navigate('/arts')}}>전체</Category>
        <Category className={categorySeq === 1? 'navCategory' : ''} onClick={() => {navigate({search: '?category=1' })}}>일러스트레이션</Category>
        <Category className={categorySeq === 2? 'navCategory' : ''} onClick={() => {navigate({search: '?category=2' })}}>캐릭터디자인</Category>
        <Category className={categorySeq === 3? 'navCategory' : ''} onClick={() => {navigate({search: '?category=3' })}}>디지털 아트</Category>
        <Category className={categorySeq === 4? 'navCategory' : ''} onClick={() => {navigate({search: '?category=4' })}}>타이포그래피</Category>
        <Category className={categorySeq === 5? 'navCategory' : ''} onClick={() => {navigate({search: '?category=5' })}}>포토그래피</Category>
        <Category className={categorySeq === 6? 'navCategory' : ''} onClick={() => {navigate({search: '?category=6' })}}>파인아트</Category>
        <Category className={categorySeq === 7? 'navCategory' : ''} onClick={() => {navigate({search: '?category=7' })}}>공예</Category>
      </div>
      <TabMenuComponent menuData={artsMainMenuData} index={artsMainIndex} setIndex={setArtsMainIndex} />
    </div>
  )
}

export default ArtsMain
