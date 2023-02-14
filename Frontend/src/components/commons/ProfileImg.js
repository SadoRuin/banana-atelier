import styled from 'styled-components'
import { checkIsDefaultProfile } from "./imageModule";

const ProfileImg = styled.div`
  width: ${props => props.width || "150px"};
  height: ${props => props.height || "150px"};
  background-image: url(${props => checkIsDefaultProfile(props.url, props.userSeq) });
  background-size: cover;
  border-radius: 50%;
  box-sizing: border-box;
  border: 1px solid #EBEBEB;
`
export default ProfileImg