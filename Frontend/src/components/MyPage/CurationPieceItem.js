import React from 'react';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCircleCheck } from "@fortawesome/free-regular-svg-icons";
import { faBan } from "@fortawesome/free-solid-svg-icons";

import { getArtThumbnail } from "../commons/imageModule";
import '../commons/ArtComponent.css'
import './CurationPieceItem.css'

function CurationPieceItem({isChecked=false, isSold, artThumbnail, artName, userSeq}) {
  if (isSold) {
    return (
      <div className="art-img__container"
           style={{
             backgroundImage : `url("${getArtThumbnail(artThumbnail, userSeq)}")`,
             backgroundSize: "cover",
             backgroundRepeat: "no-repeat",
             backgroundPosition: "center",
             cursor: "not-allowed"}}
      >
        <div className="curation-art__sold">
          <FontAwesomeIcon icon={faBan} />
        </div>
      </div>
    );
  }
  else if (isChecked) {
    return (
      <div className="art-img__container"
           style={{
              backgroundImage : `url("${getArtThumbnail(artThumbnail, userSeq)}")`,
              backgroundSize: "cover",
              backgroundRepeat: "no-repeat",
              backgroundPosition: "center",
              cursor: "pointer"}}
      >
        <div className="curation-art__selected">
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

export default CurationPieceItem;