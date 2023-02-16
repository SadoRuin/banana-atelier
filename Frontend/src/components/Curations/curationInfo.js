import React from 'react';
import styled from 'styled-components';
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import { faHand } from '@fortawesome/free-solid-svg-icons';

import { GreenBtn } from '../commons/buttons';
import './curationInfo.css'

const Frame = styled.div`
  display: flex;
  border-radius: 5px;
  border: 1px solid #EBEBEB;
  padding: 20px;
  margin-bottomo: 25px;
`;

// export async function Loader({params}){
//   const curationsSeq = params.curation_seq
//   const curationList = await axiosAuth.get('curations/on')
//   .then(response => response.data)
//   .catch(error => console.log(error))

//   console.log(curationList);
//   return null
// }

function CurationInfo () {

  return (
    <Frame>
      <div className='curation__art-img'>이미지 공간</div>
      <div className='curation__art-info-container'>
        <h2 className='curation__art-name'>작품명</h2>
        <div className='curation__art-description'>작품설명</div>
        <div className='curation__art-start-price'>가격</div>
        <div className='curation__art-now-price'>현재 가격</div>
        <div className='curation__btns'>
          <GreenBtn style={{width: '100px', height: '30px'}}><FontAwesomeIcon icon={ faHand } /> 참여하기</GreenBtn>
        </div>
      </div>
    </Frame>
  );
}

export default CurationInfo;