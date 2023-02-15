import React from 'react';

import { getArtThumbnail } from "../commons/imageModule";
import '../commons/ArtComponent.css'

function MasterpieceItem({isRepresent = false, artThumbnail, artName, userSeq}) {
  console.log(isRepresent);
  return (
      <div className="art-img__container "
           style={{backgroundImage : `url("${getArtThumbnail(artThumbnail, userSeq)}")`,
                    backgroundSize: "cover",
                    backgroundRepeat: "no-repeat",
                    backgroundPosition: "center",
                    cursor: "pointer"}}
      >
        {
          isRepresent?
          <div className="is-represent">
            대표작품입니다.
          </div>
            :
          <div className="hidden__gradient">
            <div>{artName}</div>
          </div>
        }
      </div>

  );
}

export default MasterpieceItem;