package com.ssafy.banana.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Accessors(chain = true)
public class ArtRequest {
	@ApiModelProperty(name = "작품 번호", example = "123")
	private Long artSeq;
	@ApiModelProperty(name = "작품 제목", example = "제목", required = true)
	private String artName;
	@ApiModelProperty(name = "작품 설명", example = "설명입니다", required = true)
	private String artDescription;
	@ApiModelProperty(name = "작품 카테고리", example = "일러스트레이션", required = true)
	private Long artCategorySeq;
}
