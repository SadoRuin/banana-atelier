// import React from 'react';

// const ProfileImg = ({src, height="150px"}) => {
//   return (
//     <div>
//       <img src={src} alt="" height={height} style={{border: '1px solid gray', borderRadius: '50%'}}/>
//     </div>
//   );
// };
import styled from 'styled-components'

const ProfileImg = styled.div`
  height: ${props => props.height || "150px"};
  width: ${props => props.width || "150px"};
  background-image: url(${props => props.src || "https://mindlogic-metaverse-face.s3.ap-northeast-2.amazonaws.com/custom/22592-1634653713945.jpeg"});
  background-size: cover;
  border-radius: 50%;
  box-sizing: border-box;
  border: 1px solid #EBEBEB;
`

export default ProfileImg