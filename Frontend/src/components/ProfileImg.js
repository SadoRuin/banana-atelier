import React from 'react';

const ProfileImg = ({src, height="150px"}) => {
  return (
    <div>
      <img src={src} alt="" height={height} style={{border: '1px solid gray', borderRadius: '50%'}}/>
    </div>
  );
};

export default ProfileImg