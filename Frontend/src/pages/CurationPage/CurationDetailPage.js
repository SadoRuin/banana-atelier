import React from 'react'

function CurationDetailPage() {
  return (
    <div>

      {/* 작품 사진 */}
      <img src="" alt="" />

      <div>
        <div>
          <div>
            <h1>큐레이션 제목</h1>
            ON-AIR / UPCOMING / FINISH
          </div>
          <div>
            {/* 작가 프로필 사진 */}
            <img src="" alt="" />
            작가 닉네임
          </div>
          <div>
            큐레이션 시작 날짜 & 시간 
          </div>
          <div>
            큐레이션 설명
          </div>
          <div>

            {/* 진행중인 경우, 종료된 경우 */}
            <div>
              <img src="" alt="" />
              시청자 숫자
            </div>
            <div>
              <img src="" alt="" />
              북마크 숫자
            </div>

            {/* 진행 예정인 경우 */}
            <div>
              <img src="" alt="" />
              북마크 숫자
            </div>

          </div>
          <div>
            카테고리
          </div>
        </div>
        <div>
          {/* 참여하기는 진행중인 경우만 */}
          <button>참여하기</button>
          <button>북마크</button>
        </div>
      </div>

      <div>
        <div>
          <h2>큐래이션 작품</h2>
        </div>
        
        {/* 종료된 경우만 */}
        <div>
          전체 작품 {} 낙찰된 작품 {}
        </div>
        
        <div>
          큐레이션 작품 컴포넌트들이 올 곳
        </div>
      </div>


    </div>
  )
}

export default CurationDetailPage
