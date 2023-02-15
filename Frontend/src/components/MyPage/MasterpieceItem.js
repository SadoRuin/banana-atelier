import React from 'react';

import { getArtThumbnail } from "../commons/imageModule";
import '../commons/ArtComponent.css'

function MasterpieceItem({isRepresent = false, artThumbnail, artName, userSeq}) {
  console.log(isRepresent);
  if (isRepresent) {
    return (
      <div className="art-img__container"
           style={{backgroundImage : `rgba(0, 0, 0, 0.5), url("${getArtThumbnail(artThumbnail, userSeq)}")`,
                    backgroundSize: "cover",
                    backgroundRepeat: "no-repeat",
                    backgroundPosition: "center",
                    cursor: "pointer"}}
      >
        <div className="masterpiece__selected">
          <div>{artName}</div>
        </div>
      </div>
    );
  }
  return (
      <div className="art-img__container"
           style={{backgroundImage : `url("${getArtThumbnail(artThumbnail, userSeq)}")`,
                    backgroundSize: "cover",
                    backgroundRepeat: "no-repeat",
                    backgroundPosition: "center",
                    cursor: "pointer"}}
      >
        <div className="hidden__gradient">
          <div>{artName}</div>
        </div>
      </div>
  );
}

export default MasterpieceItem;