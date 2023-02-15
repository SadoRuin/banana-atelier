import React from 'react'
import ProfileImg from "../../components/commons/ProfileImg";
import {Link} from "react-router-dom";
import { useLoaderData } from 'react-router-dom';
import { axiosAuth, axiosReissue } from '../../_actions/axiosAuth';
// import CurationComponent from '../../components/commons/CurationComponent';
import { getArtThumbnail } from '../../components/commons/imageModule';
// import { getArtImage } from '../../components/commons/imageModule';

export async function loader ({params}) {
  let curationSeq = params.curation_seq

  axiosReissue()
  
  let curationDetail = await axiosAuth.get(`/curations/details/${curationSeq}`)

  console.log(curationDetail)
  return [curationDetail.data]
}


function CurationsDetail() {

  const [curationDetail] = useLoaderData();

  let nickname= curationDetail.userNickname
  let profileImg= curationDetail.profileImg
  let userSeq = curationDetail.userSeq
  let curationThumbnail =curationDetail.curationThumbnail
  // let curationName =curationDetail.curationName
  // let curationSeq = curationDetail.curationSeq
  // let curationHit = curationDetail.curationHit
  let curationBmCount = curationDetail.curationBmCount
  let curationStartTime = curationDetail.curationStartTime
  let curationStatus = curationDetail.curationStatus
  let curationSummary = curationDetail.curationSummary


  // 큐레이션 날짜 (진행중, 예정, 종료에 따라 다르게 렌더링)
  let curationDate = null
  if (curationStatus === "INIT") {
      curationDate = <div>{`${curationStartTime[0]}.${(curationStartTime[1]+'').padStart(2, "0")}.${(curationStartTime[2]+'').padStart(2, "0")}${"  "+(curationStartTime[3]) +":" + (curationStartTime[4]) + " 예정"}`}</div>
  } else if (curationStatus === "ON") {
      curationDate = <div>{`${curationStartTime[0]}.${(curationStartTime[1]+'').padStart(2, "0")}.${(curationStartTime[2]+'').padStart(2, "0") + " 진행중"}`}</div>
  } else {
      curationDate = <div>{`${curationStartTime[0]}.${(curationStartTime[1]+'').padStart(2, "0")}.${(curationStartTime[2]+'').padStart(2, "0")}${" 종료됨"}`}</div>
  }

  
  // 진행 중인 큐레이션에는 북마크 활성화
  let isMarked = null
  if (curationStatus === "ON") {
    isMarked = <button>참여하기</button>
  }


  let afterCurationArts = null
  if (curationStatus === "END") {
    <div className="sort_tab">
      <div>전체 작품</div>
      <div>낙찰된 작품</div>
    </div>
  }



  return (
    <div>

      <div className="curation_detail_container">
        {/* 썸네일 사진 */}
        <img src={`${getArtThumbnail(curationThumbnail, userSeq)}`} alt="큐레이션 대표 이미지" />

        {/* 큐레이션 상세 정보 */}
        <div className="curation_detail_content">
          <div>
            <h1>큐레이션 제목</h1>

            {/* <span>진행중인지, 아닌지, 끝났는지에 따라 다르게 보이기</span> */}
            {/* 이건 content로 구현함 */}
            <div className="artist_profile">
              <ProfileImg height="30px" width="30px" url={profileImg} userSeq={userSeq} />              
              <Link to="/mypage/arts">{nickname} <span>작가</span></Link>
            </div>
            <div className="curation_date">
              {curationDate}
            </div>
            <div className="curation_description">
              {curationSummary}
            </div>
          </div>
          <div>
            <div>
              <div className="bookmark">
                <span>북마크 수</span>
                <img src="ArtsPage" alt="" />
                {curationBmCount}
              </div>
            </div>
            <div>
              
              {/* 참여하기는 현재 진행중일 때만*/}
              {isMarked}
              
              <button>북마크</button>
            </div>
          </div>
        </div>
      </div>

      <div className="arts_curation_for">
        <h2>큐레이션 작품</h2>
        {/* 이 탭은 큐레이션 종료된 경우에만 보이기*/}
        {afterCurationArts}
        <div>
          큐레이션에서 사용된 정보들
        </div>
      </div>


    </div>
  )
}

export default CurationsDetail
