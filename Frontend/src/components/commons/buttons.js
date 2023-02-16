import styled from 'styled-components'

import React from 'react';
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faBookmark, faHeart} from '@fortawesome/free-solid-svg-icons'
import {faBookmark as faBookmarkEmpty, faHeart as faHeartEmpty} from '@fortawesome/free-regular-svg-icons'

export const YellowBtn = styled.button`
  width: ${props => props.width || "auto"};
  border: none;
  border-radius: 15px;
  font-size: 13px;
  font-family: Pretendard-Regular;
  color: #5a5a5a;
  box-sizing: border-box;
  padding: 5px 10px;
  background-color: #ffe240;
  cursor: pointer;

  display: flex;
  justify-content: center;
  align-items: center;

  &:hover {
    background-color: #fff3ac;
  }
`

export const GreenBtn = styled.button`
  border: none;
  border-radius: 15px;
  font-size: 13px;
  font-family: Pretendard-Regular;
  box-sizing: border-box;
  padding: 5px 10px;
  background-color: #71ba1f;
  cursor: pointer;
  color: #f0f0f0;

  display: flex;
  justify-content: center;
  align-items: center;

  &:hover {
    background-color: #b9e884;
    color: #5a5a5a;
  }
`

export const BlueBtn = styled.button`
  border: none;
  border-radius: 15px;
  font-size: 13px;
  font-family: Pretendard-Regular;
  color: #5a5a5a;
  box-sizing: border-box;
  padding: 5px 10px;
  background-color: #187498;
  cursor: pointer;
  color: white;

  &:hover {
    background-color: #066084;
  }
`

export const RedBtn = styled.button`
  border: none;
  border-radius: 15px;
  font-size: 13px;
  font-family: Pretendard-Regular;
  box-sizing: border-box;
  padding: 5px 10px;
  background-color: #f35745;
  cursor: pointer;
  color: white;

  &:hover {
    background-color: #D12C2C;
  }
`

export const WhiteBtn = styled.button`
  border: 1px solid #EBEBEB;
  box-sizing: border-box;
  font-size: 13px;
  font-family: Pretendard-Regular;
  color: #5a5a5a;
  border-radius: 15px;
  padding: 5px 10px;
  background-color: #FFFFFF;
  cursor: pointer;

  &:hover {
    background-color: #F0F0F0;
  }
`

const Btn = styled.button`
  background-color: white;
  border: 1px solid #EBEBEB;
  border-radius: 50%;
  cursor: pointer;

  font-size: 13px;
  font-family: Pretendard-Regular;
  padding: 7px;
  box-sizing: border-box;
  width: 31px;
  height: 31px;

  display: flex;
  justify-content: center;
  align-items: center;
`

export function BookmarkBtn({isBookmark = false}) {
  return (
    <Btn>
      <FontAwesomeIcon icon={isBookmark ? faBookmark : faBookmarkEmpty} style={{color: '#187498'}}/>
    </Btn>
  );
}

export function LikeBtn({isLike = false}) {
  return (
    <Btn>
      <FontAwesomeIcon icon={isLike ? faHeart : faHeartEmpty} style={{color: '#EB5353'}}/>
    </Btn>
  );
}