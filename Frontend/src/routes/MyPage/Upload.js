import React from 'react';
import { Form, useNavigate } from 'react-router-dom'

// 모달창으로 구현해야댐
function Upload(props) {
  const navigate = useNavigate();
  return (
    <div>
      <h3>작품 업로드</h3>
      <Form method="POST" action="#">
        {/* 작품 업로드하는 무언가 */}
        <label htmlFor="title">작품 제목</label>
        <input type="text" name="title" id="title" placeholder="작품 제목"/>

        <div className="category">
          <div>카테고리</div>
          <label><input type="checkbox" name="category" value="illustrations" />일러스트레이션</label>
          <label><input type="checkbox" name="category" value="digital_arts" />디지털 아트</label>
          <label><input type="checkbox" name="category" value="crafts" />공예</label>
          <label><input type="checkbox" name="category" value="characters" />캐릭터디자인</label>
          <label><input type="checkbox" name="category" value="fine arts" />파인 아트</label>
          <label><input type="checkbox" name="category" value="photography" />포토그래피</label>
          <label><input type="checkbox" name="category" value="typography" />타이포그래피</label>
        </div>

        <label htmlFor="description">작품 설명</label>
        <textarea name="description" id="description" cols="30" rows="10"></textarea>

        <label htmlFor="price">작품 가격</label>
        <input type="number" id="price" name="price" placeholder="작품 경매에 쓰일 가격입니다."/>

        <div>
          <button type="submit">제출하기</button>
          <button type="button" onClick={()=>{ navigate(-1) }}>취소</button>
        </div>
      </Form>
    </div>
  );
}

export default Upload;