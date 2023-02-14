import React from 'react';
import { Link, useNavigate } from "react-router-dom";
import { axiosAuth } from '../../_actions/axiosAuth';

export async function loader ({params}) {
  // const curationsSeq = params.curation_seq
  const curationList = await axiosAuth.get('curations/on')
    .then(response => response.data)
    .catch(error => console.log(error))

  console.log(curationList);
  return null
}

function CurationsOnAir(props) {
  const navigate = useNavigate();
  return (
    <div>
      <div onClick={()=>{navigate(-1)}}>{`<`}</div>
      <h2>진행중인 큐레이션</h2>
    
      <Link to="CurationsOpenVidu">
        <h3>Openvidu</h3>
      </Link>

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

export default CurationsOnAir;