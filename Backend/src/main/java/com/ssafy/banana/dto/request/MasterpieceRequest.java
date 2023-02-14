package com.ssafy.banana.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Accessors(chain = true)
public class MasterpieceRequest {

	@ApiModelProperty(name = "작품 번호", example = "123")
	private Long artSeq;
	@ApiModelProperty(name = "대표 작품 여부", example = "true or false")
	private boolean isRepresent;
}
