import React from 'react'

function CommissionsMain() {
  return (
    <div>

      {/* 클릭하면 카테고리 변경되게 하자 */}
      <ul className="category">
        <li id="illustrations">일러스트레이션</li>
        <li id="digital_arts">디지털 아트</li>
        <li id="crafts">공예</li>
        <li id="characters">캐릭터디자인</li>
        <li id="fine_arts">파인 아트</li>
        <li id="photography">포토그래피</li>
        <li id="typography">타이포그래피</li>
      </ul>

      <div className="hot_artists">
        <h2>HOT 아티스트🔥</h2>
        <div>
          HOT 아티스트들 올 컴포넌트 (with 캐러셀)
        </div>
      </div>

      <div>
        <h2>모든 아티스트</h2>
        <div className="sort_tab">
          <div>별점 높은 작가</div>
          <div>인기 많은 작가</div>
          <div>신예 작가</div>
          <div>가격 낮은 순</div>
          <div>가격 높은 순</div>
        </div>
        <div>
          아티스트들 올 컴포넌트
        </div>
      </div>

    </div>
  )
}

export default CommissionsMain
