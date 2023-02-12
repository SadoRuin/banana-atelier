import React from 'react';
import { Form, useNavigate, redirect } from 'react-router-dom'
import {axiosAuth, axiosReissue} from "../../_actions/axiosAuth";


export async function action({request, params}) {

  // 이미지파일 제외 내가 작성한 내용은 key랑 value로 들어있음 artData에...
  const formData = await request.formData();
  const artData = Object.fromEntries(formData);
  delete artData.artFile;
  artData.artCategorySeq = +artData.artCategorySeq

  // 혹시 JSON으로 만들어야 하나 싶어서 만들어봣음??? 나도모름
  // const artJSON = JSON.stringify(artData);
  // console.log(typeof artJSON)

  // 이건 내가 업로드 한 파일 정보
  const artFile= document.querySelector('#artFile').files[0];

  // 이건 body에 들어가는 값
  // const body = {
  //   "artFile": artFile,
  //   "artRequest": JSON.stringify(artData)
  // }

  const body = new FormData();
  body.append('artFile', artFile);
  body.append('artRequest', new Blob([JSON.stringify(artData)], {type: "application/json"}))
  console.log(body);

  axiosReissue();
  await axiosAuth.post('arts', body, {
    headers : {
      'content-type' : 'multipart/form-data'
    }
  } )
    .then((response) => {
      console.log(response)
    })
    .catch(error => console.log(error))

  const [nickname, userSeq] = params.nickname_user_seq.split('@');
  const encodedNickname = encodeURI(nickname)
  return redirect(`/${encodedNickname}@${userSeq}`)
}

function Upload() {
  const navigate = useNavigate();
  return (
    <div>
      <h3>작품 업로드</h3>
      <Form method="POST">
        {/* 작품 업로드하는 무언가 */}

        <label htmlFor="title">작품 사진 올리기</label>
        <input type="file" accept="image/*" name="artFile" id="artFile" required/>

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