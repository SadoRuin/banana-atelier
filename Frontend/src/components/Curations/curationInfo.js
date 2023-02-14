import React from 'react';
import styled from 'styled-components';
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import { faHand, faUser } from '@fortawesome/free-solid-svg-icons'



const Frame = styled.div`
  display: grid;
  grid-template-columns: 180px 2fr 1fr;
  border-radius: 15px;
  border: 1px solid #EBEBEB;
  width: 600px;
  height: 300px;
`

const Thumbnail = styled.div`
    display: flex;
    overflow: hidden;
    width: 160px;
    height: 220px;
    border-radius: 15px;
    background-color: gray;
    margin: 10px 5px 10px 10px;
    justify-content: center;
    align-items: center;
  `;

// 이름, 설명 전체 틀
const Info = styled.div`
  grid-column: 2/span 2;
  grid-row: 1/span 2;
  margin-left: 10px;
`
// 이름
const Name = styled.div`
  display: flex;
  margin: 10px 0px 10px 0px;
  font-size: 20px;
  font-weight: bold;
`
// 설명
const Explain = styled.div`
  display: flex;
  border: 1px solid #EBEBEB;
  border-radius: 15px;
  width: 360px;
  height: 160px;
  justify-content: center;
  align-items: center;
  position: relative;
  top: 10px;
`

const PriceInfo = styled.div`
  display: flex;
  grid-column: 1/span 2;
  grid-row: 2/span 2;
  align-items: center;
  padding-left: 10px;
  font-size: 15px;
`

const Price = styled.div`
  display: flex;
  background-color: #EBEBEB;
  border-radius: 15px;
  width: 150px;
  height: 30px;
  justify-content: center;
  align-items: center;
  margin-left: 10px;
`

const Bidder = styled.div`
  margin-left:100px;
`


const Participate = styled.div`
  display: flex;
  background-color: #36AE7C;
  border-radius: 15px;
  width: 170px;
  height: 53px;
  justify-content: center;
  align-items: center;
  grid-column: 3/span 1;
  grid-row: 2/span 1;
  margin-right: 5px;
`
const WhiteCharacter = styled.div`
  color: #FFFFFF;
  font-size: 20px;
  padding: 10px;
`

// export async function Loader({params}){
//   const curationsSeq = params.curation_seq
//   const curationList = await axiosAuth.get('curations/on')
//   .then(response => response.data)
//   .catch(error => console.log(error))

//   console.log(curationList);
//   return null
// }

function CurationInfo () {

  // let token = localStorage.getItem('token')


  return (
    <Frame>
      <Thumbnail>
        섬네일
      </Thumbnail>
      <Info>
        <Name>
          작품명
        </Name>
        설명
        <Explain>
          내용
        </Explain>
        
      </Info>
      <PriceInfo>
        경매 시작가
        <Price>
        26,000  
        </Price>
        <Bidder>
          <FontAwesomeIcon icon={ faUser } />
          명
        </Bidder>
      </PriceInfo>

      <Participate>
        <WhiteCharacter>
          <FontAwesomeIcon icon={ faHand } />
          참여하기
        </WhiteCharacter>
      </Participate>
    </Frame>
  );
}

export default CurationInfo;