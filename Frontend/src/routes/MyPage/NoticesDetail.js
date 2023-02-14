import React from 'react';
import {useLoaderData, useNavigate} from "react-router-dom";
import {getNotice} from "../../notices";

export function loader({params}) {
  const notice = getNotice(+params.notice_id)
  return notice;
}

function NoticesDetail(props) {
  const noticeData = useLoaderData();
  const navigate = useNavigate();
  console.log('noticeData', noticeData);
  return (
    <div>
      <button onClick={()=>navigate(-1)}>앞으로가기</button>
      <h3>{noticeData.notice_title}</h3>
      <div>{noticeData.notice_content}</div>
    </div>
  );
}

export default NoticesDetail;