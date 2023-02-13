package com.ssafy.banana.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
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
@ApiModel("경매 정보 응답 DTO")
public class AuctionResponse {

	@ApiModelProperty(name = "작가 번호", example = "123")
	@JsonProperty(value = "userSeq")
	private Long artistSeq;

	@ApiModelProperty(name = "작가 닉네임", example = "닉네임")
	@JsonProperty(value = "nickname")
	private String artistNickname;

	@ApiModelProperty(name = "경매품 이미지 파일명", example = "eclipse.png")
	private String artImg;

	@ApiModelProperty(name = "경매품 이름", example = "월식이")
	private String artName;

	@ApiModelProperty(name = "경매품 설명", example = "VR 댕댕이")
	private String artDescription;

	@ApiModelProperty(name = "경매 시작가", example = "1000")
	private int auctionStartPrice;

	@ApiModelProperty(name = "경매 현재가", example = "1000")
	private int auctionCurrentPrice;

	@ApiModelProperty(name = "경매 입찰가", example = "1500")
	private int auctionBidPrice;

	@ApiModelProperty(name = "메시지", example = "[HOST] : 경매를 시작하겠습니다.")
	private String message;
}
