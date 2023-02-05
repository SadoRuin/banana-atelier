import React, {useState} from 'react'
import ProfileImg from "../../components/ProfileImg";
import {Link, Form} from "react-router-dom";

function CommissionsDetailPage() {
  const [isRegisterOpen, setIsRegisterOpen] = useState(false);
  return (
    <div>
      
      <div>
        본문 내용 주절주절... 쓰는 곳
      </div>

      <div className="commissions_container">
        <div>
          <h2>커미션 제목</h2>
          <div className="artist_profile">
            <ProfileImg src="https://item.kakaocdn.net/do/b9df681f72876cbace33e39bb375f0ea8f324a0b9c48f77dbce3a43bd11ce785" height="30px" />
            <Link to="/mypage/arts">신석호 <span>작가</span></Link>
            <div className="vote_average">
              <img src="" alt="bananaImg" />
              <p>4.5</p>
            </div>
          </div>
          <div className="commissions_meeting_info">
            <div>
              <p>미팅 1회당 소요 시간</p>
              <p>시간 적기</p>
            </div>
            <div>
              <p>미팅 횟수</p>
              <p>실제 횟수</p>
            </div>
          </div>
          <div>
            카테고리
          </div>
        </div>

        <div>
          <div>
            <p>커미션 가격</p>
            <p>50,000~ 이런 식으로 적기</p>
          </div>
          { isRegisterOpen ?
            <Form method="post">
              <textarea name="commission_content" id="commission_content" cols="30" rows="10"></textarea>
              <button type="submit">커미션 신청하기</button>
              <button onClick={()=>{ setIsRegisterOpen((prev) => !prev)}}>취소</button>
            </Form> :
            <button onClick={()=>{ setIsRegisterOpen((prev) => !prev)}}>커미션 신청하기</button>
          }

        </div>
      </div>

      <div className="familiar_artists">
        <h2>비슷한 작가</h2>
        <div>
          비슷한 작가 컴포넌트들이 올 곳
        </div>
      </div>

    </div>
  )
}

export default CommissionsDetailPage
