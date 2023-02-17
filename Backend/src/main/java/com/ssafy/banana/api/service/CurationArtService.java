package com.ssafy.banana.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ssafy.banana.db.entity.Auction;
import com.ssafy.banana.db.entity.Curation;
import com.ssafy.banana.db.entity.CurationArt;
import com.ssafy.banana.db.entity.enums.AuctionStatus;
import com.ssafy.banana.db.repository.AuctionRepository;
import com.ssafy.banana.db.repository.CurationArtRepository;
import com.ssafy.banana.db.repository.CurationRepository;
import com.ssafy.banana.dto.response.CurationArtDataResponse;
import com.ssafy.banana.dto.response.CurationEndResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CurationArtService {
	private final CurationArtRepository curationArtRepository;
	private final CurationRepository curationRepository;
	private final AuctionRepository auctionRepository;

	public List<CurationArtDataResponse> getCurationArtList(long curationSeq) {
		Curation curation = curationRepository.findById(curationSeq).orElse(null);
		return curationArtRepository.findAllByCuration(curation).stream().map(CurationArtDataResponse::new).collect(
			Collectors.toList());
	}

	public CurationEndResponse getEndCurationArtList(long curationSeq) {
		List<CurationArt> curationArtList = curationArtRepository.findAllByCuration_Id(curationSeq);

		int artCnt = curationArtList.size();
		int soldCnt = 0;
		List<Long> soldList = new ArrayList<>();
		for (int i = 0; i < artCnt; i++) {
			long curationArtSeq = curationArtList.get(i).getId();
			Auction auction = auctionRepository.findById(curationArtSeq).orElse(null);
			System.out.println("옥션" + auction);
			if (AuctionStatus.SUCCESS == auction.getAuctionStatus()) {
				soldCnt++;
				soldList.add(curationArtSeq);
			}
		}

		return new CurationEndResponse(artCnt, soldCnt, soldList);
	}

}
