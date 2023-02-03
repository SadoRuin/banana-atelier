import React from 'react';
import { useNavigate, Form } from "react-router-dom";

function CurationsRegister(props) {
  const navigate = useNavigate();


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
        여기는 대표작품 설정이랑 비슷해서, 거기 해결되면 될듯</div>
        <div>전체 작품</div>
      </Form>
    </div>
  );
}

export default CurationsRegister;