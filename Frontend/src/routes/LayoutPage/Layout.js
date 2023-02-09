import React from 'react';
import { Outlet } from 'react-router-dom';

import NavBar from "./NavBar/NavBar";
import Footer from "./Footer/Footer";

function Layout(props) {
  return (
    <div className="wrapper">
      <div className="content-wrapper">
        <NavBar />
        <div className="content__container">
          <Outlet />
        </div>
      </div>
      <Footer />
    </div>
  );
}

export default Layout;