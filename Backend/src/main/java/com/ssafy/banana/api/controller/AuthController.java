package com.ssafy.banana.api.controller;

import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.banana.api.service.AuthService;
import com.ssafy.banana.dto.request.LoginRequest;
import com.ssafy.banana.dto.response.LoginResponse;
import com.ssafy.banana.security.jwt.JwtFilter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
	public ResponseEntity verify(@RequestBody Map<String, String> map) {
		authService.sendVerificationMail(map.get("email"));
		return ResponseEntity.ok("성공적으로 인증메일을 보냈습니다.");
	}

	@GetMapping("/verify/{key}")
	public ResponseEntity getVerify(@PathVariable String key) {
		authService.verifyEmail(key);
		return ResponseEntity.ok("Successfully authenticated.");
	}
}