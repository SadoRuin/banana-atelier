package com.ssafy.banana.api.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.banana.db.entity.Art;
import com.ssafy.banana.db.entity.Auction;
import com.ssafy.banana.db.entity.AuctionBidLog;
import com.ssafy.banana.db.entity.AuctionJoin;
import com.ssafy.banana.db.entity.AuctionJoinId;
import com.ssafy.banana.db.entity.Curation;
import com.ssafy.banana.db.entity.CurationArt;
import com.ssafy.banana.db.entity.User;
import com.ssafy.banana.db.repository.ArtRepository;
import com.ssafy.banana.db.repository.AuctionBidLogRepository;
import com.ssafy.banana.db.repository.AuctionJoinRepository;
import com.ssafy.banana.db.repository.AuctionRepository;
import com.ssafy.banana.db.repository.CurationArtRepository;
import com.ssafy.banana.db.repository.CurationRepository;
import com.ssafy.banana.db.repository.UserRepository;
import com.ssafy.banana.dto.response.AuctionResponse;
import com.ssafy.banana.exception.CustomException;
import com.ssafy.banana.exception.CustomExceptionType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuctionService {

	private final UserRepository userRepository;
	private final CurationArtRepository curationArtRepository;
	private final AuctionRepository auctionRepository;
	private final AuctionJoinRepository auctionJoinRepository;
	private final ArtRepository artRepository;
	private final CurationRepository curationRepository;
	private final AuctionBidLogRepository auctionBidLogRepository;

	public AuctionJoin joinAuction(Long curationArtSeq, Long userSeq) {

		User user = userRepository.findById(userSeq)
			.orElseThrow(() -> new CustomException(CustomExceptionType.RUNTIME_EXCEPTION));
		CurationArt curationArt = curationArtRepository.findById(curationArtSeq)
			.orElseThrow(() -> new CustomException(CustomExceptionType.RUNTIME_EXCEPTION));
		LocalDateTime auctionJoinTime = LocalDateTime.now();

		// 경매 참가 신청 추가
		AuctionJoinId auctionJoinId = AuctionJoinId.builder()
			.userSeq(user.getId())
			.curationArtSeq(curationArt.getId())
			.build();
		AuctionJoin auctionJoin = AuctionJoin.builder()
			.id(auctionJoinId)
			.user(user)
			.curationArt(curationArt)
			.auctionJoinTime(auctionJoinTime)
			.build();
		auctionJoinRepository.save(auctionJoin);

		// 큐레이션 등록 작품 경매 신청자 수 기록
		int auctionPeopleCount = auctionJoinRepository.countAuctionJoinPeople(curationArtSeq);
		curationArt.setAuctionPeopleCnt(auctionPeopleCount + "");
		curationArtRepository.save(curationArt);

		return auctionJoin;
	}

	public void createAuction(Long curationArtSeq) {

		CurationArt curationArt = curationArtRepository.findById(curationArtSeq)
			.orElseThrow(() -> new CustomException(CustomExceptionType.RUNTIME_EXCEPTION));

		Auction auction = Auction.builder()
			.id(curationArtSeq)
			.curationArt(curationArt)
			// .auctionStartPrice(curationArt.isAuction())
			// .auctionGap(curationArt.getAuctionGap())
			.build();
		auctionRepository.save(auction);
	}

	public List<AuctionResponse> getAuctionInfo(Long curationSeq) {

		List<AuctionResponse> auctionResponseList = new ArrayList<>();

		Curation curation = curationRepository.findById(curationSeq)
			.orElseThrow(() -> new CustomException(CustomExceptionType.RUNTIME_EXCEPTION));
		User artist = userRepository.findById(curation.getArtist().getId())
			.orElseThrow(() -> new CustomException(CustomExceptionType.RUNTIME_EXCEPTION));

		List<CurationArt> curationArtList = curationArtRepository.findByCuration_Id(curationSeq);
		for (int i = 0; i < curationArtList.size(); i++) {
			CurationArt curationArt = curationArtList.get(i);
			if (curationArt.getIsAuction() == 0) {
				continue;
			}
			// LocalDateTime auctionStartTime = LocalDateTime.now();

			Art art = artRepository.findById(curationArt.getId())
				.orElseThrow(() -> new CustomException(CustomExceptionType.RUNTIME_EXCEPTION));
			Auction auction = auctionRepository.findByCurationArt_Id(curationArt.getId());
			AuctionBidLog auctionBidLog = auctionBidLogRepository.findTopByAuction_IdOrderByIdDesc(auction.getId());

			auctionResponseList.add(
				AuctionResponse.builder()
					.artistNickname(artist.getNickname())
					.artImg(art.getArtImg())
					.artDescription(art.getArtDescription())
					.auctionStartPrice(auction.getAuctionStartPrice())
					.auctionBidPrice(auctionBidLog.getAuctionBidPrice())
					.auctionHost("[HOST] 경매를 시작하겠습니다.")
					.build()
			);
		}
		return auctionResponseList;
	}
}
