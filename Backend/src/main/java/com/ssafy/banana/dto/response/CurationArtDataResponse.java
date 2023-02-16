package com.ssafy.banana.dto.response;

import com.ssafy.banana.db.entity.CurationArt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CurationArtDataResponse {
	//큐레이션 디테일에서 필요한 작품 속성만 조회

	private long id;
	private int auctionStartPrice;
	private int auctionGap;
	private int auctionPeopleCnt;
	private String curationThumbnail;
	private String artistNickName;
	private int artLikes;
	private int artHit;
	private String artName;
	private long artSeq;

	public CurationArtDataResponse(CurationArt ca) {
		this.id = ca.getId();
		this.auctionStartPrice = ca.getAuctionStartPrice();
		this.auctionGap = ca.getAuctionGap();
		this.auctionPeopleCnt = ca.getAuctionPeopleCnt();
		this.curationThumbnail = ca.getArt().getArtThumbnail();
		this.artistNickName = ca.getArt().getArtist().getUser().getNickname();
		this.artLikes = ca.getArt().getArtLikeCount();
		this.artHit = ca.getArt().getArtHit();
		this.artName = ca.getArt().getArtName();
		this.artSeq = ca.getArt().getId();
	}
}

