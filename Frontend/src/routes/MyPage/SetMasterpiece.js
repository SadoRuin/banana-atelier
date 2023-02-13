import React, { useState } from 'react'
import {Form, useLoaderData, useNavigate} from 'react-router-dom';
import {axiosAuth, axiosReissue} from "../../_actions/axiosAuth";
import ArtItemMyPage from "../../components/commons/ArtItemMyPage";

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
        setSelectedPieces(prev => [...prev, userArts.find((arts)=>arts.artSeq === artSeq)]);
      }
    }
    else {
      setSelectedPieces(prev => prev.filter((arts)=>arts.artSeq !== artSeq));
    }
  }


  return (
    <div>
      <div>
        <h2>대표작품 설정하기</h2>
        <div>
          <p>최대 6개까지 지정할 수 있습니다.</p>
          <p>다른 사람들에게 나의 대표작품을 보여줍시다!!~</p>
        </div>
      </div>

      <Form method="post">
        <div className="selected_masterpiece">
          <h4>현재 대표작품</h4>
          { selectedPieces.length?
            selectedPieces.map(arts => <ArtItemMyPage key={arts.artSeq} artName={arts.artName} artThumbnail={arts.artThumbnail}/>) :
            <div>선택된 작품이 없습니다.</div>
          }
        </div>

        <div className="all_masterpiece">
            <h4>전체 작품</h4>
            {/* 여기는 나의 작품목록 map 돌기 */}
            { userArts.map(arts=>{
              return (
                <label key={`my-page__masterpiece-all-${arts.id}`}>
                  {/*<img src="작품img" alt="작품 이미지"/> */}
                  <input type="checkbox" name="selected" value={arts.artSeq} onChange={handleSelected} />
                  <ArtItemMyPage artName={arts.artName} artThumbnail={arts.artThumbnail} />
                </label>
              )
            }) }
            <button type="submit">저장하기</button>
            <button onClick={()=>{ navigate(-1) }}>취소</button>

        </div>
      </Form>
    </div>
  )
}

export default SetMasterpiece
