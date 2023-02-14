import React, {useState} from 'react';
import { useNavigate, Form } from "react-router-dom";
import { axiosAuth } from '../../_actions/axiosAuth';
// import axiosCustom from '../../_actions/axiosCustom';
import { axiosReissue } from '../../_actions/axiosAuth';


function CurationsRegister(props) {
  let userSeq = localStorage.getItem("userSeq")
  
  
  
  axiosReissue()
  
  // const myArts = axiosAuth.get(`arts/${userSeq}`)
  //   .then(response => response.data)
  //   .catch(error => console.log(error))
  const myArts1 = axiosAuth.get(`arts/${userSeq}`)
    .then(response => response.data)
    .catch(error => console.log(error));

  myArts1.then(result => {
    console.log('첫번째 케이스:',result); 
    
  });


  async function getThumbnail(){
    try {
      const myArts = await axiosAuth.get(`arts/${userSeq}`);
      console.log('두번째 케이스 : ', myArts.data[0]['artThumbnail']);
      const myArtsName = myArts.data[0]['artName']
      return myArtsName
    } catch (error) {
      console.log(error);
    }
  }
  let thumbNailP =  getThumbnail();

  console.log('섬네일피',thumbNailP)

  



  const masterpieces = [
    
  ]
  
  
  // myArts?.map((item, index) => (
  //   <div key={index}>
  //     {item}
  //   </div>
  // ))  

  const curationOn = axiosAuth.get('curations/on')
    .then(response => response.data)
    .catch(error => console.log('error:', error))
  console.log('curationOn :', curationOn)


  const navigate = useNavigate();

  const [selectedPieces, setSelectedPieces] = useState([]);

  const handleSelected = (e) => {
    let pieceId = Number(e.target.value);
    console.log(pieceId);
    if (e.target.checked) {
      if (selectedPieces.length === 10) {
        alert('최대 10개의 작품만 선택할 수 있습니다');
        e.target.checked = false;
      }
      else {
        setSelectedPieces((prev) => [...prev, masterpieces.find((masterpiece)=>masterpiece.id === pieceId)]);
      }
    }
    else {
      setSelectedPieces(prev => prev.filter((piece)=>piece.id !== pieceId));
    }
  }

  const getDateToString = (date) => {
    const year = date.getFullYear();
    const month = date.getMonth();
    const day = date.getDate();
    return `${String(year)}-${String(month+1).padStart(2, "0")}-${String(day).padStart(2, "0")}`
  }

  const todayDay = new Date();
  const year = todayDay.getFullYear();
  const month = todayDay.getMonth();
  const day = todayDay.getDate();

  const minDay = getDateToString(new Date(year, month, day));
  const maxDay = getDateToString(new Date(year, month, day+14));

  const newMinDay = todayDay.toISOString()
  console.log('newMindat :',newMinDay)

  console.log(minDay);
  console.log(maxDay);
  return (
    <div>
      <div>
        <h3 onClick={()=>{ navigate(-1) }}>{`<`}</h3>
        <h3>큐레이션 등록하기</h3>
        <p>
          2주 이내에 큐레이션을 진행해보세요! <br/>
          최대 10개의 작품을 가지고 팬들과 소통할 수 있습니다. <br/>
          팬들이 구매 의사가 있다면 경매도 진행할 수 있습니다.
        </p>
        <div>
        </div>
      </div>

      <Form>
        <label>큐레이션 이름
          <input type="text" name="curation_title" placeholder="큐레이션 이름"/>
          
        </label>
        <label>큐레이션 진행 날짜
          {/* date에서 min과 max 속성을 통해 최대 2주까지 선택하게 하면 될듯 함 */}
          <input type="date" min={minDay} max={maxDay} />
        </label>
        <label>
          <textarea name="curation_content" id="curation_content" cols="30" rows="10" placeholder="큐레이션에 대한 설명을 적어주세요"></textarea>
        </label>

        <button type="submit">등록하기</button>
        <div>선택된 작품
          { selectedPieces.map(piece => <div key={piece.id}>{piece?.title}</div>) }
        </div>
        <div>전체 작품
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
      </Form>
    </div>
  );
}

export default CurationsRegister;