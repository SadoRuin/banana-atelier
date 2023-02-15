import React, { useState } from 'react'
import { Link, useLoaderData } from 'react-router-dom';
import {axiosAuth} from '../../_actions/axiosAuth'

import TabMenuComponent from "../../components/commons/TabMenuComponent";
import CurationComponent from '../../components/commons/CurationComponent';

export async function loader() {
  const curationsInitList = await axiosAuth.get(`curations/init`)
    .then(response=>response.data)
    .catch(error=>console.log(error))
  const curationsOnList = await axiosAuth.get('curations/on')
    .then(response=>response.data)
    .catch(error=>console.log(error))
  const curationsEndList = await axiosAuth.get('curations/end')
    .then(response=>response.data)
    .catch(error=>console.log(error))
  return { curationsInitList, curationsOnList, curationsEndList }
}


function CurationsMain() {
  const { curationsInitList, curationsOnList, curationsEndList } = useLoaderData();

  console.log(curationsEndList)

  const [onAirIndex, setOnAirIndex] = useState(0);
  const [initIndex, setInitIndex] = useState(0);
  const [endIndex, setEndIndex] = useState(0);

  function sortByDate (data, keyword) {
    // 오름차순 == 과거순
    if (keyword === "old") {
      return data?.sort((a, b) =>
        a.curationStartTime[0] - b.curationStartTime[0] ||
        a.curationStartTime[1] - b.curationStartTime[1] ||
        a.curationStartTime[2] - b.curationStartTime[2] ||
        a.curationStartTime[3] - b.curationStartTime[3] ||
        a.curationStartTime[4] - b.curationStartTime[4] ||
        a.curationStartTime[5] - b.curationStartTime[5] );
    }

    // 내림차순 == 최신순
    else if (keyword === "new") {
      return data.sort((a, b) =>
        b.curationStartTime[0] - a.curationStartTime[0] ||
        b.curationStartTime[1] - a.curationStartTime[1] ||
        b.curationStartTime[2] - a.curationStartTime[2] ||
        b.curationStartTime[3] - a.curationStartTime[3] ||
        b.curationStartTime[4] - a.curationStartTime[4] ||
        b.curationStartTime[5] - a.curationStartTime[5] );
    }
  }
  function sortByBookmark (data) {
    return data.sort((a, b) => b.curationBmCount - a.curationBmCount);
  }

  const onMenuData = [
    { name: '북마크를 많이 받은 순', content: sortByBookmark(curationsOnList).map((list) => 
      <div key={list.curationSeq}>
        <CurationComponent
              nickname={list.userNickname}
              profileImg={list.profileImg}
              userSeq={list.userSeq}
              curationThumbnail={list.curationThumbnail}
              curationName={list.curationName}
              curationSeq={list.curationSeq}
              curationHit={list.curationHit}
              curationBmCount={list.curationBmCount}
              curationStartTime={list.curationStartTime}
              curationStatus={list.curationStatus}
            />
      </div>) },
    { name: '최신순', content: sortByDate(curationsOnList, "new").map((list) => 
    <div key={list.curationSeq}>
      <CurationComponent
            nickname={list.userNickname}
            profileImg={list.profileImg}
            userSeq={list.userSeq}
            curationThumbnail={list.curationThumbnail}
            curationName={list.curationName}
            curationSeq={list.curationSeq}
            curationHit={list.curationHit}
            curationBmCount={list.curationBmCount}
            curationStartTime={list.curationStartTime}
            curationStatus={list.curationStatus}
          />
    </div>)},
    { name: '오래된 순', content: sortByDate(curationsOnList, "old").map((list) => 
    <div key={list.curationSeq}>
      <CurationComponent
            nickname={list.userNickname}
            profileImg={list.profileImg}
            userSeq={list.userSeq}
            curationThumbnail={list.curationThumbnail}
            curationName={list.curationName}
            curationSeq={list.curationSeq}
            curationHit={list.curationHit}
            curationBmCount={list.curationBmCount}
            curationStartTime={list.curationStartTime}
            curationStatus={list.curationStatus}
          />
    </div>) },
  ];
  const initMenuData = [
    { name: '북마크를 많이 받은 순', content: sortByBookmark(curationsInitList).map((list) => 
      <div key={list.curationSeq}>
        <CurationComponent
              nickname={list.userNickname}
              profileImg={list.profileImg}
              userSeq={list.userSeq}
              curationThumbnail={list.curationThumbnail}
              curationName={list.curationName}
              curationSeq={list.curationSeq}
              curationHit={list.curationHit}
              curationBmCount={list.curationBmCount}
              curationStartTime={list.curationStartTime}
              curationStatus={list.curationStatus}
            />
      </div>) },
    { name: '최신순', content: sortByDate(curationsInitList, "new").map((list) => 
      <div key={list.curationSeq}>
        <CurationComponent
              nickname={list.userNickname}
              profileImg={list.profileImg}
              userSeq={list.userSeq}
              curationThumbnail={list.curationThumbnail}
              curationName={list.curationName}
              curationSeq={list.curationSeq}
              curationHit={list.curationHit}
              curationBmCount={list.curationBmCount}
              curationStartTime={list.curationStartTime}
              curationStatus={list.curationStatus}
            />
      </div>)},
    { name: '오래된 순', content: sortByDate(curationsInitList, "old").map((list) => 
      <div key={list.curationSeq}>
        <CurationComponent
              nickname={list.userNickname}
              profileImg={list.profileImg}
              userSeq={list.userSeq}
              curationThumbnail={list.curationThumbnail}
              curationName={list.curationName}
              curationSeq={list.curationSeq}
              curationHit={list.curationHit}
              curationBmCount={list.curationBmCount}
              curationStartTime={list.curationStartTime}
              curationStatus={list.curationStatus}
            />
      </div> ) },
  ];
  const endMenuData = [
    { name: '북마크를 많이 받은 순', content: sortByBookmark(curationsEndList).map((list) => 
      <div key={list.curationSeq}>
        <CurationComponent
              nickname={list.userNickname}
              profileImg={list.profileImg}
              userSeq={list.userSeq}
              curationThumbnail={list.curationThumbnail}
              curationName={list.curationName}
              curationSeq={list.curationSeq}
              curationHit={list.curationHit}
              curationBmCount={list.curationBmCount}
              curationStartTime={list.curationStartTime}
              curationStatus={list.curationStatus}
            />
      </div>) },
    { name: '최신순', content: sortByDate(curationsEndList, "new").map((list) => 
      <div key={list.curationSeq}>
        <CurationComponent
              nickname={list.userNickname}
              profileImg={list.profileImg}
              userSeq={list.userSeq}
              curationThumbnail={list.curationThumbnail}
              curationName={list.curationName}
              curationSeq={list.curationSeq}
              curationHit={list.curationHit}
              curationBmCount={list.curationBmCount}
              curationStartTime={list.curationStartTime}
              curationStatus={list.curationStatus}
            />
      </div>)},
    { name: '오래된 순', content: sortByDate(curationsEndList, "old").map((list) => 
      <div key={list.curationSeq}>
        <CurationComponent
              nickname={list.userNickname}
              profileImg={list.profileImg}
              userSeq={list.userSeq}
              curationThumbnail={list.curationThumbnail}
              curationName={list.curationName}
              curationSeq={list.curationSeq}
              curationHit={list.curationHit}
              curationBmCount={list.curationBmCount}
              curationStartTime={list.curationStartTime}
              curationStatus={list.curationStatus}
            />
      </div> ) },
  ];

  return (
    <div>

      <div>
        <h2>방송중</h2>
        <TabMenuComponent menuData={onMenuData} index={onAirIndex} setIndex={setOnAirIndex} />
      </div>

      <div>
        <h2>예정</h2>
        <TabMenuComponent menuData={initMenuData} index={initIndex} setIndex={setInitIndex} />
      </div>

      <div>
        <Link to="end" className="link">
          <h2>종료된 큐레이션 {`>`}</h2>
        </Link>
        <TabMenuComponent menuData={endMenuData} index={endIndex} setIndex={setEndIndex} />
      </div>

    </div>
  )
}

export default CurationsMain
