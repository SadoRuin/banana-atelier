package com.ssafy.banana.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class AuctionRequest {

	@ApiModelProperty(name = "작가 번호", example = "123")
	@JsonProperty(value = "userSeq")
	private Long artistSeq;

	@ApiModelProperty(name = "경매품 번호", example = "123")
	private Long curationArtSeq;
}
