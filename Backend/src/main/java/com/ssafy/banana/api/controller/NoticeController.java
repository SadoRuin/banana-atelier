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
import com.ssafy.banana.dto.response.SuccessResponse;
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

	private static final String BLNAK = " ";
	private final TokenProvider tokenProvider;
	private final NoticeService noticeService;

	@ApiOperation(value = "공지사항 업로드", notes = "나의 공지사항을 업로드합니다")
	@PostMapping
	public ResponseEntity uploadNotice(
		@RequestBody NoticeRequest noticeRequest,
		@RequestHeader String Authorization) {

		String token = Authorization.split(BLNAK)[1];
		Long userSeq = tokenProvider.getSubject(token);
		noticeService.uploadNotice(noticeRequest, userSeq);

		return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse("공지사항이 등록되었습니다."));
	}

	@ApiOperation(value = "나의 공지사항 리스트", notes = "작가 본인의 공지사항 목록을 반환합니다")
	@GetMapping
	public ResponseEntity<List> getMyNoticeList(@RequestHeader String Authorization) {

		String token = Authorization.split(BLNAK)[1];
		Long userSeq = tokenProvider.getSubject(token);
		List<NoticeResponse> myNoticeList = noticeService.getMyNoticeList(userSeq);

		return ResponseEntity.status(HttpStatus.OK).body(myNoticeList);
	}

	@ApiOperation(value = "팔로잉 작가 공지사항 리스트", notes = "내가 팔로우한 작가들의 공지사항 목록을 반환합니다")
	@GetMapping("/following")
	public ResponseEntity<List> getMyArtistsNoticeList(@RequestHeader String Authorization) {

		String token = Authorization.split(BLNAK)[1];
		Long userSeq = tokenProvider.getSubject(token);
		List<NoticeResponse> myArtistNoticeList = noticeService.getMyArtistsNoticeList(userSeq);

		return ResponseEntity.status(HttpStatus.OK).body(myArtistNoticeList);
	}

	@ApiOperation(value = "공지사항 상세", notes = "공지사항 상세 정보를 반환합니다")
	@GetMapping("/detail/{notice_seq}")
	public ResponseEntity getNotice(@PathVariable("notice_seq") Long noticeSeq) {

		NoticeResponse noticeResponse = noticeService.getNotice(noticeSeq);

		return ResponseEntity.status(HttpStatus.OK).body(noticeResponse);
	}

	@ApiOperation(value = "공지사항 수정", notes = "등록된 공지사항을 수정합니다")
	@PutMapping
	public ResponseEntity updateNotice(@RequestBody NoticeRequest noticeRequest,
		@RequestHeader String Authorization) {

		String token = Authorization.split(BLNAK)[1];
		Long userSeq = tokenProvider.getSubject(token);
		Notice notice = noticeService.updateNotice(noticeRequest, userSeq);

		return ResponseEntity.status(HttpStatus.OK).body(notice);
	}

	@ApiOperation(value = "공지사항 삭제", notes = "등록된 공지사항을 삭제합니다")
	@DeleteMapping("/{notice_seq}")
	public ResponseEntity deleteNotice(@PathVariable("notice_seq") Long noticeSeq,
		@RequestHeader String Authorization) {

		String token = Authorization.split(BLNAK)[1];
		Long userSeq = tokenProvider.getSubject(token);
		noticeService.deleteNotice(noticeSeq, userSeq);

		return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse("공지사항이 삭제되었습니다."));
	}
}
