import React from 'react';
import { Link } from 'react-router-dom'
import ProfileImg from "./ProfileImg";
import { BookmarkBtn } from "./buttons";
import './curationItem.css'

function CurationItem({userNickname, profileImg, curationName, curationSeq, curationHit, curationBmCount, curationThumbnail}) {
  return (
    <div className="curation-item__container">
      <Link to={`${userNickname}/${curationSeq}`} className="curation-img__container" >
        <div className="curation-thumbnail" style={{backgroundImage : `url("file:///back/images/art/thumbnail/${curationThumbnail})"`}} />
        <div className="hidden">
          <div>{curationName}</div>
          <BookmarkBtn isLike={false} />
        </div>
      </Link>

      <div className="curation-info__container">
          <Link to={`${userNickname}`} className="curation-info__artist">
            <ProfileImg width="25px" height="25px" url={ profileImg }/>
            {userNickname}<span className="jakka">작가</span>
          </Link>
          <div className="curation-info__sub">
            <div>조회수 {curationHit}</div>
            <div>북마크 {curationBmCount}</div>
          </div>
      </div>
    </div>
  );
}

export default CurationItem;