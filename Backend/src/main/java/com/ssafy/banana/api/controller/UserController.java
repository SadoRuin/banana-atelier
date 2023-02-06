package com.ssafy.banana.api.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.banana.api.service.UserService;
import com.ssafy.banana.dto.request.SignupRequest;
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
@Api(tags = "유저관련 API")
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping("/signup")
	@ApiOperation(value = "회원가입")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "email", value = "유저 이메일", required = true),
		@ApiImplicitParam(name = "password", value = "비밀번호", required = true),
		@ApiImplicitParam(name = "nickname", value = "닉네임", required = true)
	})
	public ResponseEntity signup(@Valid @RequestBody SignupRequest signupRequest) {
		userService.signup(signupRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse("회원가입이 완료되었습니다."));
	}

	@GetMapping("/check/email/{email}")
	@ApiOperation(value = "이메일 중복체크")
	@ApiImplicitParam(name = "email", value = "유저 이메일", required = true)
	@ApiResponses({
		@ApiResponse(code = 200, message = "중복체크 통과"),
		@ApiResponse(code = 409, message = "이미 가입된 이메일 존재", response = ExceptionResponse.class)
	})
	public ResponseEntity checkEmail(@PathVariable String email) {
		userService.checkEmail(email);
		return ResponseEntity.ok(new SuccessResponse("사용가능한 이메일입니다."));
	}

	@GetMapping("/verify/{email}")
	@ApiOperation(value = "인증메일 발송")
	@ApiImplicitParam(name = "email", value = "유저 이메일", required = true)
	@ApiResponses({
		@ApiResponse(code = 200, message = "인증코드 발송"),
		@ApiResponse(code = 400, message = "메일 발송 오류", response = ExceptionResponse.class)
	})
	public ResponseEntity verify(@PathVariable String email) {
		userService.sendVerificationMail(email);
		return ResponseEntity.ok(new SuccessResponse("인증 메일이 발송되었습니다."));
	}

	@GetMapping("/check/nickname/{nickname}")
	@ApiOperation(value = "닉네임 중복체크")
	@ApiImplicitParam(name = "nickname", value = "유저 닉네임", required = true)
	@ApiResponses({
		@ApiResponse(code = 200, message = "중복체크 통과", response = SuccessResponse.class),
		@ApiResponse(code = 409, message = "이미 가입된 닉네임 존재", response = ExceptionResponse.class)
	})
	public ResponseEntity checkNickname(@PathVariable String nickname) {
		userService.checkNickname(nickname);
		return ResponseEntity.ok(new SuccessResponse("사용가능한 닉네임입니다."));
	}
}