import React from 'react'
import styled from 'styled-components';

const StyledUpcomming = styled.div`
  display: inline-block;
  padding: 5px 10px;
  border-radius: 15px;
  background-color: #F9D923;
  color: white;
  font-style: italic;
  font-weight: bold;
  font-size: 12px;
`
const StyledOnAir = styled.div`
  display: inline-block;
  padding: 5px 10px;
  border-radius: 15px;
  background-color: #EB5353;
  color: white;
  font-style: italic;
  font-weight: bold;
  font-size: 12px;
`
const StyledEnd = styled.div`
  display: inline-block;
  padding: 5px 10px;
  border-radius: 15px;
  background-color: #187498;
  color: white;
  font-style: italic;
  font-weight: bold;
  font-size: 12px;
`
export function UpcommingMark() {
  return (
    <StyledUpcomming>UPCOMMING</StyledUpcomming>
  );
}
export function OnAirMark() {
  return (
    <StyledOnAir>ON-AIR</StyledOnAir>
  );
}
export function EndMark() {
  return (
    <StyledEnd>END</StyledEnd>
  );
}