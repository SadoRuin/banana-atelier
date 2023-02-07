import React from 'react';
import { Link } from 'react-router-dom'
import './artItem.css'

function ArtItem({art_hit, art_like_count, art_name, art_seq, art_thumbnail, nickname, user_seq}) {


  return (
    <div className="art-item__container">
      <Link to={`${nickname}/${art_seq}`} className="art-img" >
        {/* url은 추후 art-thumbnail 완성되면 쓸 것*/}
        <div className="art-thumbnail" style={{backgroundImage : `url(${'https://cafe24img.poxo.com/spao/web/product/big/202212/f329103f664345d04693b48bece506f4.jpg'})`}} />
        <div className="hidden">{art_name}</div>
      </Link>
      <Link to={`${nickname}`} className="art-info Link">
        <div className="art-info__artist">{nickname}작가 </div>
        <div className="art-info__sub">
          <div>조회수{art_hit}</div>
          <div>좋아요{art_like_count}</div>
        </div>
      </Link>
    </div>
  );
}

export default ArtItem;