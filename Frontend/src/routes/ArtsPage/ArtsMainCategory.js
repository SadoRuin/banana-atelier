import React from 'react'
import axiosCustom from '../../_actions/axiosCustom';
import ArtItem from "../../components/commons/artItem";
import { useLoaderData } from 'react-router-dom';

export async function loader({params}) {
  const categorySeq = +params.category_seq;
  console.log(categorySeq)
  const artList = await axiosCustom.get(`arts/category/${categorySeq}`)
    .then(response => response.data)
    .catch(error => error)
  console.log(artList);
  return artList
}


function ArtsMainCategory() {
  const artsList = useLoaderData();
  return (
    <div>
      <div className="grid__main-components">
        {artsList.map((art) =>
          <div key={`art-item_${art.artSeq}`}>
            <ArtItem
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
    </div>
  )
}
export default ArtsMainCategory
