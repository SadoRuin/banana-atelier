import React, {useState} from 'react';
import { useNavigate, useLoaderData, redirect } from "react-router-dom";
import { axiosAuth, axiosReissue } from '../../_actions/axiosAuth';

import CurationPieceItem from "../../components/MyPage/CurationPieceItem";

export async function loader ({params}) {
  console.log(params);
  const [, userSeq] = params.nickname_user_seq.split('@')
  console.log(userSeq);
  axiosReissue();
  const userArts = await axiosAuth.get(`arts/${userSeq}`)
    .then(response => response.data)
    .catch(error=> console.log(error))
  return userArts;
}


function CurationsRegister() {
  const userArts = useLoaderData();
  const navigate = useNavigate();
  const [curationsList, setCurationsList] = useState([]);

  console.log(userArts);

  const handleSelected = (e) => {
    console.log('눌렸음!');
    let artSeq = Number(e.target.value);
    if (e.target.checked) {
      if (curationsList.length === 10) {
        alert('최대 10개의 작품만 선택할 수 있습니다');
        e.target.checked = false;
      }
      else {
        let auctionStartPrice = +prompt("경매 시작 희망가를 입력해주세요(숫자만)");
        while ( !Number.isInteger(auctionStartPrice)) {
          if (auctionStartPrice === "" || auctionStartPrice === null) {
            break
          }
          alert('숫자만 입력해주세요!')
          auctionStartPrice = +prompt("경매 시작 희망가를 입력해주세요(숫자만)");
        }
        let auctionGap = +prompt("경매 호가 단위를 입력해주세요(숫자만)");
        while (!Number.isInteger(auctionGap)) {
          if (auctionGap === "" || auctionGap === null) {
            break
          }
          alert('숫자만 입력해주세요!')
          auctionGap = +prompt("경매 호가 단위를 입력해주세요(숫자만)");
        }
        console.log(auctionGap, auctionStartPrice)

        if (Number.isInteger(auctionGap) && Number.isInteger(auctionStartPrice) && auctionStartPrice > 0 && auctionGap > 0){
          const curationArt = userArts.find((art)=>art.artSeq === artSeq);
          const newCurationArt = {
            art: curationArt,
            auctionStartPrice: auctionStartPrice,
            auctionGap: auctionGap
          }
          setCurationsList((prev) => [...prev, newCurationArt]);
        }

      }
    }
    else {
      setCurationsList(prev => prev.filter((art)=>art.art.artSeq !== artSeq));
    }
  }
  const handleSubmit = async (e) => {
    e.preventDefault();

    const curationArtList = curationsList.map((art) => {
      return {
        artSeq: art.art.artSeq,
        auctionGap: art.auctionGap,
        auctionStartPrice: art.auctionStartPrice
      }
    })
    const artistSeq = +localStorage.getItem('userSeq');
    const curationName = document.querySelector("#curationName").value;
    const curationStartTime = document.querySelector("#curationStartTime").value + ':00';
    const curationSummary = document.querySelector("#curationSummary").value;
    const body = {
      artistSeq: artistSeq,
      curationArtList: curationArtList,
      curationName: curationName,
      curationStartTime: curationStartTime,
      curationSummary: curationSummary
    }

    await axiosAuth.post('curations', body)
      .then(() => redirect('/curations'))
      .catch(error => console.log(error))
  }

  const todayDay = new Date();
  const year = todayDay.getFullYear();
  const month = todayDay.getMonth();
  const day = todayDay.getDate() + 1;

  const minDay = (new Date(year, month, day)).toISOString().slice(0, -8);
  const maxDay = (new Date(year, month, day+14)).toISOString().slice(0, -8);

  return (
    <div>
      <div>
        <h3><span onClick={()=>{ navigate(-1) }} style={{cursor: "pointer"}} >{`<`} </span>큐레이션 등록하기</h3>
        <p style={{color: "#656565"}}>
          2주 이내에 큐레이션을 진행해보세요! <br/>
          최대 10개의 작품을 가지고 팬들과 소통할 수 있습니다. <br/>
          팬들이 구매 의사가 있다면 경매도 진행할 수 있습니다.
        </p>
        <div>
        </div>
      </div>

      <form method="post" onSubmit={e => handleSubmit(e)}>
        <label>큐레이션 이름
          <input type="text" id="curationName" name="curationName" placeholder="큐레이션 이름"/>
        </label>

        <label>큐레이션 진행 날짜
          <input type="datetime-local" id="curationStartTime" name="curationStartTime" min={minDay} max={maxDay}/>
        </label>

`        <label>큐레이션 설명
`          <textarea id="curationSummary" name="curationSummary" cols="30" rows="10" placeholder="큐레이션에 대한 설명을 적어주세요"></textarea>
        </label>

        <button type="submit">등록하기</button>

        <div>
          <h4>선택된 작품</h4>
          { curationsList?.length?
            <div className="art-root__masterpiece-container">
              {curationsList.map(art =>
                <div >
                  <CurationPieceItem
                    key={`curation-register__selected-${art.art.artSeq}`}
                    artThumbnail={art.art.artThumbnail}
                    artName={art.art.artName}
                    userSeq={art.art.userSeq}
                  />
                  <div>
                    <p>경매 시작 가격 : {art.auctionStartPrice}</p>
                    <p>경매 호가 단위 : {art.auctionGap}</p>
                  </div>
                </div>
              )}
            </div> : <div>선택된 작품이 없습니다.</div>
          }
        </div>

        <div>
          <h4>전체 작품</h4>
          <div className="art-root__arts-container">
            { userArts.map((art)=>{
              const isChecked = !!curationsList?.find((a) => a.art.artSeq === art.artSeq)
              return (
                <label key={`curation-register__arts-${art.artSeq}`}>
                  { art.isSold? null :
                     ( isChecked ? <input style={{display: "none"}} type="checkbox" name="artSeq" value={art.artSeq} onChange={handleSelected} checked />
                        : <input style={{display: "none"}} type="checkbox" name="artSeq" value={art.artSeq} onChange={handleSelected}/>)
                  }
                  <CurationPieceItem
                    key={`curation-register__selected-${art.artSeq}`}
                    isChecked={isChecked}
                    artThumbnail={art.artThumbnail}
                    artName={art.artName}
                    userSeq={art.userSeq}
                    isSold={art.isSold}
                  />
                </label>
              )})
            }
          </div>
        </div>

      </form>
    </div>
  );
}

export default CurationsRegister;