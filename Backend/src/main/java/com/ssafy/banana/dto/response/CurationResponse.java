package com.ssafy.banana.dto.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.ssafy.banana.api.service.CurationService;
import com.ssafy.banana.db.entity.Art;
import com.ssafy.banana.db.entity.Artist;
import com.ssafy.banana.db.entity.Curation;

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
	private Artist artist;

	public CurationResponse(Curation curation, Artist artist){
		this.id = curation.getId();
		this.curationStartTime = curation.getCurationStartTime();
		this.curationEndTime = curation.getCurationEndTime();
		this.curationName = curation.getCurationName();
		this.curationSummary = curation.getCurationSummary();
		this.artist = Artist.builder()
			.id(artist.getUser().getId())
			.build();
	}
}

