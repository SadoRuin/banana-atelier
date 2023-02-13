import React, { useState } from 'react';
import { useLoaderData } from "react-router-dom";
import { axiosAuth, axiosReissue } from "../../_actions/axiosAuth";
import {TabMenu, TabContent} from "../../components/commons/TabMenuComponent";

export async function loader ({params}) {
  const [nickname, userSeq] = params.nickname_user_seq.split('@');

  axiosReissue();

  // 나의 공지, 없으면 null
  const userNotices = await axiosAuth(`notices/${userSeq}`)
    .then(response => response.data)
    .catch(() => null)
  // 팔로잉 한 작가들의 공지, 없으면 null
  const followingNotices = await axiosAuth(`notices/${userSeq}/following`)
    .then(response => response.data)
    .catch(() => null)
  // 이 마이페이지 주인이 작가인지 알기위해 필요함
  const userData = await axiosAuth.get(`users/profile/${userSeq}`)
    .then(response => response.data)
    .catch(error => console.log(error))

  console.log(userNotices);
  console.log(followingNotices);
  return [nickname, userSeq, userData, userNotices, followingNotices]
}


function NoticesRoot() {
  const [nickname, userSeq, userData, userNotices, followingNotices] = useLoaderData();
  const [noticeIndex, setNoticeIndex] = useState(0)
  const isMyPage = nickname === localStorage.getItem('nickname');
  const isArtist = userData.role === "ROLE_ARTIST"

  console.log(userSeq)
  const noticesMenuData = [];
  if (isArtist) {
    noticesMenuData.push({
        name: "공지사항",
        content:
        <div>
          {isMyPage ? <div>공지사항 등록 버튼</div> : null}
          {userNotices ?
            userNotices.map((notice) => <div>{notice.noticeTitle}</div>)
            :
            <div>등록된 공지사항이 없습니다.</div>}
        </div>
      })
  }
  if (isMyPage) {
    noticesMenuData.push({
      name: "작가들 공지",
      content: followingNotices?
        followingNotices.map((notice) => <div>{notice.noticeTitle}</div> )
        :
        <div>팔로잉한 작가가 없습니다.</div>
    })
  }


  return (
    <div>
      <div>
        <TabMenu>
          { noticesMenuData.map((tab, idx) =>
            <li
              className={idx === noticeIndex ? "submenu focused" : "submenu" }
              onClick={ () => setNoticeIndex(idx) }
            >
              {tab.name}
            </li>) }
        </TabMenu>
        <TabContent>
          { noticesMenuData[noticeIndex].content }
        </TabContent>
      </div>
    </div>
  );
}

export default NoticesRoot;