package com.ssafy.banana.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.ssafy.banana.db.entity.Art;
import com.ssafy.banana.db.entity.Curation;
import com.ssafy.banana.db.entity.CurationArt;
import com.ssafy.banana.db.entity.enums.CurationStatus;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CurationArtDataResponse {
	//큐레이션 디테일에서 필요한 작품 속성만 조회
	@Data
	@NoArgsConstructor
	public static class CurationArtSimple {
		private long id;
		private boolean isAuction;
		private String curationThumbnail;
		private String artistNickName;
		private int artLikes;
		private int artHit;


		public CurationArtSimple(com.ssafy.banana.db.entity.CurationArt ca){
			this.id = ca.getId();
			this.isAuction = ca.isAuction();
			this.curationThumbnail = ca.getArt().getArtThumbnail();
			this.artistNickName = ca.getArt().getArtist().getUser().getNickname();
			this.artLikes = ca.getArt().getArtLikeCount();
			this.artHit = ca.getArt().getArtHit();
		}
	}
}