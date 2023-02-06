package com.ssafy.banana.dto.response;

import java.time.LocalDateTime;
import java.util.List;


import com.ssafy.banana.db.entity.CurationArt;
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
public class CurationDetailResponse {
	private Long id;
	private LocalDateTime curationStartTime;
	private LocalDateTime curationEndTime;
	private String curationName;
	private String curationSummary;
	private CurationStatus curationStatus;
	private String user_nickName;
	private List<CurationArt> curationArtList;


	public CurationDetailResponse(CurationResponse curationResponse, List<CurationArt> curationArtList){
		this.id = curationResponse.getId();
		this.curationStartTime = curationResponse.getCurationStartTime();
		this.curationEndTime = curationResponse.getCurationEndTime();
		this.curationName = curationResponse.getCurationName();
		this.curationSummary = curationResponse.getCurationSummary();
		this.curationStatus = curationResponse.getCurationStatus();
		this.user_nickName = curationResponse.getUser_nickName();
		this.curationArtList = curationArtList;
	}
}
