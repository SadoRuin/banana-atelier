import React , {useState} from 'react'
import axiosCustom from '../../_actions/axiosCustom';
import { useNavigate, useLoaderData } from 'react-router-dom';
import ArtItem from "../../components/commons/artItem";
import { TabMenu, TabContent } from "../../components/commons/TabMenuComponent";

// import {Category} from "../../components/commons/category";

export async function loader ({request}) {
  const url = new URL(request.url);
  const categorySeq = url.searchParams.get("category") || null;

  const artsTrendAll = await axiosCustom.get('arts/trend')
    .then(response=>response.data)
    .catch(() => null)

  const artsPopularAll = await axiosCustom.get('arts/popular')
    .then(response=>response.data)
    .catch(() => null)

  const artsNewAll = await axiosCustom.get('arts/new')
    .then(response=>response.data)
    .catch(() => null)
  
  const artsAll = categorySeq?
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
  const navigate = useNavigate();
  const [artsTrend, artsPopular, artsNew, artsAll] = useLoaderData();

  const artsSortMenuData = [
    {
      name: "트렌드 작품",
      content:
        <div className="grid__main-components">
          {artsTrend?.map((art) =>
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
    },
    {
      name: "전체 작품",
      content:
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
    },
    {
      name: "새로 나온 작품",
      content:
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
    },
    {
      name: "인기 많은 작품",
      content:
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
    }
  ]

  const [sortIndex, setSortIndex] = useState(0);



  return (
    <div>

      <button onClick={() => {navigate('/arts')}}>전체</button>
      <button onClick={() => {navigate({search: '?category=1' })}}>일러스트레이션</button>
      <button onClick={() => {navigate({search: '?category=2' })}}>캐릭터디자인</button>
      <button onClick={() => {navigate({search: '?category=3' })}}>디지털 아트</button>
      <button onClick={() => {navigate({search: '?category=4' })}}>타이포그래피</button>
      <button onClick={() => {navigate({search: '?category=5' })}}>포토그래피</button>
      <button onClick={() => {navigate({search: '?category=6' })}}>파인아트</button>
      <button onClick={() => {navigate({search: '?category=7' })}}>공예</button>

      <TabMenu>
        {
          artsSortMenuData.map((tab, idx) =>
            <li
              className={idx === sortIndex ? "submenu focused" : "submenu" }
              onClick={ () => setSortIndex(idx) } key={`my-page__art-${idx}`}
            >
              {tab.name}
            </li>
          )
        }
      </TabMenu>
      <TabContent>
        {
          artsSortMenuData[sortIndex].content
        }
      </TabContent>
    </div>
  )
}

export default ArtsMain
