import React from 'react'
import { Link, useLoaderData } from 'react-router-dom';
import customAxios from '../../_actions/customAxios'

export function loader() {
  const curationsList = customAxios().get(`curations/main`);
  return curationsList;
}

function CurationsMain() {
  const curationsList = useLoaderData();
  console.log(curationsList);
  return (
    <div>

      {/* 클릭하면 카테고리 변경되게 하자 */}
      {/* 큐레이션에서 카테고리 빼기로 했음 */}
      {/*<ul className="category">*/}
      {/*  <li id="illustrations">일러스트레이션</li>*/}
      {/*  <li id="digital_arts">디지털 아트</li>*/}
      {/*  <li id="crafts">공예</li>*/}
      {/*  <li id="characters">캐릭터디자인</li>*/}
      {/*  <li id="fine_arts">파인 아트</li>*/}
      {/*  <li id="photography">포토그래피</li>*/}
      {/*  <li id="typography">타이포그래피</li>*/}
      {/*</ul>*/}

      <div>
        <Link to="on_air">
          <h2>진행중인 큐레이션</h2>
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

      <div>
        <Link to="upcoming">
          <h2>진행 예정인 큐레이션</h2>
        </Link>
        <div className="sort_tab">
          <div>북마크를 많이 받은 순</div>
          <div>시작한지 오래된 순</div>
          <div>최근에 시작한 순</div>
        </div>
        <div>
          진행 예정인 큐레이션 컴포넌트들이 올 곳
        </div>
      </div>

      <div>
        <Link to="finish">
          <h2>종료된 큐레이션</h2>
        </Link>
        <div className="sort_tab">
          <div>북마크를 많이 받은 순</div>
          <div>시작한지 오래된 순</div>
          <div>최근에 시작한 순</div>
        </div>
        <div>
          종료된 큐레이션 컴포넌트들이 올 곳
        </div>
      </div>

    </div>
  )
}

export default CurationsMain
