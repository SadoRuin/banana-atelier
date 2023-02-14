import React from 'react';
import { useRouteError } from "react-router-dom";


function Error(props) {
  const error = useRouteError();
  console.error(error);

  return (
    <div id="error-page">
      <h1>나나나나!</h1>
      <p>바나나공방에 에러가 발생했습니다!!!</p>
      <p>
        <i>{error.statusText || error.message}</i>
      </p>
    </div>
  );
}

export default Error;