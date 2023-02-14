import React from 'react';
import { Link, Form } from 'react-router-dom';

function CommissionsDetail(props) {
  return (
    <div>
      <Link to="../commissions">돌아가기</Link>
      <div className="채팅 내역">
        <p>안녕하세요</p>
        <p>반가워요</p>
        <p>커미션 신청해욤</p>
      </div>
      <Form method="post" action="" className="입력칸">
        <input type="text" name="send_content" placeholder="내용을 입력하세요" />
        <button type="submit">전송</button>
      </Form>
    </div>
  );
}

export default CommissionsDetail;