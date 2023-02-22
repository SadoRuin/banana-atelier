import React, { useState } from 'react'
import { Form, Link, redirect } from "react-router-dom";
import { useLoaderData } from 'react-router-dom';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faBookmark } from "@fortawesome/free-solid-svg-icons";
import { axiosAuth, axiosReissue } from '../../_actions/axiosAuth';
import { useNavigate } from 'react-router-dom';
import ProfileImg from "../../components/commons/ProfileImg";
// import CurationComponent from '../../components/commons/CurationComponent';
import ArtItemMyPage from "../../components/MyPage/ArtItemMyPage";
// import { getArtThumbnail } from '../../components/commons/imageModule';
import { getArtImage } from '../../components/commons/imageModule';

import { BookmarkBtn, RedBtn, YellowBtn } from "../../components/commons/buttons";
import '../ArtsPage/ArtDetail.css'
import '../MyPage/ArtsRoot.css'


export async function loader ({params}) {
  let curationSeq = +params.curation_seq
  axiosReissue();

  let curationDetail, curationDetailArts, isBookmarked = null;
  await axiosAuth.get(`/curations/details/${curationSeq}`)
    .then(response => {
      curationDetail = response.data;
      return curationDetail
    })
    .then(curationDetail => {
      return axiosAuth.get(`/curation-art/list/${curationDetail.curationSeq}`);
    })
    .then(response => {
      curationDetailArts = response.data;
    })
    .catch(error => console.log(error));

  await axiosAuth.get(`/curations/${localStorage.getItem("userSeq")}/${curationDetail.curationSeq}`)
    .then(response => {
      isBookmarked = response.data
    })

  console.log(curationDetail)
  console.log(curationDetailArts)
  console.log(isBookmarked)
  return [curationDetail, curationDetailArts, isBookmarked];
}

export async function action ({request, params}) {
  const curationSeq = +params.curation_seq;
  if (request.method === "DELETE") {
    await axiosAuth.delete(`curations/${curationSeq}`)
      .then(response => console.log(response))
      .catch(error => console.log(error))
  }
  else if (request.method === "PUT") {
    console.log("put")
  }
  return redirect('../')
}

function CurationDetail() {
  const [curationDetail, curationDetailArts, isBookmarked] = useLoaderData();
  const navigate = useNavigate()

  let nickname= curationDetail.userNickname
  let profileImg= curationDetail.profileImg
  let userSeq = curationDetail.userSeq
  // let curationThumbnail =curationDetail.curationThumbnail
  let curationImg = curationDetail.curationImg
  let curationName =curationDetail.curationName
  let curationSeq = curationDetail.curationSeq
  // let curationHit = curationDetail.curationHit
  let curationBmCount = curationDetail.curationBmCount
  let curationStartTime = curationDetail.curationStartTime
  let curationStatus = curationDetail.curationStatus
  let curationSummary = curationDetail.curationSummary

  const [bookmarkNum, setBookmarkNum] = useState(curationBmCount)
  const [likeCurations, setLikeCurations] = useState(isBookmarked)
  const handleBookMark = async () => {
    console.log('북마크눌리나요?')
    let body = {
      curationSeq: curationSeq,
      userSeq: localStorage.getItem("userSeq")
    }
    axiosReissue();

    if (likeCurations) {
      await axiosAuth.delete('curations/bookmark', {data: body})
      setBookmarkNum(prev=>prev-1)
    } else {
      await axiosAuth.post('curations/bookmark', body)
        .then(response => console.log(response))
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
    let userSeq = localStorage.getItem("userSeq")
    localStorage.setItem("artistSeq", userSeq)
    axiosReissue()
  
    axiosAuth.put(`curations/${curationSeq}/on`)

    navigate(`/curations/on_air/${curationSeq}`)
  }

  const handleEnterCuration = () => {
    navigate(`/curation/on_air/${curationSeq}`)
  }



  return (
    <div>
      <div className="art-detail__container grid__detail-page">

        {/* 유진님 curation detail에서 썸네일 말고 arts이미지로 주세요 */}
        {/*<img src={`${getArtThumbnail(curationThumbnail, userSeq)}`} alt="큐레이션 대표 이미지" className="art-img" />*/}
        <img src={`${getArtImage(curationImg, userSeq)}`} alt="큐레이션 대표 이미지" className="art-img" />
        {/* --------------------------------------------------- */}

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
              {/* 시작한 큐레이션 참여 가능, 이 링크는 어떻게 될지 모르겟음~ */}
              { curationStatus === "ON" &&
                <Link to={`../curations/on_air/${curationSeq}`}><YellowBtn style={{width: "120px"}} onClick={handleEnterCuration}>입장하기</YellowBtn></Link> }
              {/* 시작 전 큐레이션이고 자신의 글이면 시작버튼 활성화 */}
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
