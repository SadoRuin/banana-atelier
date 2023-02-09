package com.ssafy.banana.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import com.ssafy.banana.db.entity.Artist;
import com.ssafy.banana.db.entity.CurationArt;
import com.ssafy.banana.db.entity.enums.CurationStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Data
public class CurationDataResponse {
	//큐레이션 리스트에서 필요한 내용만 간략하게 조회
	@Data
	@NoArgsConstructor
	public static class CurationSimple {
		private String userNickname;
		private String curationName;
		private String curationThumbnail;
		private int curationBmCount;
		private int curationHit;
		private LocalDateTime curationStartTime;

		private CurationStatus curationStatus;
		private Long curationSeq ;

		private String profileImg;


		public CurationSimple(com.ssafy.banana.db.entity.Curation c){
			this.userNickname = c.getArtist().getUser().getNickname();
			this.curationName = c.getCurationName();
			this.curationThumbnail = c.getCurationThumbnail();
			this.curationBmCount = c.getCurationBmCount();
			this.curationHit = c.getCurationHit();
			this.curationStartTime = c.getCurationStartTime();
			this.curationStatus = c.getCurationStatus();
			this.curationSeq  = c.getId();
			this.profileImg = c.getArtist().getUser().getProfileImg();
		}
	}
	//큐레이션 하나만 조회
	@Data
	public static class Curation extends CurationSimple{
		private String curation_summary;

		public Curation(com.ssafy.banana.db.entity.Curation c){
			super(c);
			this.curation_summary = c.getCurationSummary();
		}

	}

}

