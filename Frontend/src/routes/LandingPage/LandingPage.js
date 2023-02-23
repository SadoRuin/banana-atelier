import React from "react";
import { useSelector, useDispatch } from "react-redux";
import { Link, useLoaderData } from "react-router-dom";

import { Navigation, Pagination, Scrollbar, A11y } from "swiper";
import { Swiper, SwiperSlide } from "swiper/react";
import "swiper/css"; //basic
import "swiper/css/navigation";
import "swiper/css/pagination";

import axiosCustom from "../../_actions/axiosCustom";
import { landingRenderingReset } from "../../_actions/user_action";
import ArtComponent from "../../components/commons/ArtComponent";
import CurationComponent from "../../components/commons/CurationComponent";

import "../MyPage/ArtsRoot.css";

export async function loader() {
  let artsList, curationOnList, curationInitList;
  await axiosCustom.get('arts/all' )
    .then(response => artsList = response.data)
    .catch(() => artsList = [])
  await axiosCustom.get('curations/on' )
    .then(response => curationOnList = response.data)
    .catch(() => curationOnList = [])
  await axiosCustom.get('curations/init' )
    .then(response => curationInitList = response.data)
    .catch(() => curationInitList = [])

  const curationsList = [...curationOnList, ...curationInitList];
  return [artsList, curationsList];
}

function LandingPage() {
  const dispatch = useDispatch();
  const landingStatus = useSelector((state) => state.user.landing_status);
  if (landingStatus === 2) {
    dispatch(landingRenderingReset()).then(() => window.location.reload());
  } else if (landingStatus === 3) {
    dispatch(landingRenderingReset()).then(() => window.location.reload());
  }

  const [artsList, curationsList] = useLoaderData();

  return (
    <div>
      <div>
        <h1><Link className="link" to="curations">큐레이션🍌</Link></h1>
        <Swiper
          // install Swiper modules
          modules={[Navigation, Pagination, Scrollbar, A11y]}
          spaceBetween={25}
          breakpoints={{
            998: {
              slidesPerView: 4,
              slidesPerGroup: 4,
            },
            768: {
              slidesPerView: 3,
              slidesPerGroup: 3,
            },
          }}
          navigation
          loop={true}
          scrollbar={{ draggable: true }}
        >
          { curationsList.length ? (
            curationsList.map((curation) => (
              <SwiperSlide key={`landing__curation-${curation.curationSeq}`}>
                <CurationComponent
                  nickname={curation.userNickname}
                  profileImg={curation.profileImg}
                  userSeq={curation.userSeq}
                  curationThumbnail={curation.curationThumbnail}
                  curationName={curation.curationName}
                  curationSeq={curation.curationSeq}
                  curationHit={curation.curationHit}
                  curationBmCount={curation.curationBmCount}
                  curationStartTime={curation.curationStartTime}
                  curationStatus={curation.curationStatus}
                />
              </SwiperSlide>
            ))
          ) : (
            <div className="art-root__nothing">진행중이거나 예정인 큐레이션이 없습니다</div>
          )}
        </Swiper>
      </div>

      <div>
        <h1><Link className="link" to="arts">트렌딩🔥</Link></h1>
        <div className="grid__main-components">
          {artsList.map((art) => (
            <ArtComponent
              key={`landing__art-${art.artSeq}`}
              nickname={art.nickname}
              profileImg={art.profileImg}
              userSeq={art.userSeq}
              artThumbnail={art.artThumbnail}
              artName={art.artName}
              artSeq={art.artSeq}
              artHit={art.artHit}
              artLikeCount={art.artLikeCount}
            />
          ))}
        </div>
      </div>
    </div>
  );
}

export default LandingPage;
