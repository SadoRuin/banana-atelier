import React from 'react';
import styled from 'styled-components'
import { getArtThumbnail } from "../commons/imageModule";
import './MasterPieceItem.css'

const MasterpieceItemShape = styled.div`
  background-image: url(${props => getArtThumbnail(props.url, props.userSeq)});
  object-fit: cover;
  border-radius: 5px;
  width: 100%;
  aspect-ratio: 1 / 1;
`

function MasterpieceItem({isChecked=true, artThumbnail, artName, userSeq}) {
  return (
    <div>
      <MasterpieceItemShape url={artThumbnail} userSeq={userSeq} />
      <div className={isChecked ? 'masterpiece-item__checked is-checked' : 'masterpiece-item__checked'}>
        {artName}
      </div>
    </div>
  );
}

export default MasterpieceItem;