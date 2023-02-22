import React from 'react';
import ReactDOM from 'react-dom/client';
import { applyMiddleware, createStore } from 'redux';
import { Provider } from 'react-redux';
import promiseMiddleware from 'redux-promise'
import ReduxThunk from 'redux-thunk'
import { persistStore } from "redux-persist"
import { PersistGate } from "redux-persist/integration/react"
import { createBrowserRouter, createRoutesFromElements, Route, RouterProvider } from "react-router-dom";
import reportWebVitals from './reportWebVitals';

import rootReducer from './_reducers'
import './index.css';

// 로그인 & 회원가입
import LoginPage from "./routes/LoginPage/LoginPage";
import SignUpPage from "./routes/SignUpPage/SignUpPage";

// 에러 페이지
import Error from './routes/Error'

// 전체 Layout(Nav바랑 Footer)
import Layout from "./routes/LayoutPage/Layout"

// 랜딩 페이지
import LandingPage, { loader as LandingLoader } from "./routes/LandingPage/LandingPage";

// 작품 페이지
import ArtsMain, { loader as ArtsLoader } from "./routes/ArtsPage/ArtsMain";
import ArtDetail, { action as ArtDetailAction, loader as ArtDetailLoader } from "./routes/ArtsPage/ArtDetail";

// 큐레이션 페이지
import CurationsMain, { loader as CurationsMainLoader } from "./routes/CurationsPage/CurationsMain";
import CurationsEnd from "./routes/CurationsPage/CurationsEnd";
import CurationDetail, { loader as CurationDetailLoader, action as CurationDetailAction } from "./routes/CurationsPage/CurationDetail";
import Openvidu, { loader as OpenviduLoader } from "./routes/CurationsPage/openvidu";

// 마이페이지
import MyPageLayout, { loader as MyPageLoader } from "./routes/MyPage/Layout";
// 작품탭
import MyPageArts, { loader as MyPageArtsLoader } from "./routes/MyPage/Arts";
// 공지사항탭
import MyPageNotices, { action as MyPageNoticesAction, loader as MyPageNoticesLoader } from "./routes/MyPage/Notices";
import MyPageNoticeDetail, { loader as MyPageNoticeDetailLoader } from "./routes/MyPage/NoticeDetail"
// 큐레이션탭
import MyPageCurations, { loader as MyPageCurationsLoader } from "./routes/MyPage/Curations";
import MyPageCurationRegister, { loader as MyPageCurationRegisterLoader } from "./routes/MyPage/CurationRegister";
// 프로필 수정
import EditProfile from "./routes/MyPage/EditProfile";
// 작품 업로드
import Upload, { action as UploadAction } from "./routes/MyPage/Upload"
// 대표작품 설정
import SetMasterpiece, { action as SetMasterpieceAction, loader as SetMasterpieceLoader } from "./routes/MyPage/SetMasterpiece"


const createStoreWithMiddleware = applyMiddleware(promiseMiddleware, ReduxThunk)(createStore)

const router = createBrowserRouter(
  createRoutesFromElements(
    <Route>
      {/* 로그인, 회원가입 */}
      <Route path="/login" element={ <LoginPage /> } />
      <Route path="/signup" element={ <SignUpPage /> } />

      {/* Nav바 + Footer */}
      <Route path="/" element={ <Layout /> } errorElement={ <Error /> }>
        <Route errorElement={ <Error /> }>

          {/* 랜딩 페이지 */}
          <Route index element={ <LandingPage /> } loader={ LandingLoader } errorElement={ <Error /> } />

          {/* 작품 페이지 */}
          <Route path="arts" element={ <ArtsMain /> } loader={ ArtsLoader } />
          <Route path=":nickname/:art_seq" element={ <ArtDetail /> } loader={ ArtDetailLoader } action={ ArtDetailAction } />

          {/* 큐레이션 페이지 */}
          <Route path="curations" element={ <CurationsMain /> } loader={ CurationsMainLoader } />
          <Route path="curations/end" element={ <CurationsEnd /> } />
          <Route path="curations/detail/:curation_seq" element={ <CurationDetail /> } loader={ CurationDetailLoader } action={ CurationDetailAction } />
          <Route path="curations/on_air/:curation_seq" element={ <Openvidu /> } loader={ OpenviduLoader } />

          {/*마이페이지*/}
          <Route path=":nickname_user_seq" element={ <MyPageLayout /> } loader={ MyPageLoader }>
            <Route errorElement={ <Error /> }>
              {/* 마이페이지 작품 */}
              <Route index element={ <MyPageArts /> } loader={ MyPageArtsLoader } />
              {/* 마이페이지 공지 */}
              <Route path="notices" element={ <MyPageNotices /> } loader={ MyPageNoticesLoader } action={ MyPageNoticesAction }>
                <Route path=":notice_id" element={ <MyPageNoticeDetail /> } loader={ MyPageNoticeDetailLoader } />
              </Route>
              {/* 마이페이지 큐레이션*/}
              <Route path="curations" element={ <MyPageCurations /> } loader={ MyPageCurationsLoader } />
              {/* 마이페이지 프로필수정/업로드/대표작품 설정 */}
              <Route path="edit_profile" element={ <EditProfile /> } />
              <Route path="upload" element={ <Upload /> } action={ UploadAction } />
              <Route path="set_masterpiece" element={ <SetMasterpiece /> } loader={ SetMasterpieceLoader } action={ SetMasterpieceAction } />
              <Route path="curation_register" element={ <MyPageCurationRegister /> } loader={ MyPageCurationRegisterLoader } />
            </Route>
          </Route>
        </Route>
      </Route>
    </Route>
  )
)

let store = createStoreWithMiddleware(rootReducer,
  window.__REDUX_DEVTOOLS_EXTENSION__ &&
  window.__REDUX_DEVTOOLS_EXTENSION__()
)

let persistor = persistStore(store)

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <Provider store={store}>
    <PersistGate loading={null} persistor={persistor}>
      <RouterProvider router={router}/>
    </PersistGate>
  </Provider>

);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
export default store 