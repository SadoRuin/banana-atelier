import React from 'react';
import { useLocation, Form, useNavigate } from "react-router-dom";
import { YellowBtn, RedBtn } from "../../components/commons/buttons";
import "./EditProfile.css";

// 모달창 구현해야됨!!!
function EditProfile({ state }) {
  const location = useLocation();
  const navigate = useNavigate();
  console.log(location);
  return (
    <div>
      {/* 업데이트 는 좀 나중에 해야할란가 */}
      <Form method="post">
        {/* 프로필 이미지는 나중에 npm 에서 drag 모듈을 받자 */}
        <label style={{display: "flex"}} htmlFor="nickname">닉네임</label>
        <input style={{margin: "0px 0px 10px 0px"}} type="text" id="nickname" name="nickname" />
        <button style={{marginLeft: "10px"}} className='nickname_double_check'>중복 확인</button>

        <div>
          <label style={{display: "flex"}} htmlFor="description">한 줄 소개</label>
          <input style={{margin: "0px 0px 10px 0px"}} type="text" id='description' name="description" />
        </div>
        
        {/* 내가 작가면 sns 링크 달 수 있게 하자*/}
        <p className="SNS-form" style={{fontSize: "25px"}}>SNS 관리</p>
        <div>
          <label htmlFor="email">이메일</label>
        </div>
        <input className="label-of-form" type="text" id="email" name="email" placeholder='email' />
        <div>
          <label htmlFor="instagram">인스타그램</label>
        </div>
        <input className="label-of-form" type="text" id="instagram" name="instagram" placeholder='instagram' />
        <div>
          <label htmlFor="twitter">트위터</label>
        </div>
        <input className="label-of-form" type="text" id="twitter" name="twitter" placeholder='twitter' />
        <div>
          <label htmlFor="youtube">유튜브</label>
        </div>
        <input className="label-of-form" type="text" id="youtube" name="youtube" placeholder='youtube' />
        <div>
          <label htmlFor="homepage">홈페이지</label>
        </div>
        <input className="label-of-form" type="text" id="homepage" name="homepage" placeholder='homepage' />
        
        <div style={{display: "flex", marginTop: "20px"}}>
          <YellowBtn style={{width: "80px", marginLeft:"0px", marginRight:"20px"}} type="submit"> 제출하기</YellowBtn>
          <RedBtn style={{width: "80px"}} onClick={()=>{ navigate(-1) }}>취소</RedBtn>

          {/* <button type="submit">수정하기</button>
          <button type="button" onClick={()=>{ navigate(-1) }}>취소</button> */}
        </div>
      </Form>
    </div>
  );
}

export default EditProfile;