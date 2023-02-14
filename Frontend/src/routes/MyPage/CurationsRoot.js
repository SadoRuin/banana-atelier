import React from 'react';
import { useOutletContext } from "react-router-dom";
import {axiosAuth, axiosReissue} from "../../_actions/axiosAuth";


export async function loader ({params}) {
  const userSeq = params.nickname_user_seq.split('@')[1];

  axiosReissue();
  // 페이지 주인의 큐레이션 정보 가져오기
  // const curationsList = await axiosAuth.get(`curations/${userSeq}`)
  //   .then(response => response.data)
  //   .catch(error => {
  //     console.log(error)
  //     return null
  //   })
  // 북마크한 큐레이션 가져오기
  //   const curationsBookmarkList = await axiosAuth.get()
  // 팔로잉한 작가들의 큐레이션 가져오기

  //  이 페이지 주인이 작가인지 아닌지 구분하기 위해...
  return await axiosAuth.get(`curations/${userSeq}`)
      .then(response => response.data)
      .catch(error => {
        console.log(error)
        return null
      })
}
function CurationsRoot() {
  const [isMyPage, isArtist] = useOutletContext();

  return (
    <div>
      {isMyPage? "내 페이지야" :  "남의 페이지야"}
      {isArtist? "작가야" :  "아니야"}
    </div>
  );
}

export default CurationsRoot;