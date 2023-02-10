import React from 'react';
import { Link } from 'react-router-dom'
import ProfileImg from "./ProfileImg";
import { LikeBtn } from "./buttons";
import './artItem.css'

function ArtItem({nickname, profileImg, artName, artSeq, artHit, artLikeCount, artThumbnail}) {

  return (
    <div className="art-item__container">

      <Link to={`${nickname}/${artSeq}`} className="art-img__container">
        <div className="art-thumbnail" style={{backgroundImage : `url("file:///back/images/art/thumbnail/${artThumbnail})"`}} />
        <div className="hidden">
          <div>{artName}</div>
          <LikeBtn isLike={false} />
        </div>
      </Link>

      <div className="art-info__container">
          <Link to={`${nickname}`} className="art-info__artist">
            <ProfileImg width="25px" height="25px" url={ profileImg }/>
            {nickname}<span className="jakka">작가</span>
          </Link>
          <div className="art-info__sub">
            <div>조회수 {artHit}</div>
            <div>좋아요 {artLikeCount}</div>
          </div>
      </div>

    </div>
  );
}

export default ArtItem;