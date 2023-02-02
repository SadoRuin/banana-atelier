import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { Provider } from 'react-redux';
import { applyMiddleware, createStore } from 'redux';
import promiseMiddleware from 'redux-promise'
import ReduxThunk from 'redux-thunk'
import Reducer from './_reducers'
import { Route, createBrowserRouter, createRoutesFromElements, RouterProvider } from "react-router-dom";

import Layout from "./routes/MyPage/Layout";

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

// 아래 3개는 모달로 해야할듯?
// 프로필 수정
import EditProfile from "./routes/MyPage/EditProfile";

// 작품 업로드
import Upload from "./routes/MyPage/Upload"

// 대표작품 설정
import SetMasterpiece from "./routes/MyPage/SetMasterpiece"


const createStoreWithMiddleware = applyMiddleware(promiseMiddleware, ReduxThunk)(createStore)

const router = createBrowserRouter(
  createRoutesFromElements(
    <Route
      path="/mypage"
      element={<Layout />}
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

      <Route
        path="commissions"
        element={ <Commissions /> }
      >
      </Route>
      <Route path="commissions/detail" element={ <CommissionsDetail /> } />

      <Route path="edit_profile" element={ <EditProfile /> }></Route>
      <Route path="upload" element={ <Upload /> }></Route>
      <Route path="set_masterpiece" element={ <SetMasterpiece /> }></Route>

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
