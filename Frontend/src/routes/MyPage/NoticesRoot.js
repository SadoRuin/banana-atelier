import React, { useState } from 'react';
import {Form, useLoaderData, useOutletContext} from "react-router-dom";
import { axiosAuth, axiosReissue } from "../../_actions/axiosAuth";
import {TabMenu, TabContent} from "../../components/commons/TabMenuComponent";
import {WhiteBtn, YellowBtn} from "../../components/commons/buttons";

export async function loader ({params}) {
  const userSeq = params.nickname_user_seq.split('@')[1];
  axiosReissue();

  // 나의 공지, 없으면 null
  const userNotices = await axiosAuth(`notices/${userSeq}`)
    .then(response => response.data)
    .catch(() => null)
  // 팔로잉 한 작가들의 공지, 없으면 null
  const followingNotices = await axiosAuth(`notices/${userSeq}/following`)
    .then(response => response.data)
    .catch(() => null)

  console.log(userNotices);
  console.log(followingNotices);
  return [userSeq, userNotices, followingNotices]
}

export async function action ({request }) {
  // const body = request.formData();
  const body = Object.fromEntries(request.formData());
  await axiosAuth.post('notices', body)
    .then(response => console.log(response))
    .catch(error => error)
}


function NoticesRoot() {
  const [isMyPage, isArtist] = useOutletContext();
  const [userSeq, userNotices, followingNotices] = useLoaderData();

  const [noticeIndex, setNoticeIndex] = useState(0)
  const [isFormOpen, setIsFormOpen] = useState(false)

  console.log(userSeq)
  console.log(isFormOpen)
  const noticesMenuData = [];
  if (isArtist) {
    noticesMenuData.push({
        name: "공지사항",
        content:
        <div>
          { isMyPage ?
            ( isFormOpen?
                <Form method="post">
                  <label> 공지 제목
                    <input type="text" name="noticeTitle" placeholder="제목을 입력하세요" />
                  </label><br/>
                  <label> 내용
                    <textarea name="noticeContent" id="notice_content" cols="30" rows="10" placeholder="내용을 입력하센"></textarea>
                  </label>
                  <div>
                    <YellowBtn type="submit" onSubmit={() => {setIsFormOpen(prev => !prev)}}>제출하기</YellowBtn>
                    <WhiteBtn onClick={()=>{setIsFormOpen(prev=> !prev)}}>취소</WhiteBtn>
                  </div>
                </Form>
                :
                <YellowBtn onClick={() => {setIsFormOpen(prev => !prev)}}>
                  공지 작성하기
                </YellowBtn> )
            :
            null
          }
          { userNotices ?
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