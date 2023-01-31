import React from 'react'

function ArtDetailPage() {
  return (
    <div>

      {/* 작품 사진 */}
      <img src="" alt="" />

      <div>
        <div>
          <div>
            <h1>작품 제목</h1>
          </div>
          <div>
            {/* 작가 프로필 사진 */}
            <img src="" alt="" />
            작가 닉네임
          </div>
          <div>
            등록일
          </div>
          <div>
            작품 설명
          </div>
          <div>
            카테고리
          </div>
        </div>
        <div>
          <div>
            <div>
              <img src="" alt="" />
              작품 본 사람 숫자
            </div>
            <div>
              <img src="" alt="" />
              다운로드한 사람 숫자
            </div>
            <div>
              <img src="" alt="" />
              좋아요 숫자
            </div>
          </div>
          <div>
            <button>좋아요</button>
            <button>다운로드</button>
          </div>
        </div>
      </div>

      <div>
        <div>
          <h2>비슷한 작품</h2>
        </div>
        <div>
          비슷한 작품 컴포넌트들이 올 곳
        </div>
      </div>


    </div>
  )
}

export default ArtDetailPage
