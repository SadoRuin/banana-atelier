package com.ssafy.banana.dto.response;

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
	private int isAuction;
	private int auctionGap;
	private int auctionPeopleCnt;
	private String curationThumbnail;
	private String artistNickName;
	private int artLikes;
	private int artHit;

	public CurationArtDataResponse(com.ssafy.banana.db.entity.CurationArt ca) {
		this.id = ca.getId();
		this.isAuction = ca.getIsAuction();
		this.auctionGap = ca.getAuctionGap();
		this.auctionPeopleCnt = ca.getAuctionPeopleCnt();
		this.curationThumbnail = ca.getArt().getArtThumbnail();
		this.artistNickName = ca.getArt().getArtist().getUser().getNickname();
		this.artLikes = ca.getArt().getArtLikeCount();
		this.artHit = ca.getArt().getArtHit();
	}
}
