import React from 'react';
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faCircleCheck} from "@fortawesome/free-regular-svg-icons";

import { getArtThumbnail } from "../commons/imageModule";
import '../commons/ArtComponent.css'
import './MasterPieceItem.css'

function MasterpieceItem({isRepresent = false, artThumbnail, artName, userSeq}) {
  if (isRepresent) {
    return (
      <div className="art-img__container"
           style={{backgroundImage : `url("${getArtThumbnail(artThumbnail, userSeq)}")`,
                    backgroundSize: "cover",
                    backgroundRepeat: "no-repeat",
                    backgroundPosition: "center",
                    cursor: "pointer"}}
      >
        <div className="masterpiece__selected">
          <FontAwesomeIcon icon={faCircleCheck} />
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