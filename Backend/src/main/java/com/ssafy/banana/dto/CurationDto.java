package com.ssafy.banana.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.ssafy.banana.db.entity.Artist;

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
public class CurationDto implements Serializable {
	private Long id;
	private LocalDateTime curationStartTime;
	private LocalDateTime curationEndTime;
	private String curationName;
	private String curationSummary;
	private String curation_status;
	private Artist artist;

	// public static CurationDto from(Curation curation){
	// 	if(curation == null)
	// 		return null;
	// 	return CurationDto.builder()
	// 		.id(curation.getId())
	// 		.curationStartTime(curation.getCurationStartTime())
	// 		.curationEndTime(curation.getCurationEndTime())
	// 		.curationName(curation.getCurationName())
	// 		.curationSummary(curation.getCurationSummary())
	// 		.artist(curation.getArtist())
	// 		.build();
	// }
}
