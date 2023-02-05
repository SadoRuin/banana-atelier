import React from 'react'
import ProfileImg from "../../components/ProfileImg";
import {Link} from "react-router-dom";

function CurationsDetail() {
  return (
    <div>

      <div className="curation_detail_container">
        {/* 썸네일 사진 */}
        <img src="https://img1.daumcdn.net/thumb/R1280x0.fjpg/?fname=http://t1.daumcdn.net/brunch/service/user/byFa/image/QAxZeu0rXS7f_v8lzOWLDp15jfo" alt="큐레이션 대표 이미지" />

        {/* 큐레이션 상세 정보 */}
        <div className="curation_detail_content">
          <div>
            <h1>큐레이션 제목</h1>
            {/* <span>진행중인지, 아닌지, 끝났는지에 따라 다르게 보이기</span> */}
            <div className="artist_profile">
              <ProfileImg src="https://item.kakaocdn.net/do/b9df681f72876cbace33e39bb375f0ea8f324a0b9c48f77dbce3a43bd11ce785" height="30px" />
              <Link to="/mypage/arts">신석호 <span>작가</span></Link>
            </div>
            <div className="curation_date">2023.01.19</div>
            <div className="curation_description">
              큐레이션 설명 주절주절주절
            </div>
            <div className="category">
              카테고리 칸은 이 작품의 카테고리 number 보면 됨
            </div>
          </div>
          <div>
            <div>
              {/* 시청자 수는  upcoming 이외 전부 표시*/}
              <div className="views">
                <span>시청자 수</span>
                <img src="ArtsPage" alt="" />
                32
              </div>
              <div className="bookmark">
                <span>북마크 수</span>
                <img src="ArtsPage" alt="" />
                32
              </div>
            </div>
            <div>
              {/* 참여하기는 현재 진행중일 때만*/}
              <button>참여하기</button>
              <button>북마크</button>
            </div>
          </div>
        </div>
      </div>

      <div className="arts_curation_for">
        <h2>큐레이션 작품</h2>
        {/* 이 탭은 큐레이션 종료된 경우에만 보이기*/}
        <div className="sort_tab">
          <div>전체 작품</div>
          <div>낙찰된 작품</div>
        </div>
        <div>
          큐레이션에서 사용된 정보들
        </div>
      </div>


    </div>
  )
}

export default CurationsDetail
