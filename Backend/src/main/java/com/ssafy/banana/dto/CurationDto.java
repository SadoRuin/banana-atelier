package com.ssafy.banana.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.ssafy.banana.db.entity.Artist;
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
public class CurationDto implements Serializable {
	private Long id;
	private LocalDateTime curationStartTime;
	private LocalDateTime curationEndTime;
	private String curationName;
	private String curationSummary;
	private String curationStatus;
	private Artist artist;
	private String curationHit;
	private int curationBmCount;
	private String curationThumbnail;


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
