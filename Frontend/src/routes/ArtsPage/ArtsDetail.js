import React from 'react'
import {Link, useLoaderData} from 'react-router-dom'
import ProfileImg from "../../components/commons/ProfileImg";
import customAxios from '../../_actions/customAxios';

export async function loader ({params}) {
  let artSeq = params.art_seq;
  const artData = await customAxios().get(`arts/detail/${artSeq}`)
    .then(response => response.data)
    .catch(error => error)

  console.log(artData)
  if (!artData) {
    throw new Response("", {
      status: 404,
      statusText: "Not Found",
    });
  }
  return artData;
}

function ArtsDetail() {
  const artData = useLoaderData();

  return (
    <div>

      <div className="art-detail__container">
        {/* 작품 사진 */}
        <img src={artData.art_img} alt="작품 이미지" className="art-img" />

        {/* 작품 상세 정보 */}
        <div className="art-detail_content">
          <div>
            <h1>{artData.art_name}</h1>
            <Link className="artist_profile Link" to={`./${artData.nickname}/arts`}>
              {/* 프로필 이미지는 아직입니다!! src={artData.profile_img}*/}
              <ProfileImg height="30px" width="30px" />
              <div>{artData.nickname} <span className="jakka">작가</span></div>
            </Link>
            <div className="upload_date">{`${artData.art_reg_date[0]}.${(artData.art_reg_date[1]+'').padStart(2, "0")}.${(artData.art_reg_date[2]+'').padStart(2, "0")}.`}</div>
            <div className="arts_description">
              {artData.art_description}
            </div>
            <div>
              {artData.art_category.artCategoryName}
            </div>
          </div>
          <div>
            <div>
              <div className="views">
                <img src="ArtsMain" alt="" />
                viewers:
                {artData.art_hit}
              </div>
              <div className="downloaded">
                <img src="ArtsMain" alt="" />
                downloaded:이 값이 없다!
              </div>
              <div className="likes">
                <img src="ArtsMain" alt="" />
                좋아요 : {artData.art_like_count}
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
