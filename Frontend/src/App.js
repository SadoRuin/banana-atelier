import './App.css';
import NavBar from "./routes/LayoutPage/NavBar/NavBar"
import Footer from "./routes/LayoutPage/Footer/Footer"
import LandingPage from './routes/LandingPage/LandingPage'
import LoginPage from './routes/LoginPage/LoginPage'
import SignUpPage from './routes/SignUpPage/SignUpPage'

import {
  BrowserRouter as Router,
  Routes,
  Route
} from "react-router-dom"


function App() {
  return (
    <Router>
      <NavBar></NavBar>
      <div>
        <Routes>
          <Route exact path="/" element={<LandingPage />}></Route>
          <Route exact path="/login" element={<LoginPage />}></Route>
          <Route exact path="/register" element={<SignUpPage />}></Route>
        </Routes>
      </div>
      <Footer></Footer>
    </Router>
  );
}

export default App;
