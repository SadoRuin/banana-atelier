import React, {useState} from 'react';
import {Form, Link, redirect, useLoaderData, useOutletContext} from "react-router-dom";
import {axiosAuth, axiosReissue} from "../../_actions/axiosAuth";
import {TabContent, TabMenu} from "../../components/commons/TabMenuComponent";
import {GreenBtn, WhiteBtn, YellowBtn} from "../../components/commons/buttons";
import NoticesDetail from "./NoticesDetail";
import styles from "./Notice.module.css";

export async function loader({params}) {
  const [nickname, userSeq] = params.nickname_user_seq.split('@');
  axiosReissue();

  // 나의 공지, 없으면 null
  const userNotices = await axiosAuth(`notices`)
    .then(response => response.data)
    .catch(() => null)

  // 팔로잉 한 작가들의 공지, 없으면 null
  const followingNotices = await axiosAuth(`notices/following`)
    .then(response => response.data)
    .catch(() => null)

  return [nickname, userSeq, userNotices, followingNotices]
}

export async function action({request}) {
  
  const formData = await request.formData();
  const body = Object.fromEntries(formData);

  if (request.method === "POST") {

    await axiosAuth.post('notices', body)
      .then(response => console.log(response))
      .catch(error => error)

  } else if (request.method === "PUT") {

    await axiosAuth.put('notices', body)
      .then(response => console.log(response))
      .catch(error => error)

  } else if (request.method === "DELETE") {
    alert("공지사항을 삭제하시겠습니까?")
    await axiosAuth.delete(`notices/${body.noticeSeq}`)
      .then(response => console.log(response))
      .catch(error => console.log(error))
  }
  return redirect('../notices')
}

function NoticesRoot() {
  const [isMyPage, isArtist] = useOutletContext();
  const [nickname, userSeq, userNotices, followingNotices] = useLoaderData();
  const [noticeIndex, setNoticeIndex] = useState(0)
  const [isFormOpen, setIsFormOpen] = useState(false)
  const [userNoticesOpen, setUserNoticesOpen] = useState(Array(userNotices.length).fill(false))
  const [followingNoticesOpen, setFollowingrNoticesOpen] = useState(Array(followingNotices.length).fill(false))

  // 나의 공지 토글
  const handleUserNoticeDetail = idx => {
    setUserNoticesOpen(prev => ({
      ...prev,
      [idx]: !prev[idx]
    }));
  };
  // 팔로잉 한 작가들의 공지 토글
  const handleFollowingNoticeDetail = idx => {
    setFollowingrNoticesOpen(prev => ({
      ...prev,
      [idx]: !prev[idx]
    }));
  };

  const noticesMenuData = [];
  if (isArtist) {
    noticesMenuData.push({
      name: "MY",
      content:
        <div className={styles["notice-list"]}>
          {isMyPage ?
            (isFormOpen ?
                <Form method="post" className={styles["form"]}>
                  <div className={styles["label-input"]}>
                    <label className={styles["label-of-form"]}> TITLE </label>
                    <input className={styles["input-of-form"]} type="text" name="noticeTitle" placeholder="제목을 입력하세요"/>
                  </div>
                  <div className={styles["label-input"]}>
                    <label className={styles["label-of-form"]}> CONTENT </label>
                    <textarea name="noticeContent" id="notice_content" cols="30" rows="10"
                              placeholder="내용을 입력하세요"/>
                  </div>
                  <div className={styles["buttons"]}>
                    <YellowBtn className={styles["button"]} type="submit"
                               onSubmit={() => {
                                 setIsFormOpen(prev => !prev)
                               }}
                               onClick={() => {
                                 setIsFormOpen(prev => !prev)
                               }}
                    >
                      제출하기
                    </YellowBtn>
                    <WhiteBtn className={styles["button"]}
                              onClick={() => {
                                setIsFormOpen(prev => !prev)
                              }}>
                      취소
                    </WhiteBtn>
                  </div>
                </Form>
                :
                <GreenBtn className={styles["regist-button"]}
                          onClick={() => {
                            setIsFormOpen(prev => !prev)
                          }}>
                  공 지 작 성
                </GreenBtn>
            ) :
            null
          }
          <div>
            {userNotices ?
              userNotices.map((notice) =>
                <div className={styles["each-notice"]}>
                  <div className={styles["notice-title-nickname"]}>
                    <div className={styles["notice-title"]}
                         onClick={() => handleUserNoticeDetail(notice.noticeSeq)}>
                      {notice.noticeTime[0]}
                      .{notice.noticeTime[1] < 10 ? '0' + notice.noticeTime[1] : notice.noticeTime[1]}
                      .{notice.noticeTime[2] < 10 ? '0' + notice.noticeTime[2] : notice.noticeTime[2]}
                      &nbsp; &nbsp; {notice.noticeTitle}
                    </div>
                    <div userSeq={userSeq}> {nickname} </div>
                  </div>
                  <div>
                    {userNoticesOpen[notice.noticeSeq] ?
                      <NoticesDetail noticeSeq={notice.noticeSeq}
                                     notice_title={notice.noticeTitle}
                                     notice_content={notice.noticeContent}/>
                      :
                      null
                    }
                  </div>
                </div>
              ) :
              <div>등록된 공지사항이 없습니다.</div>}
          </div>
        </div>
    })
  }
  if (isMyPage) {
    noticesMenuData.push({
      name: "ARTIST's",
      content:
        <div className={styles["notice-list"]}>
          {followingNotices ?
            followingNotices.map((notice) =>
              <div className={styles["each-notice"]}>
                <div className={styles["notice-title-nickname"]}>
                  <div className={styles["notice-title"]}
                       onClick={() => handleFollowingNoticeDetail(notice.noticeSeq)}>
                    {notice.noticeTime[0]}
                    .{notice.noticeTime[1] < 10 ? '0' + notice.noticeTime[1] : notice.noticeTime[1]}
                    .{notice.noticeTime[2] < 10 ? '0' + notice.noticeTime[2] : notice.noticeTime[2]}
                    &nbsp; &nbsp; {notice.noticeTitle}
                  </div>
                  <Link to={`/${notice.nickname}@${notice.userSeq}`} className={styles["link"]}>
                    <div userSeq={userSeq}>{notice.nickname}</div>
                  </Link>
                </div>

                <div>
                  {followingNoticesOpen[notice.noticeSeq] ?
                    <NoticesDetail noticeSeq={notice.noticeSeq}
                                   noticeTitle={notice.noticeTitle}
                                   noticeContent={notice.noticeContent}/>
                    :
                    null
                  }
                </div>
              </div>
            ) :
            <div>팔로잉한 작가가 없습니다.</div>
          }
        </div>
    })
  }


  return (
    <div>
      <div>
        <TabMenu>
          {noticesMenuData.map((tab, idx) =>
            <li
              className={idx === noticeIndex ? "submenu focused" : "submenu"}
              onClick={() => setNoticeIndex(idx)}
            >
              {tab.name}
            </li>)}
        </TabMenu>
        <TabContent>
          {noticesMenuData[noticeIndex].content}
        </TabContent>
      </div>
    </div>
  );
}

export default NoticesRoot;