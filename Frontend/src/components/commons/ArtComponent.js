import React from 'react';
import { Link } from 'react-router-dom'
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import { faEye, faThumbsUp } from '@fortawesome/free-solid-svg-icons'

import { getArtThumbnail } from "./imageModule";
import ProfileImg from "./ProfileImg";
import './ArtComponent.css'

function ArtComponent({nickname, profileImg, userSeq, artThumbnail, artName, artSeq, artHit, artLikeCount}) {

  return (
    <div className="art-item__container">

      <Link to={`/${nickname}/${artSeq}`} className="art-img__container">
        <div className="art-thumbnail" style={{backgroundImage : `url("${getArtThumbnail(artThumbnail, userSeq)}")`}} />
        <div className="hidden__gradient">
          <div>{artName}</div>
        </div>
      </Link>

      <div className="art-info__container">
          <Link to={`/${nickname}@${userSeq}`} className="art-info__artist link">
            <ProfileImg width="25px" height="25px" url={ profileImg } userSeq={userSeq} />
            <span>{nickname}</span><span className="jakka">작가</span>
          </Link>
        { (artHit && artLikeCount) &&
          <div className="art-info__sub">
            <div><FontAwesomeIcon icon={ faEye } /> {artHit}<FontAwesomeIcon icon={ faThumbsUp } /> {artLikeCount}</div>
          </div> }
      </div>

    </div>
  );
}

export default ArtComponent;