package com.ssafy.banana.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.banana.api.service.NoticeService;
import com.ssafy.banana.db.entity.Notice;
import com.ssafy.banana.dto.request.NoticeRequest;
import com.ssafy.banana.exception.CustomException;
import com.ssafy.banana.exception.CustomExceptionType;
import com.ssafy.banana.security.jwt.TokenProvider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@Api(tags = "공지관련 API")
@RequestMapping("/notices")
@RequiredArgsConstructor
public class NoticeController {

	private static final String AUTHORIZATION = "Authorization";
	private final TokenProvider tokenProvider;
	private final NoticeService noticeService;

	@ApiOperation(value = "공지사항 업로드", notes = "나의 공지사항을 업로드합니다")
	@PostMapping
	public ResponseEntity uploadNotice(
		@RequestBody NoticeRequest noticeRequest,
		@RequestHeader(AUTHORIZATION) String token) {

		token = getToken(token);
		if (!tokenProvider.validateToken(token)) {
			throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
		}
		Long userSeq = tokenProvider.getSubject(token);
		Notice notice = noticeService.uploadNotice(noticeRequest, userSeq);

		return ResponseEntity.status(HttpStatus.OK).body(notice);
	}

	private static String getToken(String token) {
		if (token.substring(0, 7).equals("Bearer ")) {
			token = token.substring("Bearer ".length());
		}
		return token;
	}
}
