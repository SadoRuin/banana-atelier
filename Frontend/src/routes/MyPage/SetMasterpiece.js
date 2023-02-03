import React, { useState } from 'react'
import { Form, useNavigate } from 'react-router-dom';

function SetMasterpiece() {
  const masterpieces = [
    {
      id: 1,
      title: '작품1'
    },
    {
      id: 2,
      title: '작품2'
    },
    {
      id: 3,
      title: '작품3'
    },
    {
      id: 4,
      title: '작품4'
    },
    {
      id: 5,
      title: '작품5'
    },
    {
      id: 6,
      title: '작품6'
    },
    {
      id: 7,
      title: '작품7'
    },
  ]

  const navigate = useNavigate();
  const [selectedPieces, setSelectedPieces] = useState([]);

  const handleSelected = () => {
  //   힘들어서... 좀이따 할게
  }


  return (
    <div>
      <div>
        <h2>대표작품 설정하기</h2>
        <div>
          <p>최대 6개까지 지정할 수 있습니다.</p>
          <p>다른 사람들에게 나의 대표작품을 보여주자구~</p>
        </div>
      </div>
      <Form method="post">
        <div className="selected_masterpiece">
          <h4>현재 작품</h4>
          { console.log(selectedPieces) }
          { selectedPieces.map((piece) => {
            return (
              piece?.title
            )
          }) }
        </div>
        <div className="all_masterpiece">
          <h4>전체 작품</h4>
          {/* 여기는 나의 작품목록 map 돌기 */}
          { masterpieces.map((masterpiece)=>{
            return (
              <label key={`masterpiece-${masterpiece.id}`}>
                {/*<img src="작품img" alt="작품 이미지"/> */}
                <input type="checkbox" name="selected" value={masterpiece.id} onChange={handleSelected} />
                {masterpiece.title}
              </label>
            )
          }) }
        </div>
        <button type="submit">저장하기</button>
        <button onClick={()=>{ navigate(-1) }}>취소</button>
      </Form>
    </div>
  )
}

export default SetMasterpiece
