import React from 'react';

const ProfileImg = ({src}) => {
  return (
    <div>
      <img src={src} alt="" height="150" style={{border: '1px solid gray', borderRadius: '50%'}}/>
    </div>
  );
};

export default ProfileImg