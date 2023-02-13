package com.ssafy.banana.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.banana.db.entity.enums.CurationStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Builder
@Getter
@Setter
public class CurationDataResponse {
	//큐레이션 리스트에서 필요한 내용만 간략하게 조회

	public static class CurationSimple {
		@JsonProperty
		private String userNickname;
		@JsonProperty
		private String curationName;
		@JsonProperty
		private String curationThumbnail;
		@JsonProperty
		private int curationBmCount;
		@JsonProperty
		private int curationHit;
		@JsonProperty
		private LocalDateTime curationStartTime;
		@JsonProperty
		private CurationStatus curationStatus;
		@JsonProperty
		private Long curationSeq;
		@JsonProperty
		private String profileImg;

		public CurationSimple(com.ssafy.banana.db.entity.Curation c) {
			this.userNickname = c.getArtist().getUser().getNickname();
			this.curationName = c.getCurationName();
			this.curationThumbnail = c.getCurationThumbnail();
			this.curationBmCount = c.getCurationBmCount();
			this.curationHit = c.getCurationHit();
			this.curationStartTime = c.getCurationStartTime();
			this.curationStatus = c.getCurationStatus();
			this.curationSeq = c.getId();
			this.profileImg = c.getArtist().getUser().getProfileImg();
		}

		public CurationSimple(com.ssafy.banana.db.entity.CurationBookmark cb) {
			this.userNickname = cb.getUser().getNickname();
			this.curationName = cb.getCuration().getCurationName();
			this.curationThumbnail = cb.getCuration().getCurationThumbnail();
			this.curationBmCount = cb.getCuration().getCurationBmCount();
			this.curationHit = cb.getCuration().getCurationHit();
			this.curationStartTime = cb.getCuration().getCurationStartTime();
			this.curationStatus = cb.getCuration().getCurationStatus();
			this.curationSeq = cb.getId().getCurationSeq();
			this.profileImg = cb.getUser().getProfileImg();
		}

		// public CurationSimple(com.ssafy.banana.db.entity.MyArtist ma) {
		// 	Long artistSeq = ma.getArtist().getId();
		// 	this.userNickname = ma
		// 	this.curationName = cb.getCuration().getCurationName();
		// 	this.curationThumbnail = cb.getCuration().getCurationThumbnail();
		// 	this.curationBmCount = cb.getCuration().getCurationBmCount();
		// 	this.curationHit = cb.getCuration().getCurationHit();
		// 	this.curationStartTime = cb.getCuration().getCurationStartTime();
		// 	this.curationStatus = cb.getCuration().getCurationStatus();
		// 	this.curationSeq = cb.getId().getCurationSeq();
		// 	this.profileImg = cb.getUser().getProfileImg();
		// }
	}

	//큐레이션 하나만 조회

	public static class Curation extends CurationSimple {
		@JsonProperty
		private String curation_summary;

		public Curation(com.ssafy.banana.db.entity.Curation c) {
			super(c);
			this.curation_summary = c.getCurationSummary();
		}

	}

}

