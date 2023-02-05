import React from 'react';
import {useNavigate} from "react-router-dom";

function CurationsUpcoming(props) {
  const navigate = useNavigate();
  return (
    <div>
      <div onClick={()=>{navigate(-1)}}>{`<`}</div>
      <h2>진행 예정인 큐레이션</h2>
      <div className="sort_tab">
        <div>북마크를 많이 받은 순</div>
        <div>시작한지 오래된 순</div>
        <div>최근에 시작한 순</div>
      </div>
      <div>
        진행중 큐레이션 컴포넌트들이 올 곳
      </div>
    </div>
  );
}

export default CurationsUpcoming;