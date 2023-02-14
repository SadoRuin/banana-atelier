import React from 'react'
import {Link, useLoaderData, redirect, Form } from 'react-router-dom'
import { axiosReissue } from '../../_actions/axiosAuth';
import { axiosAuth } from '../../_actions/axiosAuth';
import ProfileImg from "../../components/commons/ProfileImg";
import { getArtImage } from "../../components/commons/imageModule";
import {YellowBtn, RedBtn, LikeBtn} from "../../components/commons/buttons";
import {Category} from "../../components/commons/category";
import './ArtsDetail.css'
import { useState } from 'react';

export async function loader ({params}) {
  let artSeq = params.art_seq;
  
  const artData = await axiosAuth.get(`arts/detail/${artSeq}`)
    .then(response => response.data)
    .catch(error => error.response.status)
  
  const likeList = await axiosAuth.get(`arts/like`)
    .then(response=> response.data)
    .catch(() => null)

  console.log(artData);
  if (artData === 404) {
    throw new Response("", {
      status: 404,
      statusText: "작품을 찾을 수 없습니다!",
    });
  }
  else if (artData === 401) {
    return redirect('/login');
  }
  return [artData, likeList];
}

export async function action ({request, params}) {
  console.log(request);
  const artSeq = +params.art_seq;
  if (request.method === "DELETE") {
    const body = {
      "seq" : artSeq
    }
    console.log(body)
    await axiosAuth.delete('arts/delete', {
      data: {
        seq : artSeq
      }
    })
      .then(response => console.log(response))
      .catch(error => console.log(error))

  }
  else if (request.method === "PUT") {
    console.log("put")
  }

  return redirect('/')
}


function ArtsDetail() {

  const [artData, likeList] = useLoaderData();
  const [likeCnt, setLikeCnt] = useState(artData.artLikeCount);

  axiosReissue();
  let wonderValue = false
  let alreadyLike = likeList?.find((like) => like.artSeq === artData.artSeq);
  alreadyLike? wonderValue = true : wonderValue = false
  
  const [wonder, setWonder] = useState(wonderValue)
  return (
    <div>
      <div className="art-detail__container grid__detail-page">
        <img
          src={`${getArtImage(artData.artImg, artData.userSeq)}`}
          alt="작품 이미지"
          className="art-img"
        />

        {/* 작품 상세 정보 */}
        <div className="art-detail_content">
          <div className="art-detail__main-info">
            <h1>{artData.artName}</h1>
            <Link className="artist_profile link" to={`../${artData.nickname}@${artData.userSeq}`}>
              <ProfileImg height="30px" width="30px" url={artData.profileImg} userSeq={artData.userSeq} />
              <div>{artData.nickname} <span className="jakka">작가</span></div>
            </Link>

            <div className="upload_date">{`${artData.artRegDate[0]}.${(artData.artRegDate[1]+'').padStart(2, "0")}.${(artData.artRegDate[2]+'').padStart(2, "0")}.`}</div>
            {artData.userSeq === +localStorage.getItem('userSeq') ?
              <div className="art-detail__manage">
                <Form method="delete"><RedBtn type="submit">삭제하기</RedBtn></Form>
                <Form method="put">수정하기</Form>
              </div> : null
            }
            <div className="arts_description">
              {artData.artDescription}
            </div>


            <Category className="art-detail__category">
              {artData.artCategory.artCategoryName}
            </Category>
          </div>

          <div>
            <div className="art-detail__sub-info">
              <div className="views">
                <img src="ArtsMain" alt="" />
                조회수 : {artData.artHit}
              </div>
              <div className="downloaded">
                <img src="ArtsMain" alt="" />
                다운로드 : {artData.artDownloadCount}
              </div>
              <div className="likes">
                <img src="ArtsMain" alt="" />
                {/* 좋아요 : {artData.artLikeCount} */}
                좋아요 : {likeCnt}
              </div>
            </div>
            <div className="art-detail__btns">
              {/* 좋아요 누른 버튼이랑 안누른 버튼 */}
              


              <form onSubmit={(event) => {
                event.preventDefault()

                if (wonder) {
                  axiosReissue()
                  let body = {"seq": artData.artSeq}
                  axiosAuth.delete(`arts/like`, {data: body})
                  .then(response => console.log('싫어요 response', response))
                  .catch(error => {
                    console.log("이게 왜 에러지", error)
                  })
                  setLikeCnt(prev=>prev-1)
                  

                } else if (!wonder) {
                  let body = {"seq": artData.artSeq}
                  axiosReissue()
                  axiosAuth.post(`arts/like`, body)
                  .then(response => console.log('좋아요 response', response))
                  setLikeCnt(prev=>prev+1)
                }
                setWonder(prev=>!prev)

              }}>
                <LikeBtn isLike={wonder} />
              </form>
              



              <form action={`https://i8a108.p.ssafy.io/api/arts/download/${artData.artSeq}`} method="get">
                <YellowBtn type="submit">다운로드</YellowBtn>
              </form>
            </div>
          </div>
        </div>
      </div>

      <div className="familiar_arts">
        <h2>비슷한 작품</h2>
        <div>
          비슷한 작품 컴포넌트들이 올 곳
        </div>
      </div>


    </div>
  )
}

export default ArtsDetail