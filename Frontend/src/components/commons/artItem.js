import React from 'react';
import { Link } from 'react-router-dom'
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import { faEye, faThumbsUp } from '@fortawesome/free-solid-svg-icons'

import { getArtThumbnail } from "./imageModule";
import ProfileImg from "./ProfileImg";
import { LikeBtn } from "./buttons";
import './artItem.css'

function ArtItem({nickname, profileImg, userSeq, artThumbnail, artName, artSeq, artHit, artLikeCount}) {

  return (
    <div className="art-item__container">

      <Link to={`${nickname}/${artSeq}`} className="art-img__container">
        <div className="art-thumbnail" style={{backgroundImage : `url("${getArtThumbnail(artThumbnail, userSeq)}")`}} />
        <div className="hidden">
          <div>{artName}</div>
          <LikeBtn isLike={false} />
        </div>
      </Link>

      <div className="art-info__container">
          <Link to={`${nickname}@${userSeq}`} className="art-info__artist link">
            <ProfileImg width="25px" height="25px" url={ profileImg } userSeq={userSeq} />
            {nickname}<span className="jakka">작가</span>
          </Link>
          <div className="art-info__sub">
            <div><FontAwesomeIcon icon={ faEye } /> {artHit}</div>
            <div><FontAwesomeIcon icon={ faThumbsUp } /> {artLikeCount}</div>
          </div>
      </div>

    </div>
  );
}

export default ArtItem;