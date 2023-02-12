import React from 'react';
import { axiosAuth, axiosReissue } from "../../_actions/axiosAuth";
// import { useLoaderData, Form } from "react-router-dom";

export async function loader ({params}) {
  const [nickname, userSeq] = params.nickname_user_seq.split('@');

  axiosReissue();
  const userNotices = await axiosAuth(`notices/${userSeq}`)
    .then(response => response.data)
    .catch(() => null)
  const followingNotices = await axiosAuth(`notices/${userSeq}/following`)
    .then(response => response.data)
    .catch(() => null)

  console.log(userNotices);
  console.log(followingNotices);
  return [nickname, userSeq, userNotices, followingNotices]
}


function NoticesRoot() {
  // const [nickname, userSeq, userNotices, followingNotices] = useLoaderData();
  // const [nonticeIndex, setNoticeIndex] = useState(0)
  // const isMyPage = nickname === localStorage.getItem('nickname')
  // const isArtist = userData.role === 'ROLE_ARTIST'

  return (
    <div>

    </div>
  );
}

export default NoticesRoot;