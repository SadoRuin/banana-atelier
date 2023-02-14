package com.ssafy.banana.dto.request;

import io.swagger.annotations.ApiModelProperty;
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
public class CountdownRequest {
	@ApiModelProperty(name = "큐레이션 작품 번호", example = "123")
	long curationArtSeq;
	@ApiModelProperty(name = "경매 타이머 카운트", example = "4")
	int second;
}
