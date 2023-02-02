package com.ssafy.banana.api.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.banana.api.service.UserService;
import com.ssafy.banana.dto.request.SignupRequest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

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
	public ResponseEntity signup(@ApiIgnore @Valid @RequestBody SignupRequest signupRequest) {
		userService.signup(signupRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body("회원가입에 성공했습니다.");
	}

	@PostMapping("/verify")
	@ApiOperation(value = "이메일 중복체크 및 인증메일 발송")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "email", value = "유저 이메일", required = true)
	})
	@ApiResponses({
		@ApiResponse(code = 200, message = "중복체크 통과 및 인증코드 발송"),
		@ApiResponse(code = 409, message = "이미 가입된 이메일 존재", response = Exception.class)
	})
	public ResponseEntity verify(@RequestBody Map<String, String> map) {
		String email = map.get("email");
		userService.sendVerificationMail(email);
		return ResponseEntity.ok("사용가능한 이메일입니다.");
	}

}
