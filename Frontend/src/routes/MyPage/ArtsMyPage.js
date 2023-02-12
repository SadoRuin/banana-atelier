import React, { useState } from 'react';
import { axiosAuth, axiosReissue } from "../../_actions/axiosAuth";
import { useLoaderData } from "react-router-dom";

import { TabMenu, TabContent } from "../../components/commons/TabMenuComponent";

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
  const [userArts, userLikes, userMasterpiece] = useLoaderData();

  const [artsIndex, setArtsIndex] = useState(0)

  const artMenuData = [
    { name: '작품', content: userArts?
        <div>
          <h3>대표작품</h3>
          { userMasterpiece? userMasterpiece.map((masterpiece) =>
            <div>
              {masterpiece.artName}
            </div>) :
            <div>대표작품이 없습니다!</div>
          }
          <h3>전체 작품</h3>
          {
            userArts.map((art) =>
              <li>
                {art.artName}
              </li>)}
        </div> :
        (<div>작품을 올리고 작가가 되어보아요~</div>)},
    { name: '좋아요 목록', content: userLikes? userLikes.map((like) =>
        <li>
          {like.artName}
        </li>) : <div>좋아요한 작품이 없습니다.</div>},
  ]

  return (
    <div>
      <div>
        <TabMenu>
          {
            artMenuData.map((tab, idx) =>
              <li
                className={idx === artsIndex ? "submenu focused" : "submenu" }
                onClick={ () => setArtsIndex(idx) }
              >
                {tab.name}
              </li>
            )
          }
        </TabMenu>
        <TabContent>
          {
            artMenuData[artsIndex].content
          }
        </TabContent>
      </div>
    </div>
  );
}

export default ArtsMyPage;