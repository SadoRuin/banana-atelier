import React from 'react';
import { Link } from 'react-router-dom';

function Commissions(props) {
  return (
    <div>
      <h3>대화 목록</h3>
      <div>
        <Link to="detail">안읽음</Link>

        <p>안읽음</p>
        <p>커미션 seqNum를 이용해 클릭하면 그 페이지로 이동하기</p>
        <p>읽음</p>
        <p>읽음</p>
        <p>정렬은 안읽은 것 > 읽은 것 순서로 하자</p>
      </div>


    </div>
  );
}

export default Commissions;