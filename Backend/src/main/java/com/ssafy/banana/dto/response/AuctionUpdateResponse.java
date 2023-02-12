package com.ssafy.banana.dto.response;

import io.swagger.annotations.ApiModel;
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
@ApiModel("경매 입찰 정보 업데이트 응답 DTO")
public class AuctionUpdateResponse {

	@ApiModelProperty(name = "경매 현재가", example = "1500")
	private int auctionCurrentPrice;

	@ApiModelProperty(name = "경매 입찰가", example = "2000")
	private int auctionBidPrice;

	@ApiModelProperty(name = "메시지", example = "[ %s ] 님 : 입찰가 %d원")
	private String message;
}
