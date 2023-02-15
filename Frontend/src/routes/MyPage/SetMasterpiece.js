import React, { useState } from 'react'
import {Form, useLoaderData, useNavigate, redirect} from 'react-router-dom';
import {axiosAuth, axiosReissue} from "../../_actions/axiosAuth";
import MasterpieceItem from "../../components/MyPage/MasterpieceItem";
import './ArtsRoot.css'

export async function loader({params}) {
  const userSeq = params.nickname_user_seq.split('@')[1];
  axiosReissue();

  const userArts = await axiosAuth.get(`arts/${userSeq}`)
    .then(response => response.data)
    .catch(() => null)

  const userMasterpiece = await axiosAuth.get(`arts/${userSeq}/masterpiece`)
    .then(response => response.data)
    .catch(() => null)

  return [userArts, userMasterpiece];
}

export async function action ({request}) {
  const formData = await request.formData();
  for (const [key, value] of formData){
    console.log(key, value);
  }

  const body = []
  for (const [key, value] of formData){
    body.push({
      [key]: value,
      represent: true
    })
  }
  console.log(body);
  await axiosAuth.put('arts/masterpiece', body)
  return redirect('../')
}

function SetMasterpiece() {
  const [userArts, userMasterpiece] = useLoaderData();
  const navigate = useNavigate();
  const [selectedPieces, setSelectedPieces] = useState(userMasterpiece? [...userMasterpiece] : []);

  const handleSelected = (e) => {
    let artSeq = Number(e.target.value);

    // 선택된 배열에 추가/삭제하는 함수
    if (e.target.checked) {
      if (selectedPieces.length === 6) {
        alert('최대 6개의 작품만 선택할 수 있습니다');
        e.target.checked = false;
      }
      else {
        setSelectedPieces(prev => [...prev, userArts.find((art)=>art.artSeq === artSeq)]);
      }
    }
    else {
      setSelectedPieces(prev => prev.filter((arts)=>arts.artSeq !== artSeq));
    }
  }

  console.log(userArts);

  return (
    <div>
      <div>
        <h3>대표작품 설정하기</h3>
        <div style={{color: '#656565'}}>
          최대 6개까지 지정할 수 있습니다.<br/>
          다른 사람들에게 나의 대표작품을 소개해보아요
        </div>
      </div>

      <Form method="post" id="masterpiece__form">
        <div className="selected_masterpiece">
          <h4>현재 대표작품</h4>
          { selectedPieces.length?
            <div className="art-root__masterpiece-container">
              {selectedPieces.map(art =>
                <MasterpieceItem
                  key={`my-page__masterpiece-selected-${art.artSeq}`}
                  artName={art.artName}
                  userSeq={art.userSeq}
                  artThumbnail={art.artThumbnail}
                />)
              }
            </div> : <div>선택된 작품이 없습니다.</div>
          }
        </div>

        <div className="all_masterpiece">
            <h4>전체 작품</h4>
            {/* 여기는 나의 작품목록 map 돌기 */}
              <div className="art-root__arts-container">
                { userArts.map(art => {
                  const isRepresent = !!selectedPieces?.find((a) => a.artSeq === art.artSeq)
                  return (
                    <label key={`my-page__masterpiece-all-${art.artSeq}`}>
                      {isRepresent ?
                        <input style={{display: "none"}} type="checkbox" name="artSeq" value={art.artSeq} onChange={handleSelected} checked/> :
                        <input style={{display: "none"}} type="checkbox" name="artSeq" value={art.artSeq} onChange={handleSelected}/>
                      }
                      <MasterpieceItem
                        artName={art.artName}
                        userSeq={art.userSeq}
                        artThumbnail={art.artThumbnail}
                        isRepresent={isRepresent}
                      />
                    </label>
                  )
                } )}
              </div>
            <button type="submit">저장하기</button>
            <button onClick={()=>{ navigate(-1) }}>취소</button>
        </div>
      </Form>

    </div>
  )
}

export default SetMasterpiece
