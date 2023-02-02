import React from 'react'

function CommisionsApplyPage() {
  return (
    <div>
      
      <div>
        본문내용 주절주절 쓰는 곳
      </div>

      <div>
        <div>
          <div>
            <h2>
              신청하는 커미션 제목
            </h2>
          </div>
          <div>
            <div>
              {/* 작가 프사 */}
              <img src="CommissionsPage" alt="" />
              <p>작가 닉네임</p>
            </div>
            <div>
              {/* 바나나 */}
              <img src="CommissionsPage" alt="" />
              <p>평점</p>
            </div>
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
        <div>
          <div>
            신청하는 양식 작성하는 곳
          </div>
          <button>커미션 신청하기</button>
          <button>취소하기</button>
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

export default CommisionsApplyPage
