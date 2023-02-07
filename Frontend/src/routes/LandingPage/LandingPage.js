import React from 'react';
import {Link, useLoaderData} from 'react-router-dom';
import axios from 'axios'

import ArtItem from "../../components/commons/artItem";

export async function loader () {
  const arts = await axios.get('https://i8a108.p.ssafy.io/api/arts' )
    .then(response=>response)
    .catch(error=>console.log(error))
  return { arts };
}

function LandingPage() {
  const {arts} = useLoaderData();

  return (
    <div>

      <div>
        <h1><Link to="curations">큐레이션🍌</Link></h1>
        <p>현재 진행중인</p>
      </div>

      <div>
        <h1><Link to="commissions">요즘 뜨는 커미션 작가💰</Link></h1>
        <p>이 곳에는 요즘 뜨는 커미션 작가가 들어간다</p>
      </div>

      <div>
        <h1><Link to="arts">트렌딩🔥</Link></h1>
        <p>이 곳에는 요즘 인기가 많아진 작품들이 들어간다.</p>
        <div>
          {arts.data.map((art) => <div key={art.art_seq}>
            <ArtItem
              nickname={art.nickname}
              art_seq={art.art_seq}
              art_hit={art.art_hit}
              art_name={art.art_name}
              art_thumbnail={art.art_thumbnail}
              art_like_count={art.art_like_count}
            />
          </div>)}
        </div>
      </div>
      
    </div>
  );
}

export default LandingPage
