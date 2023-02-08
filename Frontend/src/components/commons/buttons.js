import styled from 'styled-components'

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

export const LikeBtn = styled.button`
  border: 1px solid #EBEBEB;
  border-radius: 50%;
  color: #EB5353;
  background-color: white;
  cursor: pointer;
  
  padding: 7px;
  font-size: 13px;
  width: 31px;
  height: 31px;
  box-sizing: border-box;
  
  display: flex;
  justify-content: center;
  align-items: center;
`

export const BookmarkBtn = styled.button`
  border: 1px solid #EBEBEB;
  border-radius: 50%;
  color: #EB5353;
  background-color: white;
  cursor: pointer;

  padding: 7px;
  font-size: 13px;
  width: 31px;
  height: 31px;
  box-sizing: border-box;

  display: flex;
  justify-content: center;
  align-items: center;
`