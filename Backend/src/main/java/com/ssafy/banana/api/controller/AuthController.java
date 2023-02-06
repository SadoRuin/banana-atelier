package com.ssafy.banana.api.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.banana.api.service.AuthService;
import com.ssafy.banana.dto.TokenDto;
import com.ssafy.banana.dto.request.LoginRequest;
import com.ssafy.banana.dto.request.VerifyRequest;
import com.ssafy.banana.dto.response.ExceptionResponse;
import com.ssafy.banana.dto.response.LoginResponse;
import com.ssafy.banana.dto.response.SuccessResponse;
import com.ssafy.banana.security.jwt.JwtFilter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@Api(tags = "인증관련 API")
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	private final Logger logger = LoggerFactory.getLogger(AuthController.class);
	private final AuthService authService;

	@PostMapping("/login")
	@ApiOperation(value = "일반 로그인")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "email", value = "로그인할 이메일", required = true),
		@ApiImplicitParam(name = "password", value = "비밀번호", required = true)
	})
	public ResponseEntity<LoginResponse> authorize(@Valid @RequestBody LoginRequest loginRequest) {

		LoginResponse loginResponse = authService.login(loginRequest);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + loginResponse.getToken());

		return ResponseEntity.ok().headers(httpHeaders).body(loginResponse);
	}

	@PostMapping("/verify")
	@ApiOperation(value = "이메일 인증 코드 검증")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "email", value = "로그인할 이메일", required = true),
		@ApiImplicitParam(name = "code", value = "인증코드", required = true)
	})
	@ApiResponses({
		@ApiResponse(code = 200, message = "이메일 인증 성공", response = SuccessResponse.class),
		@ApiResponse(code = 401, message = "저장된 인증코드와 일치하지 않음", response = ExceptionResponse.class),
		@ApiResponse(code = 404, message = "저장된 인증코드가 없음 = 만료", response = ExceptionResponse.class)
	})
	public ResponseEntity getVerify(@RequestBody VerifyRequest verifyRequest) {
		authService.verifyEmail(verifyRequest);
		return ResponseEntity.ok().body(new SuccessResponse("인증되었습니다."));
	}

	@PostMapping("/logout")
	@ApiOperation(value = "로그아웃", notes = "토큰을 redis에 저장. 이제 해당 토큰은 더이상 사용할 수 없다.")
	@ApiResponses({
		@ApiResponse(code = 200, message = "로그아웃 성공", response = SuccessResponse.class),
		@ApiResponse(code = 401, message = "인증 실패(없는 사용자 or 비밀번호 오류 or 이메일 미인증 or 로그아웃된 사용자)", response = ExceptionResponse.class),
		@ApiResponse(code = 404, message = "회원 정보가 없습니다.", response = ExceptionResponse.class),
		@ApiResponse(code = 500, message = "서버 오류", response = ExceptionResponse.class)
	})
	@Transactional
	public ResponseEntity logout(@RequestHeader String Authorization) {
		String token = Authorization.split(" ")[1];
		authService.logout(token);
		return ResponseEntity.ok().body(new SuccessResponse("로그아웃 되었습니다."));
	}

	@PostMapping("/reissue")
	@ApiOperation(value = "액세스토큰 재발급", notes = "저장된 리프레시 토큰이 유효하다면 액세스토큰 재발급")
	@ApiResponses({
		@ApiResponse(code = 200, message = "액세스토큰 재발급 성공", response = SuccessResponse.class),
		@ApiResponse(code = 401, message = "인증 실패(없는 사용자 or 비밀번호 오류 or 이메일 미인증 or 로그아웃된 사용자)", response = ExceptionResponse.class),
		@ApiResponse(code = 404, message = "회원 정보가 없습니다.", response = ExceptionResponse.class),
		@ApiResponse(code = 500, message = "서버 오류", response = ExceptionResponse.class)
	})
	@Transactional
	public ResponseEntity reissue(@RequestHeader String Authorization) {
		String token = Authorization.split(" ")[1];
		TokenDto tokenDto = authService.reissue(token);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + tokenDto.getToken());

		return ResponseEntity.ok().headers(httpHeaders).body(tokenDto);
	}
}