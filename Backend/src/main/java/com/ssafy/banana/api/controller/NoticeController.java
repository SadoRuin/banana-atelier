package com.ssafy.banana.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.banana.api.service.NoticeService;
import com.ssafy.banana.db.entity.Notice;
import com.ssafy.banana.dto.request.NoticeRequest;
import com.ssafy.banana.dto.response.NoticeResponse;
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

	@ApiOperation(value = "나의 공지사항 리스트", notes = "작가 본인의 공지사항 목록을 반환합니다")
	@GetMapping("/{user_seq}")
	public ResponseEntity<List> getMyNoticeList(@PathVariable("user_seq") Long userSeq,
		@RequestHeader(AUTHORIZATION) String token) {

		token = getToken(token);
		if (!tokenProvider.validateToken(token)) {
			throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
		}
		Long tokenUserSeq = tokenProvider.getSubject(token);
		List<NoticeResponse> myNoticeList = noticeService.getMyNoticeList(userSeq, tokenUserSeq);

		return ResponseEntity.status(HttpStatus.OK).body(myNoticeList);
	}

	@ApiOperation(value = "팔로잉 작가 공지사항 리스트", notes = "내가 팔로우한 작가들의 공지사항 목록을 반환합니다")
	@GetMapping("/{user_seq}/following")
	public ResponseEntity<List> getMyArtistsNoticeList(@PathVariable("user_seq") Long userSeq,
		@RequestHeader(AUTHORIZATION) String token) {

		token = getToken(token);
		if (!tokenProvider.validateToken(token)) {
			throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
		}
		Long tokenUserSeq = tokenProvider.getSubject(token);
		List<NoticeResponse> myArtistNoticeList = noticeService.getMyArtistsNoticeList(userSeq, tokenUserSeq);

		return ResponseEntity.status(HttpStatus.OK).body(myArtistNoticeList);
	}

	@ApiOperation(value = "공지사항 상세", notes = "공지사항 상세 정보를 반환합니다")
	@GetMapping("/detail/{notice_seq}")
	public ResponseEntity getNotice(@PathVariable("notice_seq") Long noticeSeq,
		@RequestHeader(value = AUTHORIZATION, required = false) String token) {

		token = getToken(token);
		if (!tokenProvider.validateToken(token)) {
			throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
		}
		NoticeResponse noticeResponse = noticeService.getNotice(noticeSeq);

		return ResponseEntity.status(HttpStatus.OK).body(noticeResponse);
	}

	@ApiOperation(value = "공지사항 수정", notes = "등록된 공지사항을 수정합니다")
	@PutMapping
	public ResponseEntity updateNotice(@RequestBody NoticeRequest noticeRequest,
		@RequestHeader(AUTHORIZATION) String token) {

		token = getToken(token);
		if (!tokenProvider.validateToken(token)) {
			throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
		}
		Long userSeq = tokenProvider.getSubject(token);
		Notice notice = noticeService.updateNotice(noticeRequest, userSeq);

		return ResponseEntity.status(HttpStatus.OK).body(notice);
	}

	@ApiOperation(value = "공지사항 삭제", notes = "등록된 공지사항을 삭제합니다")
	@DeleteMapping("/{notice_seq}")
	public ResponseEntity deleteNotice(@PathVariable("notice_seq") Long noticeSeq,
		@RequestHeader(AUTHORIZATION) String token) {

		token = getToken(token);
		if (!tokenProvider.validateToken(token)) {
			throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
		}
		Long userSeq = tokenProvider.getSubject(token);
		Long result = noticeService.deleteNotice(noticeSeq, userSeq);

		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	private static String getToken(String token) {
		if (token.substring(0, 7).equals("Bearer ")) {
			token = token.substring("Bearer ".length());
		}
		return token;
	}
}
