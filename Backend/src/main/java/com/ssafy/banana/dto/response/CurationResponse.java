package com.ssafy.banana.dto.response;

import java.time.LocalDateTime;


import com.ssafy.banana.db.entity.Artist;
import com.ssafy.banana.db.entity.Curation;
import com.ssafy.banana.db.entity.enums.CurationStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CurationResponse {
	private Long id;
	private LocalDateTime curationStartTime;
	private LocalDateTime curationEndTime;
	private String curationName;
	private String curationSummary;
	private CurationStatus curationStatus;
	private String user_nickName;

	public CurationResponse(Curation curation, Artist artist){
		this.id = curation.getId();
		this.curationStartTime = curation.getCurationStartTime();
		this.curationEndTime = curation.getCurationEndTime();
		this.curationName = curation.getCurationName();
		this.curationSummary = curation.getCurationSummary();
		this.curationStatus = curation.getCurationStatus();
		this.user_nickName = artist.getUser().getNickname();
	}
}

