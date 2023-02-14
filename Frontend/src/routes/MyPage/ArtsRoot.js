import React, { useState } from 'react';
import { useLoaderData, Link, redirect, useOutletContext } from "react-router-dom";

import { axiosAuth, axiosReissue } from "../../_actions/axiosAuth";
import TabMenuComponent from "../../components/commons/TabMenuComponent";
import ArtItemMyPage from "../../components/MyPage/ArtItemMyPage";
import { YellowBtn } from "../../components/commons/buttons";

export async function loader ({params}) {
  const userSeq = params.nickname_user_seq.split('@')[1];
  axiosReissue();

  // 작가의 작품
  const userArts = await axiosAuth(`arts/${userSeq}`)
    .then(response => response.data)
    .catch((error) => error.response.status === 401 ? error : null )
  // 작가가 좋아요한 작품
  const userLikes = await axiosAuth(`arts/${userSeq}/like`)
    .then(response => response.data)
    .catch((error) => error.response.status === 401 ? error : null )
  // 작가의 대표작품
  const userMasterpieces = await axiosAuth(`arts/${userSeq}/masterpiece`)
    .then(response => response.data)
    .catch((error) => error.response.status === 401 ? error : null )
  console.log(userArts);
  console.log(userLikes);
  console.log(userMasterpieces);
  // 401은 권한이 없다는 뜻이므로 login으로 보내버리자
  if (userArts?.response?.status === 401 || userLikes?.response?.status === 401 || userMasterpieces?.response?.status === 401) {
    return redirect("/login")
  }

  return [userArts, userLikes, userMasterpieces]
}

function ArtsRoot() {
  const [userArts, userLikes, userMasterpieces] = useLoaderData();
  const [isMyPage] = useOutletContext();
  const [artsIndex, setArtsIndex] = useState(0);

  const noMasterpiece = isMyPage?
    <div className="art-root__no-masterpiece">
      <div>대표 작품을 설정해보세요!</div>
      <Link to="set_masterpiece"><YellowBtn>대표작품 설정하기</YellowBtn></Link>
    </div> :
    <div className="art-root__no-masterpiece">
      대표작품이 존재하지 않습니다
    </div>

  const noArts = isMyPage?
    <div className="art-root__no-arts">
      <p>나의 작품이 존재하지 않습니다</p>
      <p>작품을 올리고 작가가 되어보세요!</p>
      <Link to="upload"><YellowBtn>작품 업로드</YellowBtn></Link>
    </div> :
    <div className="art-root__no-arts">작품이 존재하지 않습니다</div>

  const artMenuData = [
    { name: '작품',
      content:
        userArts?
          <div>
            <h3>대표작품</h3>
            { userMasterpieces?
                userMasterpieces.map((masterpiece) =>
                  <ArtItemMyPage
                    key={`my-page__arts-${masterpiece.artSeq}`}
                    nickname={masterpiece.nickname}
                    userSeq={masterpiece.userSeq}
                    artThumbnail={masterpiece.artThumbnail}
                    artName={masterpiece.artName}
                    artSeq={masterpiece.artSeq}
                  /> ) : noMasterpiece
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
                /> )}
          </div>
          : noArts
    },
    { name: '좋아요 목록',
      content:
        userLikes?
          <div className="art-root__likes">
            { userLikes.map((like) =>
              <ArtItemMyPage
                key={`my-page__art-likes-${like.artSeq}`}
                nickname={like.nickname}
                userSeq={like.userSeq}
                artThumbnail={like.artThumbnail}
                artName={like.artName}
                artSeq={like.artSeq}
              />) }
          </div>
          : <div className="art-root__likes" >좋아요한 작품이 없습니다.</div>
    }
  ]

  return (
    <div>
      <TabMenuComponent menuData={artMenuData} index={artsIndex} setIndex={setArtsIndex} />
    </div>
  );
}

export default ArtsRoot;