import React from 'react';
import styled from 'styled-components';

export const TabMenu = styled.ul`
  display: flex;
  flex-direction: row;
  align-items: center;
  list-style: none;
  margin-bottom: 20px;
  margin-top: 10px;
  padding-left: 0;
  
  .submenu {
  // 기본 Tabmenu 에 대한 CSS를 구현
    display: flex;
    margin-right: 15px;
    padding-bottom: 5px;
    cursor: pointer;
    font-size: 16px;
  }

  .focused {
    //선택된 Tabmenu 에만 적용되는 CSS를 구현
    font-weight: bold;
    border-bottom: 2px solid #F9D923;
  }

  & div.desc {
    text-align: center;
  }
`;

export const TabContent = styled.div`
  width: 100%;
`;

export default function TabMenuComponent({menuData, index, setIndex}) {

  return (
    <div>
      <TabMenu>
        { menuData.map((tab, idx) =>
          <li
            className={idx === index ? "submenu focused" : "submenu" }
            onClick={ () => setIndex(idx) }
          >
            {tab.name}
          </li>
        )}
      </TabMenu>
      <TabContent>
        { menuData[index].content }
      </TabContent>
    </div>
  );
}



