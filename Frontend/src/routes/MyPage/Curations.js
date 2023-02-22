import React, { useState } from 'react';
import { useLoaderData, useOutletContext } from "react-router-dom";
import { axiosAuth, axiosReissue } from "../../_actions/axiosAuth";

import CurationItemMyPage from "../../components/MyPage/CurationItemMyPage";
import CurationComponent from "../../components/commons/CurationComponent";
import TabMenuComponent from "../../components/commons/TabMenuComponent";

export async function loader ({params}) {
  const userSeq = params.nickname_user_seq.split('@')[1];
  axiosReissue();

  // 큐레이션 목록
  // 북마크 목록
  // 팔로잉한 작가의 큐레이션 목록
  let curationsList, bookmarkedList, followingCurationsList = null

  await axiosAuth.get(`curations/${userSeq}`)
    .then(response => curationsList = response.data)
    .catch(error => console.log(error))

  await axiosAuth.get(`curations/${userSeq}/following`)
    .then(response => followingCurationsList = response.data)
    .catch(error => console.log(error))

  await axiosAuth.get(`curations/${userSeq}/bookmark`)
    .then(response => bookmarkedList = response.data)
    .catch(error => console.log(error))

  console.log(curationsList)
  console.log(bookmarkedList)
  return [curationsList, bookmarkedList, followingCurationsList];
}

function Curations() {
  const [, isArtist] = useOutletContext();
  const [curationsList, bookmarkedList, followingCurationsList] = useLoaderData();
  const [curationsIndex, setCurationsIndex] = useState(0)

  console.log(curationsList);

  const ArtMenuData = [];
  if (isArtist && curationsList.length) {
    ArtMenuData.push(
      {
        name: '큐레이션',
        content:
          <div className="art-root__arts-container">
            {curationsList.map((curation) =>
              <CurationItemMyPage
                key={`curation-root__curation-list-${curation.curationSeq}`}
                userNickname={curation.userNickname}
                userSeq={curation.userSeq}
                curationThumbnail={curation.curationThumbnail}
                curationSeq={curation.curationSeq}
                profileImg={curation.profileImg}
                curationName={curation.curationName}
              />)}
          </div>
      }
    )
  }
  ArtMenuData.push(
    {
      name: '북마크한 큐레이션',
      content:
        bookmarkedList?
          <div className="art-root__arts-container">
            {bookmarkedList.map((curation) =>
              <CurationComponent
                key={`curation-root__bookmarked-list-${curation.curationSeq}`}
                nickname={curation.artistNickName}
                userSeq={curation.artistSeq}
                curationThumbnail={curation.curationThumbnail}
                curationName={curation.curationName}
                curationSeq={curation.curationSeq}
                profileImg={curation.artistProfileImg}
              />)}
          </div> : <div className="art-root__nothing">북마크한 큐레이션이 없습니다</div>
    }
  )
  ArtMenuData.push(
    {
      name: '팔로잉 작가의 큐레이션',
      content:
        followingCurationsList?
          <div className="art-root__arts-container">
            {followingCurationsList.map((curation) =>
              <CurationComponent
                key={`curation-root__following-curation-list-${curation.curationSeq}`}
                nickname={curation.userNickname}
                userSeq={curation.userSeq}
                curationThumbnail={curation.curationThumbnail}
                curationName={curation.curationName}
                curationSeq={curation.curationSeq}
                profileImg={curation.profileImg}
              />)}
          </div> : <div className="art-root__nothing">작가의 큐레이션이 없습니다</div>
    }
  )

  return (
    <div>
      <div>
        <TabMenuComponent menuData={ArtMenuData} index={curationsIndex} setIndex={setCurationsIndex} />
      </div>
    </div>
  );
}

export default Curations;