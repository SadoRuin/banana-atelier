package com.ssafy.banana.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.banana.api.service.AuctionService;
import com.ssafy.banana.db.entity.AuctionJoin;
import com.ssafy.banana.dto.response.AuctionResponse;
import com.ssafy.banana.dto.response.SuccessResponse;
import com.ssafy.banana.exception.CustomException;
import com.ssafy.banana.exception.CustomExceptionType;
import com.ssafy.banana.security.jwt.TokenProvider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@Api(tags = "경매관련 API")
@RequestMapping("/auctions")
@RequiredArgsConstructor
public class AuctionController {

	private static final String BLNAK = " ";
	private final TokenProvider tokenProvider;
	private final AuctionService auctionService;

	@ApiOperation(value = "경매 참가 신청", notes = "해당 큐레이션 작품에 대해 경매 참가 신청합니다")
	@PostMapping("/join/{curationArtSeq}")
	public ResponseEntity joinAuction(
		@PathVariable Long curationArtSeq,
		@RequestHeader String Authorization) {

		String token = Authorization.split(BLNAK)[1];
		Long userSeq = tokenProvider.getSubject(token);
		AuctionJoin auctionJoin = auctionService.joinAuction(curationArtSeq, userSeq);

		return ResponseEntity.status(HttpStatus.OK).body(auctionJoin);
	}

	@PreAuthorize("hasRole('ARTIST')")
	@ApiOperation(value = "경매 시작", notes = "해당 큐레이션 작품들에 대한 경매 정보를 모두 생성합니다")
	@PostMapping("/start/{curationSeq}")
	public ResponseEntity startAuction(@PathVariable Long curationSeq) {

		int artCount = auctionService.createAuction(curationSeq);
		if (artCount > 0) {
			return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse("경매가 시작됩니다."));
		} else {
			throw new CustomException(CustomExceptionType.UNABLE_AUCTION);
		}
	}

	@ApiOperation(value = "경매 정보", notes = "경매시 필요한 정보를 반환합니다")
	@GetMapping("/{curationSeq}")
	public ResponseEntity getAuctionInfo(
		@PathVariable Long curationSeq) {

		List<AuctionResponse> auctionInfoList = auctionService.getAuctionInfo(curationSeq);

		return ResponseEntity.status(HttpStatus.OK).body(auctionInfoList);
	}
}
