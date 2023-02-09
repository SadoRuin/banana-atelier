import styled from 'styled-components'

import React from 'react';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faHeart, faBookmark } from '@fortawesome/free-solid-svg-icons'
import { faHeart as faHeartEmpty, faBookmark as faBookmarkEmpty } from '@fortawesome/free-regular-svg-icons'

export const YellowBtn = styled.button`
  width: ${props => props.width || "auto"};
  border: none;
  border-radius: 15px;
  font-size: 13px;
  box-sizing: border-box;
  padding: 7px 10px;
  background-color: #F9D923;
  cursor: pointer;
  &:hover {
    background-color: #E7C508;
  }
`

export const GreenBtn = styled.button`
  border: none;
  border-radius: 15px;
  font-size: 13px;
  box-sizing: border-box;
  padding: 7px 10px;
  background-color: #36AE7C;
  cursor: pointer;
  color: white;
  &:hover {
    background-color: #1A9763;
  }
`

export const BlueBtn = styled.button`
  border: none;
  border-radius: 15px;
  font-size: 13px;
  box-sizing: border-box;
  padding: 7px 10px;
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
  box-sizing: border-box;
  padding: 7px 10px;
  background-color: #EB5353;
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
  border-radius: 15px;
  padding: 7px 10px;
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
  padding: 7px;
  box-sizing: border-box;
  width: 31px;
  height: 31px;

  display: flex;
  justify-content: center;
  align-items: center;
`

export function BookmarkBtn ({ isBookmark=false }) {
  return (
    <Btn>
        <FontAwesomeIcon icon={ isBookmark? faBookmark : faBookmarkEmpty } style={{ color: '#187498' }} />
    </Btn>
  );
}

export function LikeBtn ({ isLike=false }) {
  return (
    <Btn>
      <FontAwesomeIcon icon={ isLike? faHeart : faHeartEmpty } style={{ color: '#EB5353' }} />
    </Btn>
  );
}