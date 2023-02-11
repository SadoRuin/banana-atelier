package com.ssafy.banana.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CurationArtRequest {
	private int isAuction;
	private int auctionGap;
	private long artSeq;
}
