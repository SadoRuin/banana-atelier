import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import reportWebVitals from './reportWebVitals';
import {Provider} from 'react-redux';
import {applyMiddleware, createStore} from 'redux';
import promiseMiddleware from 'redux-promise'
import ReduxThunk from 'redux-thunk'
import rootReducer from './_reducers'
import {persistStore} from "redux-persist"
import {PersistGate} from "redux-persist/integration/react"
import {createBrowserRouter, createRoutesFromElements, Route, RouterProvider} from "react-router-dom";

// 로그인 & 회원가입
import LoginPage from "./routes/LoginPage/LoginPage";
import SignUpPage from "./routes/SignUpPage/SignUpPage";

// 에러 페이지
import Error from './routes/Error'

// 전체 Layout(Nav바랑 Footer)
import Layout from "./routes/LayoutPage/Layout"

// 랜딩 페이지
import LandingPage, {loader as LandingLoader} from "./routes/LandingPage/LandingPage";

// 작품 페이지
import ArtsMain, {loader as ArtsLoader} from "./routes/ArtsPage/ArtsMain";
import ArtsDetail, {action as ArtsDetailAction, loader as ArtsDetailLoader} from "./routes/ArtsPage/ArtsDetail";

// 큐레이션 페이지
import CurationsMain, {loader as curationListLoader} from "./routes/CurationsPage/CurationsMain";
// import CurationsOnAir, {loader as CurationsOnAirLoader} from "./routes/CurationsPage/CurationsOnAir"
// import CurationsUpcoming from "./routes/CurationsPage/CurationsUpcoming";
import CurationsEnd from "./routes/CurationsPage/CurationsEnd";
import CurationsDetail, {loader as CurationsDetailLoader, action as CurationDetailAction} from "./routes/CurationsPage/CurationsDetail";

// import CurationsOpenVidu from './routes/CurationsPage/CurationsOpenVidu';
import Openvidu from "./routes/CurationsPage/openvidu";
import { loader as OpenviduLoader } from "./routes/CurationsPage/openvidu";

// 마이페이지
import MyPageLayout, {loader as MyPageLoader} from "./routes/MyPage/Layout";

// 작품탭
import ArtsRoot, {loader as ArtsMyPageLoader} from "./routes/MyPage/ArtsRoot";

// 공지사항탭
import NoticesRoot, {action as NoticesAction, loader as NoticesLoader} from "./routes/MyPage/NoticesRoot";
import NoticesDetail, {loader as noticeLoader} from "./routes/MyPage/NoticesDetail"

// 큐레이션탭
import CurationsRoot from "./routes/MyPage/CurationsRoot";
import { loader as CurationsRootLoader } from  "./routes/MyPage/CurationsRoot";
import CurationsRegister, {loader as CurationRegisterLoader} from "./routes/MyPage/CurationsRegister";

// 모달(프로필 수정, 작품 업로드, 대표작품 설정)
import EditProfile from "./routes/MyPage/EditProfile";
// 작품 업로드
import Upload, {action as UploadAction} from "./routes/MyPage/Upload"
// 대표작품 설정
import SetMasterpiece, { action as SetMasterpieceAction, loader as SetMasterpieceLoader } from "./routes/MyPage/SetMasterpiece"



const createStoreWithMiddleware = applyMiddleware(promiseMiddleware, ReduxThunk)(createStore)

const router = createBrowserRouter(
  createRoutesFromElements(
    <Route>
      {/* 로그인 */}
      <Route path="/login" element={<LoginPage/>}/>
      {/* 회원가입 */}
      <Route path="/signup" element={<SignUpPage/>}/>

      {/* Nav바 + Footer */}
      <Route
        path="/"
        element={<Layout/>}
        errorElement={<Error/>}
      >
        <Route errorElement={<Error/>}>
          {/* 랜딩 페이지 */}
          <Route index element={<LandingPage/>} loader={LandingLoader} errorElement={<Error/>}/>

          {/* 작품 페이지 */}
          <Route path="arts" element={<ArtsMain/>} loader={ArtsLoader}/>
          <Route path=":nickname/:art_seq" element={<ArtsDetail/>} loader={ArtsDetailLoader} action={ArtsDetailAction}/>

          {/* 큐레이션 페이지 */}
          <Route path="curations" element={<CurationsMain/>} loader={curationListLoader}/>
          <Route path="curations/detail/:curation_seq" element={<CurationsDetail/>} loader={CurationsDetailLoader} action={CurationDetailAction}/>
          <Route path="curations/on_air/:curation_seq" element={<Openvidu />} loader={OpenviduLoader} />


          {/*<Route path="curations/on_air" element={<CurationsOnAir/>} loader={CurationsOnAirLoader}/>*/}
          {/*<Route path="curations/upcoming" element={<CurationsUpcoming/>}/>*/}
          <Route path="curations/end" element={<CurationsEnd/>}/>


          {/* 커미션 페이지 */}
          {/*<Route path="commissions" element={ <CommissionsMain /> } />*/}
          {/*<Route path="commissions/detail" element={ <CommissionsDetail /> } />*/}
          {/*<Route path="commissions/detail/register" element={ <CommissionsRegister/> } />*/}

          {/*마이페이지*/}
          <Route
            path=":nickname_user_seq"
            element={<MyPageLayout/>}
            loader={MyPageLoader}
          >
            <Route errorElement={<Error/>}>
              {/* 마이페이지 작품 */}
              <Route
                index
                element={<ArtsRoot/>}
                loader={ArtsMyPageLoader}
              />
              {/* 마이페이지 공지 */}
              <Route
                path="notices"
                element={<NoticesRoot/>}
                loader={NoticesLoader}
                action={NoticesAction}
              >
                {/*<Route*/}
                {/*  path="mine"*/}
                {/*  element={ <NoticesMine /> }*/}
                {/*  loader={getNotices}/>*/}
                {/*<Route*/}
                {/*  path="following"*/}
                {/*  element={ <NoticesFollowing /> }*/}
                {/*  loader={getNotices}*/}
                {/*/>*/}
                <Route
                  path=":notice_id"
                  element={<NoticesDetail/>}
                  loader={noticeLoader}
                />
              </Route>

              {/* 마이페이지 큐레이션*/}
              <Route
                path="curations"
                element={<CurationsRoot/>}
                loader={CurationsRootLoader}
              />

              {/*<Route path="commissions" element={ <Commissions /> } />*/}
              {/*<Route path="commissions/detail" element={ <MyPageCommissionsDetail /> } />*/}

              <Route path="edit_profile" element={<EditProfile/>}></Route>
              <Route path="upload" element={<Upload/>} action={UploadAction}></Route>
              <Route path="set_masterpiece" element={<SetMasterpiece/>} loader={SetMasterpieceLoader}
                      action={SetMasterpieceAction}></Route>
              <Route path="curation_register" element={<CurationsRegister/>} loader={CurationRegisterLoader}/>
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
  <Provider
    store={store}
  >
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