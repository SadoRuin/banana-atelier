import React from 'react';

function ArtsIndex(props) {
  return (
    <div>
      {/* 여기는 작가인 경우에만 활성화 해야 함*/}
      <div className='masterpiece'>
        <h3>대표 작품</h3>
        <button className='edit_masterpiece'>대표작품 설정하기</button>
        <div>대표작품 있으면 렌더링, 없으면 설정하라는 문구 출력하자</div>
      </div>

      {/* 여기는 다 활성화 */}
      <div className='favorite_arts'>
        <h3>나의 작품</h3>
        <div>나의 작품이 있으면 다 보이고, 없으면 업로드하면 작가가 될 수 있다고 꼬드기자.</div>
      </div>
    </div>
  );
}

export default ArtsIndex;