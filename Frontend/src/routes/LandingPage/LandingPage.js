import React from 'react';
import { Link } from 'react-router-dom';

function LandingPage() {
  return (
    <div>

      <div>
        <h1><Link to="curations">큐레이션🍌</Link></h1>
        <p>이 곳에는 현재 진행중인/진행 예정인 큐레이션 컴포넌트가 들어간다</p>
      </div>

      <div>
        <h1><Link to="commissions">요즘 뜨는 커미션 작가💰</Link></h1>
        <p>이 곳에는 요즘 뜨는 커미션 작가가 들어간다</p>
      </div>

      <div>
        <h1><Link to="arts">트렌딩🔥</Link></h1>
        <p>이 곳에는 요즘 인기가 많아진 작품들이 들어간다.</p>
      </div>
      
    </div>
  );
}

export default LandingPage
