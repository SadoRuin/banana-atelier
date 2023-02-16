import React, { useState } from 'react'
import { Link, useLoaderData } from 'react-router-dom';
import {axiosAuth} from '../../_actions/axiosAuth'

import { Navigation, Pagination, Scrollbar, A11y } from 'swiper';
import { Swiper, SwiperSlide } from 'swiper/react';
import "swiper/css"; //basic
import "swiper/css/navigation";
import "swiper/css/pagination";

import TabMenuComponent, {TabContent, TabMenu} from "../../components/commons/TabMenuComponent";
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
    { 
      name: '북마크를 많이 받은 순', 
      content:
        sortByBookmark(curationsOnList).map((list) =>
          <SwiperSlide key={`on-curations__bm-${list.curationSeq}`}>
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
          </SwiperSlide>)
    },
    { 
      name: '최신순', 
      content:
          sortByDate(curationsOnList, "new").map((list) =>
            <SwiperSlide key={`on-curations__new-${list.curationSeq}`}>
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
            </SwiperSlide>)
    },
    { 
      name: '오래된 순', 
      content:
          sortByDate(curationsOnList, "old").map((list) =>
            <SwiperSlide key={`on-curations__old-${list.curationSeq}`}>
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
            </SwiperSlide>)
    }
  ];
  const initMenuData = [
    { 
      name: '북마크를 많이 받은 순', 
      content: 
        sortByBookmark(curationsInitList).map((list) =>
          <SwiperSlide key={`init-curations__bm-${list.curationSeq}`}>
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
          </SwiperSlide>)
    },
    { 
      name: '최신순', 
      content:
        sortByDate(curationsInitList, "new").map((list) =>
          <SwiperSlide key={`init-curations__new-${list.curationSeq}`}>
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
        </SwiperSlide>)
    },
    { 
      name: '오래된 순', 
      content:
        sortByDate(curationsInitList, "old").map((list) =>
          <SwiperSlide key={`init-curations__old-${list.curationSeq}`}>
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
          </SwiperSlide>)
    }
  ];
  const endMenuData = [
    {
      name: '북마크를 많이 받은 순',
      content:
      <div className="grid__main-components">
        {sortByBookmark(curationsEndList).map((list) =>
          <div key={`end-curations__bm-${list.curationsSeq}`}>
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
          </div>)}
        </div>
    },
    {
      name: '최신순',
      content:
      <div className="grid__main-components">
        {sortByDate(curationsEndList, "new").map((list) =>
          <div key={`end-curations__new-${list.curationsSeq}`}>
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
        </div>)}
      </div>
    },
    {
      name: '오래된 순',
      content:
      <div className="grid__main-components">
        {sortByDate(curationsEndList, "old").map((list) =>
          <div key={`end-curations__old-${list.curationsSeq}`}>
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
          </div>)}
      </div>
    }
  ];

  return (
    <div>
      <div>
        <h2>큐레이션 진행 중</h2>
          <TabMenu>
            { onMenuData.map((tab, idx) =>
              <li
                className={idx === onAirIndex ? "submenu focused" : "submenu" }
                onClick={ () => setOnAirIndex(idx) }
              >
                {tab.name}
              </li>
            )}
          </TabMenu>
          <Swiper
            // install Swiper modules
            modules={[Navigation, Pagination, Scrollbar, A11y]}
            spaceBetween={25}
            breakpoints={{
              998: {
                slidesPerView: 4,
                slidesPerGroup: 4
              },
              768: {
                slidesPerView: 3,
                slidesPerGroup: 3
              }
            }}
            navigation
            pagination={{ clickable: true }}
            loop={true}
            scrollbar={{ draggable: true }}
          >
            <TabContent>
              { onMenuData[onAirIndex].content }
            </TabContent>
          </Swiper>
      </div>

      <div>
        <h2>큐레이션 예정</h2>
        <TabMenu>
          { initMenuData.map((tab, idx) =>
            <li
              className={idx === initIndex ? "submenu focused" : "submenu" }
              onClick={ () => setInitIndex(idx) }
            >
              {tab.name}
            </li>
          )}
        </TabMenu>
        <Swiper
          // install Swiper modules
          modules={[Navigation, Pagination, Scrollbar, A11y]}
          spaceBetween={25}
          breakpoints={{
            998: {
              slidesPerView: 4,
              slidesPerGroup: 4
            },
            768: {
              slidesPerView: 3,
              slidesPerGroup: 3
            }
          }}
          navigation
          pagination={{ clickable: true }}
          loop={true}
          scrollbar={{ draggable: true }}
        >
          <TabContent>
            { initMenuData[initIndex].content }
          </TabContent>
        </Swiper>
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

export default (CurationsMain)
