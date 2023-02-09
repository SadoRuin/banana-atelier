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
  width: ${props => props.width || "150px"};
  height: ${props => props.height || "150px"};

  background-image: url(${props => props.url?.slice(0, 15) === 'default_profile' ? `file:/back/images/default_profile/${props.url}` : `file:/back/images/profile/${props.url}`});
  background-size: cover;
  border-radius: 50%;
  box-sizing: border-box;
  border: 1px solid #EBEBEB;
`

export default ProfileImg