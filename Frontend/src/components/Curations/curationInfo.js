import React from 'react';
import styled from 'styled-components';
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import { faHand } from '@fortawesome/free-solid-svg-icons';

import { Navigation, Pagination, Scrollbar, A11y } from 'swiper';
import { Swiper, SwiperSlide } from 'swiper/react';
import "swiper/css"; //basic
import "swiper/css/navigation";
import "swiper/css/pagination";

import { GreenBtn } from '../commons/buttons';
import { getArtThumbnail } from "../commons/imageModule";
import './curationInfo.css'

const Frame = styled.div`
  display: flex;
  border-radius: 5px;
  border: 1px solid #EBEBEB;
  padding: 20px;
  margin-bottom: 25px;
`;

function CurationInfo ({curationArtsList, outBtn}) {
  const newCurationArtsList = curationArtsList.map((art) => {
    return {...art, auctionNowPrice:art.auctionStartPrice}})
  console.log(newCurationArtsList);

  return (
    <Frame>
      <Swiper
        modules={[Navigation, Pagination, Scrollbar, A11y]}
        // spaceBetween={25}
        slidesPerView={1}
        slidesPerGroup={1}
        navigation
        pagination={{ clickable: true }}
        scrollbar={{ draggable: true }}
      >
        { newCurationArtsList.map((art) =>
            <SwiperSlide key={`openvidu__art-info-${art.artSeq}`}>
              <div className="curation-art-container">
                <img alt={`${art.artistNickName} 작가의 작품 ${art.artName}`}
                     className='curation__art-img'
                     src={getArtThumbnail(art.curationThumbnail, art.artistSeq)}
                />
                <div className='curation__art-info-container'>
                  <h2 className='curation__art-name'>{art.artName}</h2>
                  <div className='curation__art-description' style={{whiteSpace: "pre-line"}}>{art.artDescription}</div>
                  <div className='curation__art-start-price'>시작 가격 : <span style={{fontWeight: 'bold'}}>{art.auctionStartPrice}</span></div>
                  <div className='curation__art-now-price'>현재 가격 : <span style={{fontWeight: 'bold'}}>{art.auctionNowPrice}</span></div>
                  <div className='curation__btns'>
                    <GreenBtn className='curation__participate-auction' style={{width: '60%'}}><FontAwesomeIcon icon={faHand}/> {art.auctionGap}원 추가하기</GreenBtn>
                    {outBtn}
                  </div>
                </div>
              </div>
            </SwiperSlide>
        )}
      </Swiper>
    </Frame>
  );
}

export default CurationInfo;