import React from 'react';
import {axiosAuth, axiosReissue} from "../../_actions/axiosAuth";
// import {useLoaderData} from "react-router-dom";

export async function loader ({params}) {
  const userSeq = params.nickname_user_seq.split('@')[1];

  axiosReissue();
  const userArts = await axiosAuth(`arts/${userSeq}`)
    .then(response => response.data)
    .catch(() => null)
  const userLikes = await axiosAuth(`arts/${userSeq}/like`)
    .then(response => response.data)
    .catch(() => null)
  const userMasterpiece = await axiosAuth(`arts/${userSeq}/masterpiece`)
    .then(response => response.data)
    .catch(() => null)
  console.log(userArts);
  console.log(userLikes);
  console.log(userMasterpiece)
  return [userArts, userLikes, userMasterpiece]
}

function ArtsMyPage() {
  // const [userArts, userLikes, userMasterpiece] = useLoaderData
  //
  // const artMenuData = [
  //   { name: '작품', content: [...userArts]},
  //   { name: '좋아요 목록', content: [...userLikes]},
  //   { name: '대표작품', content: [...userMasterpiece]}
  // ]

  return (
    <div>
      내 작업, 좋아하는 작품, 내 소장작품(내 페이지일 때만 렌더링? 근데 애초에 이거 의미가 있나 커미션이 없는데)
    </div>
  );
}

export default ArtsMyPage;