import React, { useState } from 'react'
import { Form, Link, redirect, useLoaderData, useNavigate } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faBookmark } from "@fortawesome/free-solid-svg-icons";

import { axiosAuth, axiosReissue } from '../../_actions/axiosAuth';
import ProfileImg from "../../components/commons/ProfileImg";
import ArtItemMyPage from "../../components/MyPage/ArtItemMyPage";
import { getArtImage } from '../../components/commons/imageModule';

import { BookmarkBtn, RedBtn, YellowBtn } from "../../components/commons/buttons";
import '../ArtsPage/ArtDetail.css'
import '../MyPage/ArtsRoot.css'


export async function loader ({params}) {
  axiosReissue();
  let curationSeq = +params.curation_seq
  let curationDetail, curationDetailArts, isBookmarked = null;

  await axiosAuth.get(`/curations/details/${curationSeq}`)
    .then(response =>  curationDetail = response.data)
  await axiosAuth.get(`/curation-art/list/${curationDetail.curationSeq}`)
    .then(response =>  curationDetailArts = response.data)
  await axiosAuth.get(`/curations/${localStorage.getItem("userSeq")}/${curationDetail.curationSeq}`)
    .then(response => isBookmarked = response.data)

  return [curationDetail, curationDetailArts, isBookmarked];
}

export async function action ({request, params}) {
  const curationSeq = +params.curation_seq;
  if (request.method === "DELETE") {
    await axiosAuth.delete(`curations/${curationSeq}`)
  }
  else if (request.method === "PUT") {
  }
  return redirect('../')
}

function CurationDetail() {
  const [curationDetail, curationDetailArts, isBookmarked] = useLoaderData();
  const navigate = useNavigate()

  let nickname = curationDetail.userNickname
  let profileImg = curationDetail.profileImg
  let userSeq = curationDetail.userSeq
  let curationImg = curationDetail.curationImg
  let curationName =curationDetail.curationName
  let curationSeq = curationDetail.curationSeq
  let curationBmCount = curationDetail.curationBmCount
  let curationStartTime = curationDetail.curationStartTime
  let curationStatus = curationDetail.curationStatus
  let curationSummary = curationDetail.curationSummary

  const [bookmarkNum, setBookmarkNum] = useState(curationBmCount)
  const [likeCurations, setLikeCurations] = useState(isBookmarked)
  const handleBookMark = async () => {
    axiosReissue();
    let body = {
      curationSeq: curationSeq,
      userSeq: localStorage.getItem("userSeq")
    }

    if (likeCurations) {
      await axiosAuth.delete('curations/bookmark', {data: body})
      setBookmarkNum(prev=>prev-1)
    } else {
      await axiosAuth.post('curations/bookmark', body)
      setBookmarkNum(prev=>prev+1)
    }
    setLikeCurations(prev=>!prev)
  }

  // 큐레이션 날짜 (진행중, 예정, 종료에 따라 다르게 렌더링)
  let curationDate;
  if (curationStatus === "INIT") {
      curationDate = <div>{`${curationStartTime[0]}.${(curationStartTime[1]+'').padStart(2, "0")}.${(curationStartTime[2]+'').padStart(2, "0")} ${(curationStartTime[3]+'').padStart(2, "0")}:${(curationStartTime[4]+"").padStart(2, "0")} 예정`}</div>
  } else if (curationStatus === "ON") {
      curationDate = <div>{`${curationStartTime[0]}.${(curationStartTime[1]+'').padStart(2, "0")}.${(curationStartTime[2]+'').padStart(2, "0")} 진행중`}</div>
  } else {
      curationDate = <div>{`${curationStartTime[0]}.${(curationStartTime[1]+'').padStart(2, "0")}.${(curationStartTime[2]+'').padStart(2, "0")} 종료`}</div>
  }

  const handleStartCuration = () => {
    axiosReissue();
    axiosAuth.put(`curations/${curationSeq}/on`);
    navigate(`/curations/on_air/${curationSeq}`);
  }

  const handleEnterCuration = () => {
    navigate(`/curation/on_air/${curationSeq}`)
  }

  return (
    <div>
      <div className="art-detail__container grid__detail-page">
        <img src={`${getArtImage(curationImg, userSeq)}`} alt="큐레이션 대표 이미지" className="art-img" />
        {/* 큐레이션 상세 정보 */}
        <div className="art-detail_content">
          <div className="art-detail__main-info">
            <div className="art-detail__title">
              <h1>{curationName}</h1>
              { userSeq === +localStorage.getItem('userSeq') &&
                <div className="art-detail__manage">
                  <Form method="delete"><RedBtn type="submit">삭제하기</RedBtn></Form>
                  <Form method="put"><YellowBtn type="submit">수정하기</YellowBtn></Form>
                </div> }
            </div>
            <Link className="art-detail__profile link" to={`/${nickname}@${userSeq}`}>
              <ProfileImg height="30px" width="30px" url={profileImg} userSeq={userSeq} />
              <div>{nickname} <span className="jakka">작가</span></div>
            </Link>
            <div className="upload_date">{curationDate}</div>
            <div className="arts_description" style={{whiteSpace: "pre-line"}}>
              {curationSummary}
            </div>
          </div>

          <div>
            <div className="art-detail__sub-info">
              <div className="views">
                <FontAwesomeIcon icon={faBookmark} /> {bookmarkNum}
              </div>
            </div>

            <div className="art-detail__btns">
              <div onClick={handleBookMark}>
                <BookmarkBtn  isBookmark={likeCurations} />
              </div>
              { curationStatus === "ON" &&
                <Link to={`../curations/on_air/${curationSeq}`}><YellowBtn style={{width: "120px"}} onClick={handleEnterCuration}>입장하기</YellowBtn></Link> }
              { (curationStatus === "INIT" && userSeq === +localStorage.getItem('userSeq')) &&
                <Link to={`../curations/on_air/${curationSeq}`} ><YellowBtn style={{width: "120px"}} onClick={handleStartCuration}>시작하기</YellowBtn></Link> }
            </div>

          </div>
        </div>
      </div>

      <div className="arts_curation_for art-root__masterpiece-container">
        <h3 style={{gridColumn: '1 / end'}}>큐레이션 진행 작품</h3>
        { curationDetailArts.map((art) =>
          <div key={`curation-detail__art-${art.artSeq}`}>
            <ArtItemMyPage
              artThumbnail={art.curationThumbnail}
              userSeq={userSeq}
              artSeq={art.artSeq}
              artName={art.artName}
              nickname={art.artistNickName}
            />
            <div>경매 시작가 : {art.auctionStartPrice}</div>
            <div>호가 단위 : {art.auctionGap}</div>
          </div>
        )}
      </div>

    </div>
  )
}

export default CurationDetail
