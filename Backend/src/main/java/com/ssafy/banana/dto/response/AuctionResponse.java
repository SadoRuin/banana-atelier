package com.ssafy.banana.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class AuctionResponse {
	
	@JsonProperty(value = "userSeq")
	private Long artistSeq;
	@JsonProperty(value = "nickname")
	private String artistNickname;
	private String artImg;
	private String artName;
	private String artDescription;
	private int auctionStartPrice;
	private int auctionBidPrice;
	private String auctionHost;
}
