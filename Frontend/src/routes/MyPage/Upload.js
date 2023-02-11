import React from 'react';
import { Form, useNavigate } from 'react-router-dom'
import {axiosAuth, axiosReissue} from "../../_actions/axiosAuth";

export async function action({request}) {
  const formData = await request.formData();
  const updates = Object.fromEntries(formData);
  console.log(updates);
  // 이렇게 하면 내가 입력한 정보가 객체로 잘 넘어오거든?
  // {
  //   artCategorySeq : "3"
  //   artDescription : "ㄷㄷㄷㄷㄷㄷㄷㄷㄷㄷㄷㄷㄷㄷㄷㄷㄷㄷㄷㄷㄷ"
  //   artFile : "6905d2b5d05b4d0b27ec8731cb8252fa7f9f127ae3ca5dc7f0f6349aebcdb3c4.png"
  //   artName : "ㄷㄷㄷㄷ"
  // }
  // 넘어오는게 다 문자열이라 숫자로 바꿀건 바꾸고...
  // 이렇게 가져온 정보를 어떻게 해서 어떻게 axios로 보내야 할지 모르겠어!!

  axiosReissue();
  await axiosAuth('arts');
}

function Upload() {
  const navigate = useNavigate();
  return (
    <div>
      <h3>작품 업로드</h3>
      <Form method="POST">
        {/* 작품 업로드하는 무언가 */}

        <label htmlFor="title">작품 사진 올리기</label>
        <input type="file" accept="image/*" name="artFile" id="title" required/>

        <label htmlFor="title">작품 제목</label>
        <input type="text" name="artName" id="title" placeholder="작품 제목" required/>

        {/* 하나만 입력받는다면 나중에 radio로 바꿔야함~ */}
        <div className="category">
          <div>카테고리</div>
          <label><input type="checkbox" name="artCategorySeq" value="1" />일러스트레이션</label>
          <label><input type="checkbox" name="artCategorySeq" value="3" />디지털 아트</label>
          <label><input type="checkbox" name="artCategorySeq" value="2" />캐릭터디자인</label>
          <label><input type="checkbox" name="artCategorySeq" value="4" />타이포그래피</label>
          <label><input type="checkbox" name="artCategorySeq" value="5" />포토그래피</label>
          <label><input type="checkbox" name="artCategorySeq" value="6" />파인 아트</label>
          <label><input type="checkbox" name="artCategorySeq" value="7" />공예</label>
        </div>

        <label htmlFor="description">작품 설명</label>
        <textarea name="artDescription" id="description" cols="30" rows="10" required></textarea>

        {/* 아직 백에서 안만들었으니 pass */}
        {/*<label htmlFor="price">작품 가격</label>*/}
        {/*<input type="number" id="price" name="price" placeholder="작품 경매에 쓰일 가격입니다."/>*/}

        <div>
          <button type="submit">제출하기</button>
          <button type="button" onClick={()=>{ navigate(-1) }}>취소</button>
        </div>
      </Form>
    </div>
  );
}

export default Upload;