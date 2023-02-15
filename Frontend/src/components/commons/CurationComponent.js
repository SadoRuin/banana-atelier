import React from 'react';
import { Link } from 'react-router-dom'
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import { faBookmark } from '@fortawesome/free-solid-svg-icons'
import { faCalendar } from '@fortawesome/free-regular-svg-icons'

import { getArtThumbnail } from "./imageModule";
import ProfileImg from "./ProfileImg";
import './ArtComponent.css'

function CurationComponent({nickname, profileImg, userSeq, curationThumbnail, curationName, curationSeq, curationHit, curationBmCount, curationStartTime, curationStatus}) {

    return (
    <div className="art-item__container">

        <Link to={`/${nickname}/${curationSeq}`} className="art-img__container">
            <div className="art-thumbnail" style={{backgroundImage : `url("${getArtThumbnail(curationThumbnail, userSeq)}")`}} />
            <div className="hidden__gradient">
            <div>{curationName}</div>
            </div>
        </Link>

        <div className="art-info__container">
            <Link to={`/${nickname}@${userSeq}`} className="art-info__artist link">
                <ProfileImg width="25px" height="25px" url={ profileImg } userSeq={userSeq} />
                <span>{nickname}</span><span className="jakka">작가</span>
            </Link>
            <div className="art-info__sub">
                <div><FontAwesomeIcon icon={ faCalendar } /> {`${curationStartTime[0]}.${(curationStartTime[1]+'').padStart(2, "0")}.${(curationStartTime[2]+'').padStart(2, "0")}`}  <FontAwesomeIcon icon={ faBookmark } /> {curationBmCount}</div>
            </div>
        </div>

    </div>
    );
}

export default CurationComponent;