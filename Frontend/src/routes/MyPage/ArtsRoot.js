import React, { useState } from 'react';
import { useLoaderData, Link, redirect } from "react-router-dom";
import { axiosAuth, axiosReissue } from "../../_actions/axiosAuth";
import { TabMenu, TabContent } from "../../components/commons/TabMenuComponent";
import ArtItemMyPage from "../../components/commons/ArtItemMyPage";
import {YellowBtn} from "../../components/commons/buttons";

export async function loader ({params}) {
  const userSeq = params.nickname_user_seq.split('@')[1];
  axiosReissue();


  const userArts = await axiosAuth(`arts/${userSeq}`)
    .then(response => response.data)
    .catch((error) => error.response.status === 401 ? error : null )
  const userLikes = await axiosAuth(`arts/${userSeq}/like`)
    .then(response => response.data)
    .catch((error) => error.response.status === 401 ? error : null )
  const userMasterpiece = await axiosAuth(`arts/${userSeq}/masterpiece`)
    .then(response => response.data)
    .catch((error) => error.response.status === 401 ? error : null )
  console.log(userArts);
  console.log(userLikes);
  console.log(userMasterpiece);
  if (userArts?.response?.status === 401 || userLikes?.response?.status === 401 || userMasterpiece?.response?.status === 401) {
    return redirect("/login")
  }
  return [userArts, userLikes, userMasterpiece]
}

function ArtsRoot() {
  const [userArts, userLikes, userMasterpiece] = useLoaderData();

  const [artsIndex, setArtsIndex] = useState(0)

  console.log(userArts);

  const artMenuData = [
    { name: '작품',
      content:
        userArts?
          <div>
            <h3>대표작품</h3>
            { userMasterpiece? userMasterpiece.map((masterpiece) =>
              <div key={`my-page__masterpiece-${masterpiece.artSeq}`}>
                {masterpiece.artName}
              </div>) :
              <div>
                <div>대표작품이 없습니다! 대표작품을 설정해보세요~</div>
                <Link to="set_masterpiece"><YellowBtn>대표작품 설정하기</YellowBtn></Link>
              </div>
            }
            <h3>전체 작품</h3>
            {
              userArts.map((art) =>
                <ArtItemMyPage
                  key={`my-page__arts-${art.artSeq}`}
                  nickname={art.nickname}
                  userSeq={art.userSeq}
                  artThumbnail={art.artThumbnail}
                  artName={art.artName}
                  artSeq={art.artSeq}
                />)}
          </div>
          :
          <div>작품을 올리고 작가가 되어보아요~</div> },
    { name: '좋아요 목록',
      content:
        userLikes? userLikes.map((like) =>
          <ArtItemMyPage
            key={`my-page__art-likes-${like.artSeq}`}
            nickname={like.nickname}
            userSeq={like.userSeq}
            artThumbnail={like.artThumbnail}
            artName={like.artName}
            artSeq={like.artSeq}
          />)
          :
          <div>좋아요한 작품이 없습니다.</div> },
  ]

  return (
    <div>
      <div>
        <TabMenu>
          {
            artMenuData.map((tab, idx) =>
              <li
                className={idx === artsIndex ? "submenu focused" : "submenu" }
                onClick={ () => setArtsIndex(idx) } key={`my-page__art-${idx}`}
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

export default ArtsRoot;