import React from 'react';
import { Link } from 'react-router-dom'
import './artsThumbnail.css'

function ArtsThumbnail({art_hit, art_like_count, art_name, art_seq, art_thumbnail, nickname, user_seq}) {
  return (
    <div className="art-thumbnail__container">
      <div className="art-thumbnail">
        <div className="hidden" width="100%" height="100%">{art_name}</div>
        <img src="https://item.kakaocdn.net/do/2697398bad5927125f3db2f8a248ca15f604e7b0e6900f9ac53a43965300eb9a" height alt=""/>
      </div>
      <div>
        <Link className="Link" to={`../${nickname}`}>{nickname}작가 </Link>
        <div>조회수{art_hit}</div>
        <div>좋아요{art_like_count}</div>
      </div>
    </div>
  );
}

export default ArtsThumbnail;