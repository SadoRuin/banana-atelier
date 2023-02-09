import React from 'react';
import { Link } from 'react-router-dom'
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faHeart } from '@fortawesome/free-solid-svg-icons'
// import { faHeart as faHearEmpty } from '@fortawesome/free-regular-svg-icons'
import ProfileImg from "./ProfileImg";
import { LikeBtn } from "./buttons";
import './artItem.css'

function ArtItem({nickname, profile_img, art_name, art_seq, art_hit, art_like_count, art_thumbnail}) {

  return (
    <div className="art-item__container">

      <Link to={`${nickname}/${art_seq}`} className="art-img" onMouseOver={(e)=>{}} >
        {/* url은 추후 art-thumbnail 완성되면 쓸 것*/}
        {/*<div className="art-thumbnail" style={{backgroundImage : `url(${'https://cafe24img.poxo.com/spao/web/product/big/202212/f329103f664345d04693b48bece506f4.jpg'})`}} />*/}
        <div className="art-thumbnail" style={{backgroundImage : `url("file:///back/images/art/thumbnail/${art_thumbnail})"`}} />
        <div className="hidden">
          <div>{art_name}</div>
          <LikeBtn><FontAwesomeIcon icon={faHeart} /></LikeBtn>
        </div>
      </Link>

      <div className="art-info">
        <Link to={`${nickname}`} className="art-info link">
          <div className="art-info__artist">
            <ProfileImg width="25px" height="25px" url={ profile_img }/>
            {nickname}<span className="jakka">작가</span>
          </div>
          <div className="art-info__sub">
            <div>조회수 {art_hit}</div>
            <div>좋아요 {art_like_count}</div>
          </div>
        </Link>
      </div>

    </div>
  );
}

export default ArtItem;