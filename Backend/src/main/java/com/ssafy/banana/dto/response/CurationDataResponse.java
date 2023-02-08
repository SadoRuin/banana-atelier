package com.ssafy.banana.dto.response;

import java.time.LocalDateTime;


import com.ssafy.banana.db.entity.Artist;
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
	@Data
	@NoArgsConstructor
	public static class CurationSimple {
		private String user_nickname;
		private String curation_name;
		private String curation_thumbnail;
		private int curation_bm_count;
		private String curation_hit;
		private LocalDateTime curation_start_time;

		private CurationStatus curation_status;


		public CurationSimple(com.ssafy.banana.db.entity.Curation c){
			this.user_nickname = c.getArtist().getUser().getNickname();
			this.curation_name = c.getCurationName();
			this.curation_thumbnail = c.getCurationThumbnail();
			this.curation_bm_count = c.getCurationBmCount();
			this.curation_hit = c.getCurationHit();
			this.curation_start_time = c.getCurationStartTime();
			this.curation_status = c.getCurationStatus();
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

