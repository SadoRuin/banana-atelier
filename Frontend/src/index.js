import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import reportWebVitals from './reportWebVitals';
import { Provider } from 'react-redux';
import { applyMiddleware, createStore } from 'redux';
import promiseMiddleware from 'redux-promise'
import ReduxThunk from 'redux-thunk'
import Reducer from './_reducers'

import { Route, createBrowserRouter, createRoutesFromElements, RouterProvider } from "react-router-dom";

// 로그인 & 회원가입
import LoginPage from "./routes/LoginPage/LoginPage";
import SignUpPage from "./routes/SignUpPage/SignUpPage";

// 전체 Layout(Nav바랑 Footer)
import Layout from "./routes/LayoutPage/Layout"

// 랜딩 페이지
import LandingPage from "./routes/LandingPage/LandingPage";

// 작품 페이지
import ArtsPage from "./routes/ArtsPage/ArtsPage";
import ArtsDetailPage from "./routes/ArtsPage/ArtsDetailPage";

// 큐레이션 페이지
import CurationsPage from "./routes/CurationsPage/CurationsPage";
import CurationsOnAir from "./routes/CurationsPage/CurationsOnAir"
import CurationsUpcoming from "./routes/CurationsPage/CurationsUpcoming";
import CurationsFinish from "./routes/CurationsPage/CurationsFinish";
import CurationsDetailPage from "./routes/CurationsPage/CurationsDetailPage";
import CurationsRegister from "./routes/CurationsPage/CurationsRegister";

// 커미션 페이지
import CommissionsPage from "./routes/CommissionsPage/CommissionsPage";
import CommissionsDetailPage from "./routes/CommissionsPage/CommissionsDetailPage";

// 마이페이지
import MyPageLayout from "./routes/MyPage/MyPageLayout";
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
import CommissionsDetail from "./routes/MyPage/CommissionsDetail"
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
      >
        {/* 랜딩 페이지 */}
        <Route index element={ <LandingPage /> } />

        {/* 작품 페이지 */}
        <Route path="arts" element={ <ArtsPage /> } />
        <Route path="arts/detail" element={ <ArtsDetailPage /> } />

        {/* 큐레이션 페이지 */}
        <Route path="curations" element={ <CurationsPage /> } />
        <Route path="curations/on_air" element={ <CurationsOnAir /> } />
        <Route path="curations/upcoming" element={ <CurationsUpcoming /> } />
        <Route path="curations/finish" element={ <CurationsFinish /> } />
        <Route path="curations/detail" element={ <CurationsDetailPage /> } />
        <Route path="curations/register" element={ <CurationsRegister /> } />

        {/* 커미션 페이지 */}
        <Route path="commissions" element={ <CommissionsPage /> } />
        <Route path="commissions/detail" element={ <CommissionsDetailPage /> } />
        <Route path="commissions/detail/register" element={ <CommissionsRegister/> } />

        {/*마이페이지*/}
        <Route
          path="mypage"
          element={<MyPageLayout />}
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
          <Route path="commissions/detail" element={ <CommissionsDetail /> } />

          <Route path="arts/edit_profile" element={ <EditProfile /> }></Route>
          <Route path="arts/upload" element={ <Upload /> }></Route>
          <Route path="arts/set_masterpiece" element={ <SetMasterpiece /> }></Route>

        </Route>
      </Route>

    </Route>
  )
)



const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <Provider
      store = {createStoreWithMiddleware(Reducer,
        window.__REDUX_DEVTOOLS_EXTIONSION__ &&
        window.__REDUX_DEVTOOLS_EXTIONSION__()
        )}
    >
      <RouterProvider router={router} />
    </Provider>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
