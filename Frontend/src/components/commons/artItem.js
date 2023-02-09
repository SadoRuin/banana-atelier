import React from 'react';
import { Link } from 'react-router-dom'
import ProfileImg from "./ProfileImg";
import { LikeBtn } from "./buttons";
import './artItem.css'

function ArtItem({nickname, profile_img, art_name, art_seq, art_hit, art_like_count, art_thumbnail}) {

  return (
    <div className="art-item__container">

      <Link to={`${nickname}/${art_seq}`} className="art-img__container">
        <div className="art-thumbnail" style={{backgroundImage : `url("file:///back/images/art/thumbnail/${art_thumbnail})"`}} />
        <div className="hidden">
          <div>{art_name}</div>
          <LikeBtn isLike={false} />
        </div>
      </Link>

      <div className="art-info__container">
          <Link to={`${nickname}`} className="art-info__artist">
            <ProfileImg width="25px" height="25px" url={ profile_img }/>
            {nickname}<span className="jakka">작가</span>
          </Link>
          <div className="art-info__sub">
            <div>조회수 {art_hit}</div>
            <div>좋아요 {art_like_count}</div>
          </div>
      </div>

    </div>
  );
}

export default ArtItem;