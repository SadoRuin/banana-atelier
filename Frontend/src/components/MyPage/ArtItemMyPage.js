import React from 'react';
import { Link } from 'react-router-dom'

import { getArtThumbnail } from "../commons/imageModule";
import '../commons/ArtComponent.css'

function ArtItemMyPage({nickname, userSeq, artThumbnail, artName, artSeq}) {
  console.log(nickname)
  return (
    <div className="art-item__container">

      <Link to={`/${nickname}/${artSeq}`} className="art-img__container">
        <div className="art-thumbnail" style={{backgroundImage : `url("${getArtThumbnail(artThumbnail, userSeq)}")`}} />
        <div className="hidden__gradient">
          <div>{artName}</div>
        </div>
      </Link>

    </div>
  );
}

export default ArtItemMyPage;