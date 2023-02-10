import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import reportWebVitals from './reportWebVitals';
import { Provider } from 'react-redux';
import { applyMiddleware, createStore } from 'redux';
import promiseMiddleware from 'redux-promise'
import ReduxThunk from 'redux-thunk'
import rootReducer from './_reducers'

import { Route, createBrowserRouter, createRoutesFromElements, RouterProvider } from "react-router-dom";

// 로그인 & 회원가입
import LoginPage from "./routes/LoginPage/LoginPage";
import SignUpPage from "./routes/SignUpPage/SignUpPage";

// 에러 페이지
import Error from './routes/Error'

// 전체 Layout(Nav바랑 Footer)
import Layout from "./routes/LayoutPage/Layout"

// 랜딩 페이지
import LandingPage from "./routes/LandingPage/LandingPage";
import { loader as LandingLoader } from './routes/LandingPage/LandingPage'

// 작품 페이지
import ArtsMain from "./routes/ArtsPage/ArtsMain";
import ArtsDetail from "./routes/ArtsPage/ArtsDetail";
import { loader as ArtsDetailLoader } from "./routes/ArtsPage/ArtsDetail";

// 큐레이션 페이지
import CurationsMain from "./routes/CurationsPage/CurationsMain";
import { loader as curationListLoader } from "./routes/CurationsPage/CurationsMain";
import CurationsOnAir from "./routes/CurationsPage/CurationsOnAir"
import CurationsUpcoming from "./routes/CurationsPage/CurationsUpcoming";
import CurationsEnd from "./routes/CurationsPage/CurationsEnd";
import CurationsDetail from "./routes/CurationsPage/CurationsDetail";
import CurationsRegister from "./routes/CurationsPage/CurationsRegister";

import CurationsOpenVidu from './routes/CurationsPage/CurationsOpenVidu';


// 커미션 페이지
import CommissionsMain from "./routes/CommissionsPage/CommissionsMain";
import CommissionsDetail from "./routes/CommissionsPage/CommissionsDetail";

// 마이페이지
import MyPageLayout from "./routes/MyPage/Layout";
import { loader as myPageLoader } from "./routes/MyPage/Layout"

// 작품탭
import ArtsRoot from "./routes/MyPage/ArtsRoot"
import ArtsFavorite from "./routes/MyPage/ArtsFavorite";
import ArtsIndex from "./routes/MyPage/ArtsIndex";
import ArtsMyCollections from "./routes/MyPage/ArtsMyCollections";
// 공지사항탭
import NoticesRoot from "./routes/MyPage/NoticesRoot";
import NoticesMine from "./routes/MyPage/NoticesMine";
import NoticesFollowing from "./routes/MyPage/NoticesFollowing";
import NoticesDetail, {loader as noticeLoader} from "./routes/MyPage/NoticesDetail"
import { getNotices } from "./notices"
// 큐레이션탭
import CurationsRoot from "./routes/MyPage/CurationsRoot";
import CurationsMine from "./routes/MyPage/CurationsMine";
import CurationsFollowing from "./routes/MyPage/CurationsFollowing";
import CurationsBookmark from "./routes/MyPage/CurationsBookmark";
// 커미션탭
import Commissions from "./routes/MyPage/Commissions"
import MyPageCommissionsDetail from "./routes/MyPage/CommissionsDetail"
// 모달(프로필 수정, 작품 업로드, 대표작품 설정)
import EditProfile from "./routes/MyPage/EditProfile";
// 작품 업로드
import Upload from "./routes/MyPage/Upload"
// 대표작품 설정
import SetMasterpiece from "./routes/MyPage/SetMasterpiece"
import CommissionsRegister from "./routes/CommissionsPage/CommissionsRegister";


const createStoreWithMiddleware = applyMiddleware(promiseMiddleware, ReduxThunk)(createStore)

const router = createBrowserRouter(
  createRoutesFromElements(
    <Route>
      {/* 로그인 */}
      <Route path="/login" element={ <LoginPage /> } />
      {/* 회원가입 */}
      <Route path="/signup" element={ <SignUpPage/> } />

      {/* Nav바 + Footer */}
      <Route
        path="/"
        element={ <Layout /> }
        errorElement={ <Error /> }
      >
        <Route errorElement={ <Error /> }>
          {/* 랜딩 페이지 */}
          <Route index element={ <LandingPage /> } loader={LandingLoader} errorElement={ <Error /> } />

          {/* 작품 페이지 */}
          <Route path="arts" element={ <ArtsMain /> } />
          <Route path=":nickname/:art_seq" element={ <ArtsDetail /> } loader={ArtsDetailLoader} />

          {/* 큐레이션 페이지 */}
          <Route path="curations" element={ <CurationsMain /> } loader={curationListLoader}/>
          <Route path="curations/on_air" element={ <CurationsOnAir /> } />

          <Route path="curations/on_air/CurationsOpenVidu" element={<CurationsOpenVidu/>}/>

          <Route path="curations/upcoming" element={ <CurationsUpcoming /> } />
          <Route path="curations/end" element={ <CurationsEnd /> } />
          <Route path="curations/detail" element={ <CurationsDetail /> } />
          <Route path="curations/register" element={ <CurationsRegister /> } />

          {/* 커미션 페이지 */}
          <Route path="commissions" element={ <CommissionsMain /> } />
          <Route path="commissions/detail" element={ <CommissionsDetail /> } />
          <Route path="commissions/detail/register" element={ <CommissionsRegister/> } />

          {/*마이페이지*/}
          <Route
            path=":nickname_user_seq"
            element={<MyPageLayout />}
            loader={myPageLoader}
          >

            <Route
              path="arts"
              element={<ArtsRoot />}
            >
              <Route index element={ <ArtsIndex /> }>
                {/* 대표작품 설정 페이지 들어갈거임 */}
              </Route>
              <Route path="favorite" element={ <ArtsFavorite /> }/>
              <Route path="owns" element={ <ArtsMyCollections /> } />
            </Route>

            <Route
              path="notices"
              element={ <NoticesRoot /> }
            >
              <Route
                path="mine"
                element={ <NoticesMine /> }
                loader={getNotices}/>
              <Route
                path="following"
                element={ <NoticesFollowing /> }
                loader={getNotices}
              />
              <Route
                path=":notice_id"
                element={<NoticesDetail/>}
                loader={noticeLoader}
              />
            </Route>


            <Route
              path="curations"
              element={ <CurationsRoot /> }
            >
              <Route path="mine" element={ <CurationsMine /> } />
              <Route path="following" element={ <CurationsFollowing /> } />
              <Route path="bookmark" element={ <CurationsBookmark /> } />
            </Route>

            <Route path="commissions" element={ <Commissions /> } />
            <Route path="commissions/detail" element={ <MyPageCommissionsDetail /> } />

            <Route path="arts/edit_profile" element={ <EditProfile /> }></Route>
            <Route path="arts/upload" element={ <Upload /> }></Route>
            <Route path="arts/set_masterpiece" element={ <SetMasterpiece /> }></Route>

          </Route>
        </Route>
      </Route>

    </Route>
  )
)
let store = createStoreWithMiddleware(rootReducer,
  window.__REDUX_DEVTOOLS_EXTIONSION__ &&
  window.__REDUX_DEVTOOLS_EXTIONSION__()
  )


const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <Provider
      store = {store}
    >
      <RouterProvider router={router} />
    </Provider>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
export default store 