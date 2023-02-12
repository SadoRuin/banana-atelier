package com.ssafy.banana.api.service;

import static java.time.LocalDateTime.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.banana.db.entity.Art;
import com.ssafy.banana.db.entity.Auction;
import com.ssafy.banana.db.entity.AuctionBidLog;
import com.ssafy.banana.db.entity.AuctionJoin;
import com.ssafy.banana.db.entity.AuctionJoinId;
import com.ssafy.banana.db.entity.Curation;
import com.ssafy.banana.db.entity.CurationArt;
import com.ssafy.banana.db.entity.User;
import com.ssafy.banana.db.entity.enums.AuctionStatus;
import com.ssafy.banana.db.repository.ArtRepository;
import com.ssafy.banana.db.repository.AuctionBidLogRepository;
import com.ssafy.banana.db.repository.AuctionJoinRepository;
import com.ssafy.banana.db.repository.AuctionRepository;
import com.ssafy.banana.db.repository.CurationArtRepository;
import com.ssafy.banana.db.repository.CurationRepository;
import com.ssafy.banana.db.repository.UserRepository;
import com.ssafy.banana.dto.request.AuctionRequest;
import com.ssafy.banana.dto.response.AuctionResponse;
import com.ssafy.banana.dto.response.AuctionUpdateResponse;
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

	/**
	 * 경매 참여
	 * @param curationArtSeq 경매 작품 pk
	 * @param userSeq 경매 참여 신청 유저 pk
	 * @return
	 */
	@Transactional
	public AuctionJoin joinAuction(Long curationArtSeq, Long userSeq) {

		User user = userRepository.findById(userSeq)
			.orElseThrow(() -> new CustomException(CustomExceptionType.RUNTIME_EXCEPTION));
		CurationArt curationArt = curationArtRepository.findById(curationArtSeq)
			.orElseThrow(() -> new CustomException(CustomExceptionType.RUNTIME_EXCEPTION));

		// 작가 본인 작품, 또는 작가가 경매를 원하지 않는 작품은 경매 참여 불가
		if (curationArt.getCuration().getArtist().getId() == userSeq
			|| curationArt.getIsAuction() == 0) {
			throw new CustomException(CustomExceptionType.AUCTION_FAIL);
		}
		// 경매 참가 신청 추가
		AuctionJoinId auctionJoinId = AuctionJoinId.builder()
			.userSeq(user.getId())
			.curationArtSeq(curationArt.getId())
			.build();
		if (auctionJoinRepository.findById(auctionJoinId).isPresent()) {
			throw new CustomException(CustomExceptionType.AUCTION_JOIN_CONFLICT);
		}
		AuctionJoin auctionJoin = AuctionJoin.builder()
			.id(auctionJoinId)
			.user(user)
			.curationArt(curationArt)
			.auctionJoinTime(now())
			.build();
		auctionJoinRepository.save(auctionJoin);

		// 큐레이션 등록 작품 - 경매 신청자 수 기록
		int auctionPeopleCount = auctionJoinRepository.countAuctionJoinPeople(curationArtSeq);
		curationArt.setAuctionPeopleCnt(auctionPeopleCount);
		curationArtRepository.save(curationArt);

		return auctionJoin;
	}

	@Transactional
	public int createAuction(Long curationSeq) {

		Curation curation = curationRepository.findById(curationSeq)
			.orElseThrow(() -> new CustomException(CustomExceptionType.RUNTIME_EXCEPTION));
		List<CurationArt> curationArtList = curationArtRepository.findByCuration_Id(curation.getId());
		Auction auction = null;

		int artCount = 0;
		for (int i = 0; i < curationArtList.size(); i++) {
			CurationArt curationArt = curationArtList.get(i);
			if (curationArt.getIsAuction() == 0) {
				continue;
			}
			auction = auctionRepository.findById(curationArt.getId()).orElse(null);

			if (auction == null) {
				auction = Auction.builder()
					.id(curationArt.getId())
					.curationArt(curationArt)
					.auctionStartPrice(curationArt.getIsAuction())
					.auctionGap(500)
					.auctionEndPrice(curationArt.getIsAuction())
					.auctionStatus(AuctionStatus.INIT)
					.user(curation.getArtist().getUser())
					.build();
				auctionRepository.save(auction);
				artCount++;
			}
		}
		return artCount;
	}

	@Transactional
	public AuctionResponse getAuctionInfo(Long curationSeq, Long userSeq) {

		Curation curation = curationRepository.findById(curationSeq)
			.orElseThrow(() -> new CustomException(CustomExceptionType.RUNTIME_EXCEPTION));
		User artist = userRepository.findById(curation.getArtist().getId())
			.orElseThrow(() -> new CustomException(CustomExceptionType.RUNTIME_EXCEPTION));

		List<CurationArt> curationArtList = curationArtRepository.findByCuration_Id(curationSeq);

		for (int i = 0; i < curationArtList.size(); i++) {
			CurationArt curationArt = curationArtList.get(i);
			Auction auction = auctionRepository.findByCurationArt_IdOrderByCurationArt_Id(curationArt.getId());

			// 작가가 경매를 원하지 않거나 경매시작 이후의 상태라면 넘김
			if (curationArt.getIsAuction() == 0 || auction.getAuctionStatus() != AuctionStatus.INIT) {
				continue;
			}
			Art art = artRepository.findById(curationArt.getArt().getId())
				.orElseThrow(() -> new CustomException(CustomExceptionType.FILE_EXTENSION_ERROR));

			AuctionResponse auctionResponse = AuctionResponse.builder()
				.artistSeq(artist.getId())
				.artistNickname(artist.getNickname())
				.artImg(art.getArtImg())
				.artName(art.getArtName())
				.artDescription(art.getArtDescription())
				.auctionStartPrice(auction.getAuctionStartPrice())
				.auctionCurrentPrice(auction.getAuctionStartPrice())
				.auctionBidPrice(auction.getAuctionStartPrice() + curationArt.getAuctionGap())
				.message("[HOST] 경매를 시작하겠습니다.")
				.build();

	/**
	 * 경매 입찰 정보 업데이트
	 * @param auctionRequest
	 * @param userSeq
	 * @return
	 */
	@Transactional
	public AuctionUpdateResponse updateAuction(AuctionRequest auctionRequest, Long userSeq) {

		// 작가는 본인의 경매 참여 불가
		if (auctionRequest.getArtistSeq() == userSeq) {
			throw new CustomException(CustomExceptionType.AUCTION_FAIL);
		}
		// 현재 경매
		Auction auction = auctionRepository.findById(auctionRequest.getCurationArtSeq())
			.orElseThrow(() -> new CustomException(CustomExceptionType.RUNTIME_EXCEPTION));
		// 입찰자
		User user = userRepository.findById(userSeq)
			.orElseThrow(() -> new CustomException(CustomExceptionType.RUNTIME_EXCEPTION));

		// 최근 입찰
		AuctionBidLog auctionBidLog = auctionBidLogRepository.findTopByAuction_IdOrderByIdDesc(
			auctionRequest.getCurationArtSeq());

		// 입찰 로그 기록
		auctionBidLog = AuctionBidLog.builder()
			.auctionBidPrice(auctionBidLog.getAuctionBidPrice() + auction.getAuctionGap())
			.auctionBidTime(now())
			.user(user)
			.auction(auction)
			.build();
		auctionBidLogRepository.save(auctionBidLog);

		int currentPrice = auctionBidLog.getAuctionBidPrice();
		AuctionUpdateResponse auctionUpdateResponse = AuctionUpdateResponse.builder()
			.auctionCurrentPrice(currentPrice)
			.auctionBidPrice(currentPrice + auction.getAuctionGap())
			.message(String.format("[ %s ] 님의 입찰가 %d원", user.getNickname(), currentPrice))
			.build();
		return auctionUpdateResponse;

	}
}
