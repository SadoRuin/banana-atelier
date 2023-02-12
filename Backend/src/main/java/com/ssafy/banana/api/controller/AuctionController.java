package com.ssafy.banana.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.banana.api.service.AuctionService;
import com.ssafy.banana.db.entity.AuctionJoin;
import com.ssafy.banana.dto.request.AuctionRequest;
import com.ssafy.banana.dto.response.AuctionResponse;
import com.ssafy.banana.dto.response.AuctionUpdateResponse;
import com.ssafy.banana.dto.response.SuccessResponse;
import com.ssafy.banana.security.jwt.TokenProvider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
	@ApiImplicitParam(name = "curationArtSeq", value = "큐레이션 작품 번호", required = true)
	@PostMapping("/join/{curationArtSeq}")
	public ResponseEntity joinAuction(
		@PathVariable Long curationArtSeq,
		@RequestHeader String Authorization) {

		String token = Authorization.split(BLNAK)[1];
		Long userSeq = tokenProvider.getSubject(token);
		AuctionJoin auctionJoin = auctionService.joinAuction(curationArtSeq, userSeq);

		return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse("경매 참가 신청 완료"));
	}

	@PreAuthorize("hasRole('ARTIST')")
	@ApiOperation(value = "경매 시작", notes = "해당 큐레이션 작품들에 대한 경매 정보를 모두 생성합니다")
	@ApiImplicitParam(name = "curationSeq", value = "큐레이션 번호", required = true)
	@PostMapping("/start/{curationSeq}")
	public ResponseEntity startAuction(
		@PathVariable Long curationSeq,
		@RequestHeader String Authorization) {

		String token = Authorization.split(BLNAK)[1];
		Long userSeq = tokenProvider.getSubject(token);
		auctionService.createAuction(curationSeq, userSeq);

		return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse("경매가 시작됩니다."));
	}

	@ApiOperation(value = "경매 정보", notes = "경매시 필요한 정보를 반환합니다")
	@ApiImplicitParam(name = "curationSeq", value = "큐레이션 번호", required = true)
	@GetMapping("/{curationSeq}")
	public ResponseEntity getAuctionInfo(
		@PathVariable Long curationSeq,
		@RequestHeader String Authorization) {

		String token = Authorization.split(BLNAK)[1];
		Long userSeq = tokenProvider.getSubject(token);
		AuctionResponse auctionResponse = auctionService.getAuctionInfo(curationSeq, userSeq);

		return ResponseEntity.status(HttpStatus.OK).body(auctionResponse);
	}

	@ApiOperation(value = "경매 입찰", notes = "경매시 입찰 정보를 업데이트합니다")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userSeq", value = "작가 번호", required = true),
		@ApiImplicitParam(name = "curationArtSeq", value = "경매품 번호", required = true),
	})
	@PostMapping("/bid")
	public ResponseEntity updateAuction(
		@RequestBody AuctionRequest auctionRequest,
		@RequestHeader String Authorization) {

		String token = Authorization.split(BLNAK)[1];
		Long userSeq = tokenProvider.getSubject(token);
		AuctionUpdateResponse auctionUpdateResponse = auctionService.updateAuction(auctionRequest, userSeq);

		return ResponseEntity.status(HttpStatus.OK).body(auctionUpdateResponse);
	}

	@PreAuthorize("hasRole('ARTIST')")
	@ApiOperation(value = "모든 경매 종료", notes = "경매를 모두 종료합니다")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userSeq", value = "작가 번호", required = true),
		@ApiImplicitParam(name = "curationArtSeq", value = "경매품 번호", required = true),
	})
	@DeleteMapping("/closeAll/{curationSeq}")
	public ResponseEntity closeAllAuction(
		@PathVariable Long curationSeq,
		@RequestHeader String Authorization) {

		String token = Authorization.split(BLNAK)[1];
		Long userSeq = tokenProvider.getSubject(token);
		auctionService.closeAllAuction(curationSeq, userSeq);

		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

}
