import React from 'react';
import styled from 'styled-components';
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import { faHand, faUser } from '@fortawesome/free-solid-svg-icons'

const Frame = styled.div`
  display: flex;
  border-radius: 15px;
  border: 1px solid #EBEBEB;
  width: 600px;
  height: 300px;
`

const Thumbnail = styled.div`
    display: flex;
    overflow: hidden;
    width: 120px;
    height: 180px;
    border-radius: 15px;
    background-color: gray;
    margin: 10px 5px 10px 10px;
    justify-content: center;
    align-items: center;
  `;

const Name = styled.div`
  display: flex;
  // margin: 10px;
`

const Explain = styled.div`
  display: flex;
  border: 1px solid #EBEBEB;
  border-radius: 15px;
  width: 120px;
  height: 80px;
  justify-content: center;
  align-items: center;
  
`
const Participate = styled.div`
  position: absolute;
  display: flex;
  background-color: #36AE7C;
  border-radius: 15px;
  width: 170px;
  height: 53px;
  justify-content: center;
  align-items: center;
`
const WhiteCharacter = styled.div`
  color: #FFFFFF;
  font-size: 20px;
  padding: 10px;
`

function CurationInfo () {
  return (
    <Frame>
      <Thumbnail>
        섬네일
      </Thumbnail>
      <Name>
        작품명
      </Name>
      <div>
        설명
        <Explain>
          내용
        </Explain>
      </div>
      <div>
        경매 시작가
      </div>
      <div>
        <FontAwesomeIcon icon={ faUser } />
        <Participate>
          <WhiteCharacter>
            <FontAwesomeIcon icon={ faHand } />
            참여하기
          </WhiteCharacter>
        </Participate>
      </div>
    </Frame>
  );
}

export default CurationInfo;