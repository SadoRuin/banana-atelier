import React from 'react'
import {Link, useLoaderData} from 'react-router-dom'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faHeart } from '@fortawesome/free-solid-svg-icons'
import { faHeart as faHearEmpty } from '@fortawesome/free-regular-svg-icons'

import customAxios from '../../_actions/customAxios';
import ProfileImg from "../../components/commons/ProfileImg";
import {YellowBtn , LikeBtn} from "../../components/commons/buttons";
import './ArtsDetail.css'

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
    <div className="content__container">

      <div className="art-detail__container">
        {/* 작품 사진 */}
        <img src={artData.art_img} alt="작품 이미지" className="art-img" />

        {/* 작품 상세 정보 */}
        <div className="art-detail_content">
          <div>
            <h1>{artData.art_name}</h1>
            <Link className="artist_profile Link" to={`../${artData.nickname}/arts`}>
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
                조회수 : {artData.art_hit}
              </div>
              <div className="downloaded">
                <img src="ArtsMain" alt="" />
                다운로드 : {artData.art_download_count}
              </div>
              <div className="likes">
                <img src="ArtsMain" alt="" />
                좋아요 : {artData.art_like_count}
              </div>
            </div>
            <div>
              {/* 좋아요 누른 버튼이랑 안누른 버튼 */}
              <LikeBtn><FontAwesomeIcon icon={faHeart} /></LikeBtn>
              <LikeBtn><FontAwesomeIcon icon={faHearEmpty} /></LikeBtn>
              {/* 이미지 다운로드는 아직인듯? */}
              <YellowBtn>다운로드</YellowBtn>
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
