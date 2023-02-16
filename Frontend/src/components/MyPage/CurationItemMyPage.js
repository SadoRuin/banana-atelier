import React from 'react';
import { Link } from 'react-router-dom';

import { getArtThumbnail } from "../commons/imageModule";
import '../commons/ArtComponent.css'

function CurationItemMyPage({userSeq, curationThumbnail, curationName, curationSeq}) {
  return (
    <div className="art-item__container">

      <Link to={`/curations/detail/${curationSeq}`} className="art-img__container">
        <div className="art-thumbnail" style={{backgroundImage : `url("${getArtThumbnail(curationThumbnail, userSeq)}")`}} />
        <div className="hidden__gradient">
          <div>{curationName}</div>
        </div>
      </Link>

    </div>
  );
}

export default CurationItemMyPage;