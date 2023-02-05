import React from 'react'
import { Link } from 'react-router-dom'
import ProfileImg from "../../components/ProfileImg";

function ArtsDetailPage() {
  return (
    <div>

      <div className="arts_detail_container">
        {/* 작품 사진 */}
        <img src="https://img1.daumcdn.net/thumb/R1280x0.fjpg/?fname=http://t1.daumcdn.net/brunch/service/user/byFa/image/QAxZeu0rXS7f_v8lzOWLDp15jfo" alt="작품 이미지" />

        {/* 작품 상세 정보 */}
        <div className="arts_detail_content">
          <div>
            <h1>작품 제목</h1>
            <div className="artist_profile">
              <ProfileImg src="https://item.kakaocdn.net/do/b9df681f72876cbace33e39bb375f0ea8f324a0b9c48f77dbce3a43bd11ce785" height="30px" />
              <Link to="/mypage/arts">신석호 <span>작가</span></Link>
            </div>
            <div className="upload_date">2023.01.19</div>
            <div className="arts_description">
              작품 설명
            </div>
            <div>
              카테고리 칸은 이 작품의 카테고리 number 보면 됨
            </div>
          </div>
          <div>
            <div>
              <div className="views">
                <img src="ArtsPage" alt="" />
                작품 본 사람 숫자
              </div>
              <div className="downloaded">
                <img src="ArtsPage" alt="" />
                다운로드한 사람 숫자
              </div>
              <div className="likes">
                <img src="ArtsPage" alt="" />
                좋아요 숫자
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

export default ArtsDetailPage
