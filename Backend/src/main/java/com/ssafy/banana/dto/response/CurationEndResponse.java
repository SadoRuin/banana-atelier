package com.ssafy.banana.dto.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Builder
public class CurationEndResponse {
	//큐레이션 아트에서 팔린거랑 안팔린거조회
	@JsonProperty
	private int allArtCnt;
	@JsonProperty
	private int endArtCnt;
	@JsonProperty
	private List<Long> soldArtList;

	public CurationEndResponse(int allArtCnt, int endArtCnt,
		List<Long> soldArtList) {
		this.allArtCnt = allArtCnt;
		this.endArtCnt = endArtCnt;
		this.soldArtList = soldArtList;
	}
}
