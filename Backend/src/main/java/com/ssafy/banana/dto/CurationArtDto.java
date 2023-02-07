package com.ssafy.banana.dto;

import java.io.Serializable;

import com.ssafy.banana.db.entity.Art;
import com.ssafy.banana.db.entity.Curation;

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
public class CurationArtDto implements Serializable {
	private Long id;
	private boolean isAuction;
	private String auctionPeopleCnt;
	private Art art;
	private Curation curation;

}
