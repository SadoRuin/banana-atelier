import React from 'react';
import { Form, useNavigate, redirect } from 'react-router-dom'
import {axiosAuth, axiosReissue} from "../../_actions/axiosAuth";

import './Upload.css'

export async function action({request, params}) {

  // 이미지파일 제외 내가 작성한 내용은 key랑 value로 들어있음 artData에...
  const formData = await request.formData();
  const artData = Object.fromEntries(formData);
  delete artData.artFile;
  artData.artCategorySeq = +artData.artCategorySeq


  // 이건 내가 업로드 한 파일 정보
  const artFile= document.querySelector('#upload__img').files[0];

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

  const handleReadURL = (input) => {
    if (input.target.files && input.target.files[0]) {
      let reader = new FileReader();
      reader.onload = function(e) {
        const preview = document.getElementById('upload__preview')
        preview.style.backgroundImage = `url(${e.target.result})`;
        preview.style.backgroundSize = 'contain';
        preview.style.backgroundRepeat = 'no-repeat'
        preview.style.border= '0';
        preview.innerHTML = "";
      };
      reader.readAsDataURL(input.target.files[0]);
    }
    else {
      const preview = document.getElementById('upload__preview')
      preview.style.backgroundImage = `url(")`;
      preview.style.backgroundSize = 'contain';
      preview.style.backgroundRepeat = 'no-repeat'
      preview.style.border= '2px dotted #656565';
      preview.innerHTML = "작품 미리보기";

    }
  }

  return (
    <div>
      <h3>작품 업로드</h3>
      <Form method="POST" className="upload__container">
        {/* 작품 업로드하는 무언가 */}
        <div className="upload__form">
          <label htmlFor="upload__img" className="upload__img">
            <div id="upload__preview">작품 미리보기</div>
            <input
              type="file"
              accept="images/*"
              name="artFile"
              id="upload__img"
              onChange={(e) => {handleReadURL(e)}}
              required
            />
          </label>

          <div className="upload__info" >
            <div className="upload__info-container">
              <label htmlFor="title" className="upload__bold-label">작품 제목</label>
              <input type="text" name="artName" id="title" placeholder="작품 제목" required/>
            </div>

            {/* 하나만 입력받는다면 나중에 radio로 바꿔야함~ */}
            <div className="upload__info-container" >
              <div className="upload__bold-label">카테고리</div>
              <label><input type="radio" name="artCategorySeq" value="1" />일러스트레이션</label>
              <label><input type="radio" name="artCategorySeq" value="3" />디지털 아트</label>
              <label><input type="radio" name="artCategorySeq" value="2" />캐릭터디자인</label>
              <label><input type="radio" name="artCategorySeq" value="4" />타이포그래피</label>
              <label><input type="radio" name="artCategorySeq" value="5" />포토그래피</label>
              <label><input type="radio" name="artCategorySeq" value="6" />파인 아트</label>
              <label><input type="radio" name="artCategorySeq" value="7" />공예</label>
            </div>
          </div>

          <div className="upload__description">
            <label htmlFor="description" className="upload__bold-label">작품 설명</label>
            <textarea name="artDescription" id="description" cols="30" rows="10" required></textarea>
          </div>
        </div>

        <div className="upload__btns">
          <button type="submit">제출하기</button>
          <button type="button" onClick={()=>{ navigate(-1) }}>취소</button>
        </div>
      </Form>
    </div>
  );
}

export default Upload;