import styled from 'styled-components';

export const Category = styled.div`
  display: inline-block;
  font-size: 13px;
  padding: 7px 13px;
  border: 2px solid #F9D923;
  box-sizing: border-box;
  border-radius: 30px;
  margin-bottom: 15px;
  
  &:hover {
    background-color: #F9D923;
    font-weight: bold;
    cursor: pointer;
    
    transition: 300ms;
  }
`