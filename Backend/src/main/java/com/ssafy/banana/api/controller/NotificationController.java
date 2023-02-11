package com.ssafy.banana.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.banana.dto.response.ExceptionResponse;
import com.ssafy.banana.dto.response.SuccessResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@Api(tags = "알림관련 API")
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {
	@GetMapping
	@ApiOperation(value = "전체 공지사항 조회")
	@ApiResponses({
		@ApiResponse(code = 200, message = "공지사항 조회 성공", response = SuccessResponse.class),
		@ApiResponse(code = 400, message = "이메일 발송 오류 발생", response = ExceptionResponse.class),
		@ApiResponse(code = 404, message = "회원 정보가 없습니다.", response = ExceptionResponse.class)
	})
	public ResponseEntity getNotifications() {

		return ResponseEntity.ok(new SuccessResponse(""));
	}

	@PostMapping("/notice")
	@ApiOperation(value = "공지사항 알림 등록")
	@ApiImplicitParam(name = "email", value = "유저 이메일", required = true)
	@ApiResponses({
		@ApiResponse(code = 200, message = "이메일로 임시비밀번호 발송", response = SuccessResponse.class),
		@ApiResponse(code = 400, message = "이메일 발송 오류 발생", response = ExceptionResponse.class),
		@ApiResponse(code = 404, message = "회원 정보가 없습니다.", response = ExceptionResponse.class)
	})
	public ResponseEntity createNoticeNotification() {

		return ResponseEntity.ok(new SuccessResponse(""));
	}

	@GetMapping("/notice")
	@ApiOperation(value = "공지사항 알림 조회")
	@ApiImplicitParam(name = "email", value = "유저 이메일", required = true)
	@ApiResponses({
		@ApiResponse(code = 200, message = "이메일로 임시비밀번호 발송", response = SuccessResponse.class),
		@ApiResponse(code = 400, message = "이메일 발송 오류 발생", response = ExceptionResponse.class),
		@ApiResponse(code = 404, message = "회원 정보가 없습니다.", response = ExceptionResponse.class)
	})
	public ResponseEntity getNoticeNotification() {

		return ResponseEntity.ok(new SuccessResponse(""));
	}

}
