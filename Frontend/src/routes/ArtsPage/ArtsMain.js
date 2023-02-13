import React from 'react'
import axiosCustom from '../../_actions/axiosCustom';
import ArtItem from "../../components/commons/artItem";
import { useLoaderData, Link } from 'react-router-dom';


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

  const [artsTrend, artsPopular, artsNew] = useLoaderData();
  return (
    <div>

      {/* 클릭하면 카테고리 변경되게 하자 */}

      <Link className='link' to="1">일러스트레이션</Link><br />
      <Link className='link' to="2">캐릭터 디자인</Link><br />
      <Link className='link' to="3">디지털 아트</Link><br />
      <Link className='link' to="4">타이포그래피</Link><br />
      <Link className='link' to="5">포토그래피</Link><br />
      <Link className='link' to="6">파인아트</Link><br />
      <Link className='link' to="7">공예</Link>

      {/* 정렬 탭도.. */}
      <div className="sort_tab">
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



      <div>여기에 작품 component 렌더링?</div>
      


    </div>
  )
}

export default ArtsMain
