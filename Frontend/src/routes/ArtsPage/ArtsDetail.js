import React from 'react'
import {Link, useLoaderData} from 'react-router-dom'
import axios from 'axios'
import ProfileImg from "../../components/ProfileImg";

export async function loader ({params}) {
  let artSeq = params.art_seq;

  const artData = await axios.get(`https://i8a108.p.ssafy.io/api/arts/detail/${artSeq}`)
    .then(response => response)
    .catch(error => error)

  return artData;
}

function ArtsDetail() {
  const art = useLoaderData().data;
  console.log(art)
  return (
    <div>

      <div className="arts_detail_container">
        {/* 작품 사진 */}
        <img src={art.art_img} alt="작품 이미지" />

        {/* 작품 상세 정보 */}
        <div className="arts_detail_content">
          <div>
            <h1>{art.art_name}</h1>
            <div className="artist_profile">
              <ProfileImg src="https://item.kakaocdn.net/do/b9df681f72876cbace33e39bb375f0ea8f324a0b9c48f77dbce3a43bd11ce785" height="30px" />
              <Link to="/mypage/arts">{art.nickname} <span>작가</span></Link>
            </div>
            <div className="upload_date">{`${art.art_reg_date[0]}.${art.art_reg_date[1]}.${art.art_reg_date[2]}.`}</div>
            <div className="arts_description">
              {art.art_description}
            </div>
            <div>
              {art.art_category.artCategoryName}
            </div>
          </div>
          <div>
            <div>
              <div className="views">
                <img src="ArtsMain" alt="" />
                viewers:
                {art.art_hit}
              </div>
              <div className="downloaded">
                <img src="ArtsMain" alt="" />
                downloaded:이 값이 없다!
              </div>
              <div className="likes">
                <img src="ArtsMain" alt="" />
                좋아요 : {art.art_like_count}
              </div>
            </div>
            <div>
              <button>좋아요</button>
              <button>다운로드</button>
            </div>
          </div>
        </div>
      </div>

      <div className="familiar_arts">
        <h2>비슷한 작품</h2>
        <div>
          비슷한 작품 컴포넌트들이 올 곳
        </div>
      </div>


    </div>
  )
}

export default ArtsDetail
