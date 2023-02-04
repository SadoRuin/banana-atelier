import React from 'react';
import {NavLink, useLoaderData } from "react-router-dom";

function NoticesFollowing(props) {
  const noticesData = useLoaderData();

  return (
    <div>

      <ul>
        { noticesData.map((noticeData) => {
          return (
            <li key={`notices_following_${noticeData.id}`}>
              <NavLink to={`../${noticeData.id}`}>{noticeData.notice_title}</NavLink>
              {/*{ params.notice_id === ''+noticeData.id && <Outlet /> }*/}

            </li>
          )
        })}
      </ul>
    </div>
  );
}

export default NoticesFollowing;