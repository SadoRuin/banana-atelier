import React from 'react'
import {Link, useLoaderData} from 'react-router-dom'
import axiosAuth from '../../_actions/axiosAuth';
import ProfileImg from "../../components/commons/ProfileImg";
import { getArtImage } from "../../components/commons/imageModule";
import {YellowBtn , LikeBtn} from "../../components/commons/buttons";
import {Category} from "../../components/commons/category";
import './ArtsDetail.css'

export async function loader ({params}) {
  let artSeq = params.art_seq;

  const artData = await axiosAuth.get(`arts/detail/${artSeq}`)
    .then(response => response.data)
    .catch(error => console.log(error))

  console.log(artData);
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
  console.log(artData);

  return (
    <div>
      <div className="art-detail__container grid__detail-page">
        {/* 작품 사진 */}
        {/*<img src={artData.art_img} alt="작품 이미지" className="art-img" />*/}
        <img
          src={`${getArtImage(artData.artImg, artData.userSeq)}`}
          alt="작품 이미지"
          className="art-img"
        />

        {/* 작품 상세 정보 */}
        <div className="art-detail_content">
          <div className="art-detail__main-info">
            <h1>{artData.artName}</h1>
            <Link className="artist_profile link" to={`../${artData.nickname}/arts`}>
              <ProfileImg height="30px" width="30px" url={artData.profileImg} artistSeq={artData.userSeq} />
              <div>{artData.nickname} <span className="jakka">작가</span></div>
            </Link>
            <div className="upload_date">{`${artData.artRegDate[0]}.${(artData.artRegDate[1]+'').padStart(2, "0")}.${(artData.artRegDate[2]+'').padStart(2, "0")}.`}</div>
            <div className="arts_description">
              {artData.artDescription}
            </div>
            <Category className="art-detail__category">
              {artData.artCategory.artCategoryName}
            </Category>
          </div>

          <div>
            <div className="art-detail__sub-info">
              <div className="views">
                <img src="ArtsMain" alt="" />
                조회수 : {artData.artHit}
              </div>
              <div className="downloaded">
                <img src="ArtsMain" alt="" />
                다운로드 : {artData.artDownloadCount}
              </div>
              <div className="likes">
                <img src="ArtsMain" alt="" />
                좋아요 : {artData.artLikeCount}
              </div>
            </div>
            <div className="art-detail__btns">
              {/* 좋아요 누른 버튼이랑 안누른 버튼 */}
              <LikeBtn isLike={true} />
              {/* 이미지 다운로드는 아직인듯? */}
              <YellowBtn width="150px">다운로드</YellowBtn>
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