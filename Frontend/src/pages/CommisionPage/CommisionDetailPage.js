import React from 'react'

function CommisionDetailPage() {
  return (
    <div>
      
      <div>
        본문 내용 주절주절... 쓰는 곳
      </div>

      <div>
        <div>
          <div>
            <h2>커미션 제목</h2>
          </div>
          <div>
            <div>
              {/* 프로필 사진 */}
              <img src="" alt="" />
              <p>작가 닉네임</p>
            </div>
            <div>
              {/* 바나나 */}
              <img src="" alt="" />
              <p>평점</p>
            </div>
            <div>
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
        </div>
        <div>
          <div>
            <p>커미션 가격</p>
            <p>50,000~ 이런 식으로 적기</p>
          </div>
          <button>커미션 신청하기</button>
        </div>
      </div>

      <div>
        <div>
          비슷한 작가
        </div>
        <div>
          비슷한 작가들 올 컴포넌트
        </div>
      </div>

    </div>
  )
}

export default CommisionDetailPage
