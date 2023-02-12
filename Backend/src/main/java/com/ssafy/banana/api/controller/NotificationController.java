package com.ssafy.banana.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.banana.api.service.NotificationService;
import com.ssafy.banana.dto.NotificationDto;
import com.ssafy.banana.dto.request.NotificationReadRequest;
import com.ssafy.banana.dto.request.NotificationRequest;
import com.ssafy.banana.dto.response.ExceptionResponse;
import com.ssafy.banana.dto.response.SuccessResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@Api(tags = "알림관련 API")
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

	private final NotificationService notificationService;

	@GetMapping
	@ApiOperation(value = "전체 알림 조회")
	@ApiResponse(code = 200, message = "알림 조회 성공", response = List.class)
	public ResponseEntity<List<NotificationDto>> getAllNotification(@RequestHeader String Authorization) {
		String token = Authorization.split(" ")[1];
		return ResponseEntity.ok(notificationService.getAllNotification(token));
	}

	@PostMapping("/notice")
	@ApiOperation(value = "공지사항 알림 등록")
	@ApiImplicitParam(name = "seq", value = "공지사항 번호", required = true)
	@ApiResponses({
		@ApiResponse(code = 200, message = "공지사항 알림 등록 성공", response = SuccessResponse.class),
		@ApiResponse(code = 404, message = "작가정보 없음, 공지사항 정보 없음, 팔로워 없음", response = ExceptionResponse.class)
	})
	public ResponseEntity<SuccessResponse> createNoticeNotification(@RequestHeader String Authorization,
		@RequestBody NotificationRequest notificationRequest) {
		String token = Authorization.split(" ")[1];
		notificationService.createNoticeNotification(token, notificationRequest);
		return ResponseEntity.ok(new SuccessResponse("공지사항 알림이 등록되었습니다."));
	}

	@GetMapping("/notice")
	@ApiOperation(value = "공지사항 알림 조회")
	@ApiResponses({
		@ApiResponse(code = 200, message = "공지사항 알림 조회 성공", response = List.class),
		@ApiResponse(code = 404, message = "회원정보 없음, 공지사항 알림 없음", response = ExceptionResponse.class)
	})
	public ResponseEntity<List<NotificationDto>> getNoticeNotifications(@RequestHeader String Authorization) {
		String token = Authorization.split(" ")[1];
		return ResponseEntity.ok(notificationService.getNoticeNotifications(token));
	}

	@PostMapping("/curation")
	@ApiOperation(value = "큐레이션 알림 등록")
	@ApiImplicitParam(name = "seq", value = "큐레이션 번호", required = true)
	@ApiResponses({
		@ApiResponse(code = 200, message = "큐레이션 알림 등록 성공", response = SuccessResponse.class),
		@ApiResponse(code = 404, message = "작가정보 없음, 큐레이션 정보 없음, 팔로워 없음", response = ExceptionResponse.class)
	})
	public ResponseEntity<SuccessResponse> createCurationNotification(@RequestHeader String Authorization,
		@RequestBody NotificationRequest notificationRequest) {
		String token = Authorization.split(" ")[1];
		notificationService.createCurationNotification(token, notificationRequest);
		return ResponseEntity.ok(new SuccessResponse("큐레이션 알림이 등록되었습니다."));
	}

	@GetMapping("/curation")
	@ApiOperation(value = "큐레이션 알림 조회")
	@ApiResponses({
		@ApiResponse(code = 200, message = "큐레이션 알림 조회 성공", response = List.class),
		@ApiResponse(code = 404, message = "회원정보 없음, 큐레이션 알림 없음", response = ExceptionResponse.class)
	})
	public ResponseEntity<List<NotificationDto>> getCurationNotifications(@RequestHeader String Authorization) {
		String token = Authorization.split(" ")[1];
		return ResponseEntity.ok(notificationService.getCurationNotifications(token));
	}

	@PatchMapping("/read")
	@ApiOperation(value = "알림 읽음")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "notificationSeq", value = "알림 번호", required = true),
		@ApiImplicitParam(name = "notificationType", value = "알림 타입", readOnly = true)
	})
	@ApiResponses({
		@ApiResponse(code = 200, message = "읽음 처리 성공", response = SuccessResponse.class),
		@ApiResponse(code = 403, message = "권한 없음", response = ExceptionResponse.class),
		@ApiResponse(code = 404, message = "알림 없음", response = ExceptionResponse.class)
	})
	public ResponseEntity<SuccessResponse> readNotification(@RequestHeader String Authorization, @RequestBody
	NotificationReadRequest notificationReadRequest) {
		String token = Authorization.split(" ")[1];
		notificationService.readNotification(token, notificationReadRequest);
		return ResponseEntity.ok(new SuccessResponse("읽음처리 되었습니다."));
	}

}
