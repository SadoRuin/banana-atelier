import React from 'react';
import styled from 'styled-components';

export const TabMenu = styled.ul`
  display: flex;
  flex-direction: row;
  align-items: center;
  list-style: none;
  margin-bottom: 10px;
  margin-top: 10px;
  padding-left: 0;
  
  .submenu {
  // 기본 Tabmenu 에 대한 CSS를 구현
    display: flex;
    padding-right: 10px;
    cursor: pointer;
  }

  .focused {
   //선택된 Tabmenu 에만 적용되는 CSS를 구현
    font-weight: bold;
  }

  & div.desc {
    text-align: center;
  }
`;

export const TabContent = styled.div`
  text-align: center;
`;

export default function TabMenuComponent({menuData, onAirIndex, setOnAirIndex}) {

  return (
    <div>
      <TabMenu>
        { menuData.map((tab, index) =>
          <li
            className={index === onAirIndex ? "submenu focused" : "submenu" }
            onClick={ () => setOnAirIndex(index) }
          >
            {tab.name}
          </li>
        )}
      </TabMenu>
      <TabContent>
        { menuData[onAirIndex].content.map((curation) =>
          <div>
            {curation.curationName} | {curation.curationStartTime} | {curation.curationBmCount}
          </div>
        )}
      </TabContent>
    </div>
  );
}



