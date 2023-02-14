package com.ssafy.banana.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.ssafy.banana.api.service.AuctionService;
import com.ssafy.banana.dto.request.AuctionRequest;
import com.ssafy.banana.dto.request.CountdownRequest;
import com.ssafy.banana.dto.request.SeqRequest;
import com.ssafy.banana.dto.response.AuctionResponse;
import com.ssafy.banana.dto.response.AuctionUpdateResponse;
import com.ssafy.banana.dto.response.ExceptionResponse;
import com.ssafy.banana.dto.response.SuccessResponse;
import com.ssafy.banana.security.jwt.TokenProvider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
	@ApiImplicitParams({
		@ApiImplicitParam(name = "seq", value = "큐레이션 작품 번호", required = true),
		@ApiImplicitParam(name = "Authorization", value = "token", required = true),
	})
	@PostMapping("/join")
	public ResponseEntity joinAuction(
		@RequestBody SeqRequest seqRequest,
		@RequestHeader String Authorization) {

		String token = Authorization.split(BLNAK)[1];
		Long userSeq = tokenProvider.getSubject(token);
		auctionService.joinAuction(seqRequest.getSeq(), userSeq);

		return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse("경매 참가 신청 완료"));
	}

	@PreAuthorize("hasRole('ARTIST')")
	@ApiOperation(value = "경매 시작", notes = "해당 큐레이션 작품들에 대한 경매 정보를 모두 생성합니다")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "seq", value = "큐레이션 번호", required = true),
		@ApiImplicitParam(name = "Authorization", value = "token", required = true),
	})
	@PostMapping("/start")
	public ResponseEntity startAuction(
		@RequestBody SeqRequest seqRequest,
		@RequestHeader String Authorization) {

		String token = Authorization.split(BLNAK)[1];
		Long userSeq = tokenProvider.getSubject(token);
		auctionService.createAuction(seqRequest.getSeq(), userSeq);

		return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse("경매가 시작됩니다."));
	}

	@ApiOperation(value = "경매 정보", notes = "경매시 필요한 정보를 반환합니다")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "seq", value = "큐레이션 번호", required = true),
		@ApiImplicitParam(name = "Authorization", value = "token", required = true),
	})
	@PostMapping
	public ResponseEntity getAuctionInfo(
		@RequestBody SeqRequest seqRequest) {

		AuctionResponse auctionResponse = auctionService.getAuctionInfo(seqRequest.getSeq());

		return ResponseEntity.status(HttpStatus.OK).body(auctionResponse);
	}

	@ApiOperation(value = "경매 입찰", notes = "경매시 입찰 정보를 업데이트합니다")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userSeq", value = "작가 번호", required = true),
		@ApiImplicitParam(name = "curationArtSeq", value = "경매품 번호", required = true),
		@ApiImplicitParam(name = "Authorization", value = "token", required = true)
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

	@ApiOperation(value = "현재 경매 종료", notes = "경매 시간이 남지 않으면 현재 경매를 종료합니다")
	@ApiImplicitParam(name = "seq", value = "큐레이션 작품 번호", required = true)
	@PutMapping("/close-one")
	public ResponseEntity closeOneAuction(
		@RequestBody SeqRequest seqRequest) {

		auctionService.closeOneAuction(seqRequest.getSeq());

		return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse("현재 작품 경매 종료"));
	}

	@PreAuthorize("hasRole('ARTIST')")
	@ApiOperation(value = "모든 경매 종료", notes = "하나의 큐레이션에 포함된 경매가 모두 종료합니다")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "seq", value = "큐레이션 번호", required = true),
		@ApiImplicitParam(name = "Authorization", value = "token", required = true),
	})
	@PutMapping("/close-all")
	public ResponseEntity closeAllAuction(
		@RequestBody SeqRequest seqRequest,
		@RequestHeader String Authorization) {

		String token = Authorization.split(BLNAK)[1];
		Long userSeq = tokenProvider.getSubject(token);
		auctionService.closeAllAuction(seqRequest.getSeq(), userSeq);

		return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse("경매가 모두 종료되었습니다."));
	}

	@ApiOperation(value = "경매 호스트 접속", notes = "경매 자동호스트에 접속")
	@ApiImplicitParam(name = "curationArtSeq", value = "경매품 번호", required = true)
	@ApiResponses({
		@ApiResponse(code = 200, message = "경매호스트 접속 성공", response = List.class),
		@ApiResponse(code = 400, message = "경매호스트 접속 오류.", response = ExceptionResponse.class)
	})
	@GetMapping(value = "/connect/{curationArtSeq}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public ResponseEntity<SseEmitter> connectAuctionHost(
		@PathVariable long curationArtSeq) {

		return ResponseEntity.ok(auctionService.connectAuctionHost(curationArtSeq));
	}

	@PreAuthorize("hasRole('ARTIST')")
	@ApiOperation(value = "경매 호스트 접속 일괄 종료", notes = "경매 자동호스트 접속 종료")
	@ApiImplicitParam(name = "curationArtSeq", value = "경매품 번호", required = true)
	@ApiResponses({
		@ApiResponse(code = 200, message = "경매호스트 일괄 종료 성공", response = List.class),
		@ApiResponse(code = 404, message = "진행중인 경매호스트가 없음.", response = ExceptionResponse.class)
	})
	@GetMapping(value = "/close-all/{curationArtSeq}")
	public ResponseEntity<SuccessResponse> closeAllAuctionHost(
		@PathVariable long curationArtSeq) {
		auctionService.closeAllAuctionHost(curationArtSeq);
		return ResponseEntity.ok(new SuccessResponse("경매 호스트가 종료되었습니다."));
	}

	@PreAuthorize("hasRole('ARTIST')")
	@ApiOperation(value = "경매 호스트 카운트다운", notes = "경매 자동호스트에 카운트 다운")
	@ApiImplicitParam(name = "curationArtSeq", value = "경매품 번호", required = true)
	@PostMapping("/countdown")
	public ResponseEntity<SuccessResponse> countAuction(
		@RequestBody CountdownRequest countdownRequest) {
		auctionService.countdownAuctionHost(countdownRequest);
		return ResponseEntity.ok(new SuccessResponse("카운트다운 성공"));
	}
}
