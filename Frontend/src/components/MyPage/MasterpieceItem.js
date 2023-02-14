import React from 'react';

import { getArtThumbnail } from "../commons/imageModule";
import '../commons/ArtComponent.css'

function MasterpieceItem({isChecked=true, artThumbnail, artName, userSeq}) {
  console.log(isChecked)
  return (
    <div className="art-item__container">
      <div className="art-thumbnail" style={{backgroundImage : `url("${getArtThumbnail(artThumbnail, userSeq)}")`}} />
      <div className="hidden__gradient">
        <div>{artName}</div>
      </div>
    </div>
  );
}

export default MasterpieceItem;