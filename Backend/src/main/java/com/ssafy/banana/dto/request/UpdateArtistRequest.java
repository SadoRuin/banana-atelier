package com.ssafy.banana.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A DTO for the {@link com.ssafy.banana.db.entity.Artist} entity
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ApiModel("작가정보수정 요청 DTO")
public class UpdateArtistRequest {
	@ApiModelProperty(name = "인스타그램 링크")
	private String instagramLink;
	@ApiModelProperty(name = "트위터 링크")
	private String twitterLink;
	@ApiModelProperty(name = "유튜브 링크")
	private String youtubeLink;
	@ApiModelProperty(name = "블로그 링크")
	private String blogLink;
	@ApiModelProperty(name = "작가 소개")
	private String artistIntro;
}