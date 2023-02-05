import React from 'react';
import { Form } from 'react-router-dom'

function CommissionsRegister(props) {
  return (
    <div>
      <h3>커미션 글 등록</h3>
      <Form method="post">
        <label>커미션 제목
          <input type="text" name="commissions_title" placeholder="커미션 제목"/>
        </label>
        <label>커미션 최저 가격
          <input type="number" name="commissions_min" placeholder="가격을 적어주세요"/>
        </label>
        <label>미팅 1회당 소요 시간
          <input type="number" name="commissions_time" placeholder="미팅 1회당 소요 시간"/>
        </label>
        <label>미팅 횟수
          <input type="number" name="commissions_"/>
        </label>
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
        <label>커미션 개요
          <textarea name="commissions_content" id="commissions_content" cols="30" rows="10"></textarea>
        </label>
        <button type="submit">등록하기</button>
      </Form>
    </div>
  );
}

export default CommissionsRegister;