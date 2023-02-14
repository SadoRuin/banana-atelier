import React from 'react';
import {axiosAuth} from "../../_actions/axiosAuth";
import {useLoaderData} from "react-router-dom";
import ArtComponent from "../../components/commons/ArtComponent";

export async function loader () {
  const artList = await axiosAuth('arts/all')
    .then(response => response.data)
    .catch(error => error.response.status)
  console.log(artList)
  return artList;
}

function ArtsMainAll() {
  const artList = useLoaderData();
  return (
    <div><div className="sort_tab">
      <div>요즘 뜨는 작품</div>
      <div className="grid__main-components">
        {artList.map((art) =>
          <div key={`art-item_${art.artSeq}`}>
            <ArtComponent
              nickname={art.nickname}
              profileImg={art.profileImg}
              userSeq={art.userSeq}
              artThumbnail={art.artThumbnail}
              artName={art.artName}
              artSeq={art.artSeq}
              artHit={art.artHit}
              artLikeCount={art.artLikeCount}
            />
          </div>
        )}
      </div>


      <div>새로 나온 작품</div>
      <div className="grid__main-components">
        {artList.map((art) =>
          <div key={`art-item_${art.artSeq}`}>
            <ArtComponent
              nickname={art.nickname}
              profileImg={art.profileImg}
              userSeq={art.userSeq}
              artThumbnail={art.artThumbnail}
              artName={art.artName}
              artSeq={art.artSeq}
              artHit={art.artHit}
              artLikeCount={art.artLikeCount}
            />
          </div>
        )}
      </div>


      <div>인기 작품</div>

      <div className="grid__main-components">
        {artList.map((art) =>
          <div key={`art-item_${art.artSeq}`}>
            <ArtComponent
              nickname={art.nickname}
              profileImg={art.profileImg}
              userSeq={art.userSeq}
              artThumbnail={art.artThumbnail}
              artName={art.artName}
              artSeq={art.artSeq}
              artHit={art.artHit}
              artLikeCount={art.artLikeCount}
            />
          </div>
        )}
      </div>
    </div></div>
  );
}

export default ArtsMainAll;