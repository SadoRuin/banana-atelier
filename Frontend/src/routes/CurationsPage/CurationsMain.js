import React, { useState } from 'react'
import { Link, useLoaderData } from 'react-router-dom';
import customAxios from '../../_actions/customAxios'

import {TabMenu, TabContent} from "../../components/commons/tabMenu";

export function loader() {
  const curationsList = customAxios().get(`curations/main`)
    .then(response=>response.data)
    .catch(error=>console.log(error))
  return curationsList;
}

function CurationsMain() {
  const curationsList = useLoaderData();

  const upcommingCurationsList = [];
  const onAirCurationsList = [];
  const endCurationsList = [];
  curationsList.map((curation) => {
    if (curation.curationStatus === 'INIT') {
      return upcommingCurationsList.push(curation)
    }
    else if (curation.curationStatus === 'ONAIR') {
      return onAirCurationsList.push(curation)
    }
    return endCurationsList.push(curation)
  })

  const [onAirIndex, setOnAirIndex] = useState(0);
  const [upcommingIndex, setUpcommingIndex] = useState(0);
  const [endIndex, setEndIndex] = useState(0);

  const onAirCurations = [
    { name: '북마크를 많이 받은 순', content: [...onAirCurationsList].sort((a, b) => b.curationBmCount - a.curationBmCount) },
    { name: '최신순', content: [...onAirCurationsList]
        .sort((a, b) =>
          b.curationStartTime[0] - a.curationStartTime[0] ||
          b.curationStartTime[1] - a.curationStartTime[1] ||
          b.curationStartTime[2] - a.curationStartTime[2] ||
          b.curationStartTime[3] - a.curationStartTime[3] ||
          b.curationStartTime[4] - a.curationStartTime[4] ||
          b.curationStartTime[5] - a.curationStartTime[5] )},
    { name: '오래된 순', content: [...onAirCurationsList]
        .sort((a, b) =>
          a.curationStartTime[0] - b.curationStartTime[0] ||
          a.curationStartTime[1] - b.curationStartTime[1] ||
          a.curationStartTime[2] - b.curationStartTime[2] ||
          a.curationStartTime[3] - b.curationStartTime[3] ||
          a.curationStartTime[4] - b.curationStartTime[4] ||
          a.curationStartTime[5] - b.curationStartTime[5] )},
  ];
  const upcommingCurations = [
    { name: '북마크를 많이 받은 순', content: [...upcommingCurationsList].sort((a, b) => b.curationBmCount - a.curationBmCount) },
    { name: '최신순', content: [...upcommingCurationsList]
        .sort((a, b) =>
          b.curationStartTime[0] - a.curationStartTime[0] ||
          b.curationStartTime[1] - a.curationStartTime[1] ||
          b.curationStartTime[2] - a.curationStartTime[2] ||
          b.curationStartTime[3] - a.curationStartTime[3] ||
          b.curationStartTime[4] - a.curationStartTime[4] ||
          b.curationStartTime[5] - a.curationStartTime[5] )},
    { name: '오래된 순', content: [...upcommingCurationsList]
        .sort((a, b) =>
          a.curationStartTime[0] - b.curationStartTime[0] ||
          a.curationStartTime[1] - b.curationStartTime[1] ||
          a.curationStartTime[2] - b.curationStartTime[2] ||
          a.curationStartTime[3] - b.curationStartTime[3] ||
          a.curationStartTime[4] - b.curationStartTime[4] ||
          a.curationStartTime[5] - b.curationStartTime[5] )},
  ];
  const endCurations = [
    { name: '북마크를 많이 받은 순', content: [...endCurationsList].sort((a, b) => b.curationBmCount - a.curationBmCount) },
    { name: '최신순', content: [...endCurationsList]
        .sort((a, b) =>
          b.curationStartTime[0] - a.curationStartTime[0] ||
          b.curationStartTime[1] - a.curationStartTime[1] ||
          b.curationStartTime[2] - a.curationStartTime[2] ||
          b.curationStartTime[3] - a.curationStartTime[3] ||
          b.curationStartTime[4] - a.curationStartTime[4] ||
          b.curationStartTime[5] - a.curationStartTime[5] )},
    { name: '오래된 순', content: [...endCurationsList]
        .sort((a, b) =>
          a.curationStartTime[0] - b.curationStartTime[0] ||
          a.curationStartTime[1] - b.curationStartTime[1] ||
          a.curationStartTime[2] - b.curationStartTime[2] ||
          a.curationStartTime[3] - b.curationStartTime[3] ||
          a.curationStartTime[4] - b.curationStartTime[4] ||
          a.curationStartTime[5] - b.curationStartTime[5] )},
  ];

  const handlerUpcommingIndex = (index) => {

  };
  const handlerEndIndex = (index) => {
    setEndIndex(index);
  };


  return (
    <div>

      <div>
        <Link to="on_air" className="link">
          <h2>진행중인 큐레이션</h2>
        </Link>
        <div>
          <TabMenu>
            { onAirCurations.map((tab, index) =>
              <li
                className={index === onAirIndex ? "submenu focused" : "submenu" }
                onClick={ () => setOnAirIndex(index) }
              >
                {tab.name}
              </li>
            )}
          </TabMenu>
          <TabContent>
            { onAirCurations[onAirIndex].content.map((curation) =>
              <div>
                {curation.curationName} | {curation.curationStartTime} | {curation.curationBmCount}
              </div>
            )}
          </TabContent>
        </div>
      </div>

      <div>
        <Link to="upcoming" className="link">
          <h2>진행 예정인 큐레이션</h2>
        </Link>
        <div>
          <TabMenu>
            { upcommingCurations.map((tab, index) =>
              <li
                className={index === upcommingIndex ? "submenu focused" : "submenu" }
                onClick={ () => setUpcommingIndex(index) }
              >
                {tab.name}
              </li>
            )}
          </TabMenu>
          <TabContent>
            { upcommingCurations[upcommingIndex].content.map((curation) =>
              <div>
                {curation.curationName} | {curation.curationStartTime} | {curation.curationBmCount}
              </div>
            )}
          </TabContent>
        </div>
      </div>

      <div>
        <Link to="end" className="link">
          <h2>종료된 큐레이션 {`>`}</h2>
        </Link>
        <div>
          <TabMenu>
            { endCurations.map((tab, index) =>
              <li
                className={index === endIndex ? "submenu focused" : "submenu" }
                onClick={ () => setEndIndex(index) }
              >
                {tab.name}
              </li>
            )}
          </TabMenu>
          <TabContent>
            { endCurations[endIndex].content.map((curation) =>
              <div>
                {curation.curationName} | {curation.curationStartTime} | {curation.curationBmCount}
              </div>
            )}
          </TabContent>
        </div>
      </div>

    </div>
  )
}

export default CurationsMain
