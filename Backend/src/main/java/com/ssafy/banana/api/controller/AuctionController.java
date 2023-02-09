package com.ssafy.banana.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.banana.api.service.AuctionService;
import com.ssafy.banana.db.entity.AuctionJoin;
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

	private static final String AUTHORIZATION = "Authorization";
	private final TokenProvider tokenProvider;
	private final AuctionService auctionService;

	@ApiOperation(value = "경매 참가 신청", notes = "해당 큐레이션 작품에 대해 경매 참가 신청합니다")
	@PostMapping("/{curationArtSeq}")
	public ResponseEntity joinAuction(
		@PathVariable Long curationArtSeq,
		@RequestHeader(AUTHORIZATION) String token) {

		token = getToken(token);
		if (!tokenProvider.validateToken(token)) {
			throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
		}
		Long userSeq = tokenProvider.getSubject(token);
		AuctionJoin auctionJoin = auctionService.joinAuction(curationArtSeq, userSeq);

		return ResponseEntity.status(HttpStatus.OK).body(auctionJoin);
	}

	private static String getToken(String token) {
		if (token.substring(0, 7).equals("Bearer ")) {
			token = token.substring("Bearer ".length());
		}
		return token;
	}
}
