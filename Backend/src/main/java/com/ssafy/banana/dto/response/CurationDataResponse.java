package com.ssafy.banana.dto.response;

import java.time.LocalDateTime;


import com.ssafy.banana.db.entity.Artist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Data
public class CurationDataResponse {
	@Data
	@NoArgsConstructor
	public static class CurationSimple {
		private String userNickName;
		private String curationName;
		private String curationThumbnail;
		private int curationBmCount;
		private String curationHit;
		private LocalDateTime curationStartTime;


		public CurationSimple(com.ssafy.banana.db.entity.Curation c){
			this.userNickName = c.getArtist().getUser().getNickname();
			this.curationName = c.getCurationName();
			this.curationThumbnail = c.getCurationThumbnail();
			this.curationBmCount = c.getCurationBmCount();
			this.curationHit = c.getCurationHit();
			this.curationStartTime = c.getCurationStartTime();
		}
	}

	@Data
	public static class Curation extends CurationSimple{
		private String curationSummary;

		public Curation(com.ssafy.banana.db.entity.Curation c){
			super(c);
			this.curationSummary = c.getCurationSummary();
		}

	}

}

