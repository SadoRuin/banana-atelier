import React from 'react';
import { Link } from 'react-router-dom'
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import { faBookmark } from '@fortawesome/free-solid-svg-icons'
import { faCalendar } from '@fortawesome/free-regular-svg-icons'

import { getArtThumbnail } from "./imageModule";
import ProfileImg from "./ProfileImg";
import {OnAirMark, UpcommingMark, EndMark} from "../Curations/mark";
import './ArtComponent.css'

function CurationComponent({ nickname, profileImg, userSeq, curationThumbnail, curationName, curationSeq, curationBmCount, curationStartTime=undefined, curationStatus }) {
    let content = null;
    if (curationStartTime) {
        if (curationStatus === "INIT") {
            content = <div><FontAwesomeIcon icon={ faCalendar } /> {`${curationStartTime[0]}.${(curationStartTime[1]+'').padStart(2, "0")}.${(curationStartTime[2]+'').padStart(2, "0")}  ${(curationStartTime[3]+'').padStart(2, "0")}:${(curationStartTime[4]+'').padStart(2, "0")} 예정`} <FontAwesomeIcon icon={ faBookmark } /> {curationBmCount}</div>
        } else {
            content = <div><FontAwesomeIcon icon={ faCalendar } /> {`${curationStartTime[0]}.${(curationStartTime[1]+'').padStart(2, "0")}.${(curationStartTime[2]+'').padStart(2, "0")} 종료됨`}  <FontAwesomeIcon icon={ faBookmark } />{curationBmCount}</div>
        }
    }
    let mark;
    if (curationStatus === 'INIT') {
        mark = <UpcommingMark />
    }
    else if (curationStatus === 'ON') {
        mark = <OnAirMark />
    }
    else if (curationStatus === 'END') {
        mark = <EndMark />
    }

    return (
    <div className="art-item__container">

        <Link to={`/curations/detail/${curationSeq}`} className="art-img__container">
            <div className="art-thumbnail" style={{backgroundImage : `url("${getArtThumbnail(curationThumbnail, userSeq)}")`}} />
            <div className="hidden__gradient">
                <div>{curationName}</div>
                <div>{mark}</div>
            </div>
        </Link>

        <div className="art-info__container">
            <Link to={`/${nickname}@${userSeq}`} className="art-info__artist link">
                <ProfileImg width="25px" height="25px" url={ profileImg } userSeq={userSeq} />
                <span>{nickname}</span><span className="jakka">작가</span>
            </Link>
            <div className="art-info__sub">
                { content }
            </div>
        </div>

    </div>
    );
}

export default CurationComponent;