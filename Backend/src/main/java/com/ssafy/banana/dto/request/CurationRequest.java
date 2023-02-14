package com.ssafy.banana.dto.request;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CurationRequest implements Serializable {

	private LocalDateTime curationStartTime;
	private String curationName;
	private String curationSummary;
	private Long artistSeq;
	private List<CurationArtRequest> curationArtList;
}
