import React from 'react'

function ArtsMain() {
  return (
    <div>

      {/* 클릭하면 카테고리 변경되게 하자 */}
      <ul className="category">
        <li id="illustrations" value="일러스트레이션">일러스트레이션</li>
        <li id="digital_arts" value="디지털 아트">디지털 아트</li>
        <li id="crafts" value="공예">공예</li>
        <li id="characters" value="캐릭터 디자인" >캐릭터 디자인</li>
        <li id="fine_arts" value="파인아트">파인아트</li>
        <li id="photography" value="포토그래피">포토그래피</li>
        <li id="typography" value="타이포그래피">타이포그래피</li>
      </ul>

      {/* 정렬 탭도.. */}
      <div className="sort_tab">
        <div>요즘 뜨는 작품</div>
        <div>새로 나온 작품</div>
        <div>인기 작품</div>
      </div>

      <div>여기에 작품 component 렌더링?</div>
      
    </div>
  )
}

export default ArtsMain
