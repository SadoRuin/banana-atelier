import React, { useState } from 'react';
import styled from 'styled-components';
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import { faHand } from '@fortawesome/free-solid-svg-icons';

import { GreenBtn } from '../commons/buttons';
import { getArtThumbnail } from "../commons/imageModule";
import TabMenuComponent from "../commons/TabMenuComponent";
import './curationInfo.css'

const Frame = styled.div`
  width: 100%;
  border-radius: 5px;
  border: 1px solid #EBEBEB;
  padding: 20px;
  margin-bottom: 20px;
`;

function CurationInfo ({curationArtsList, outBtn}) {
  const newCurationArtsList = curationArtsList.map((art) => {
    return {...art, auctionNowPrice:art.auctionStartPrice}})

  const [artIndex, setArtIndex] = useState(0);
  const [artList, setArtList] = useState([...newCurationArtsList]);
  const handleAuctionPrice = (e, idx) => {
    setArtList(prev => {
      console.log(prev);
      return prev.map((art, index) => {
        if (index === idx) {
          return {...art, auctionNowPrice: art.auctionNowPrice + art.auctionGap}
        }
        return {...art}
      })
    })
  }


  const artTabMenu = artList.map((art, idx) => {
    return {
      name: idx+1,
      content:
        <div className="curation-art-container" key={`curation__art-detail-${art.artSeq}`}>
          <div className='curation__art-img'
            style={{
              backgroundImage : `url(${getArtThumbnail(art.curationThumbnail, art.artistSeq)})`,
              backgroundSize: 'cover',
              backgroundRepeat: 'no-repeat',
              backgroundPosition: 'center',
              borderRadius: '5px'
            }}
          >
          </div>
          <div className='curation__art-info-container'>
            <h2 className='curation__art-name'>{art.artName}</h2>
            <div className='curation__art-description' style={{whiteSpace: "pre-line"}}>{art.artDescription}</div>
            <div className='curation__art-start-price'>시작 가격 : <span style={{fontWeight: 'bold'}}>{art.auctionStartPrice}</span></div>
            <div className='curation__art-now-price'>현재 가격 : <span style={{fontWeight: 'bold'}}>{art.auctionNowPrice}</span></div>
            <div className='curation__btns'>
              <div style={{width: '60%'}} onClick={(e)=>handleAuctionPrice(e, artIndex)} ><GreenBtn className='curation__participate-auction' style={{width: "100%"}} ><FontAwesomeIcon icon={faHand}/>  {art.auctionNowPrice + art.auctionGap}원에 입찰하기</GreenBtn></div>
              <div style={{width: '40%', marginLeft: "10px"}}>{outBtn}</div>
            </div>
          </div>
        </div>
    }
  })

  return (
    <Frame>
      <TabMenuComponent style={{width: '100%'}} menuData={artTabMenu} index={artIndex} setIndex={setArtIndex}/>
    </Frame>
  );
}

export default CurationInfo;