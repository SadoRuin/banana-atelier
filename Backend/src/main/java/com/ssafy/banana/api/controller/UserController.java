package com.ssafy.banana.api.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.banana.db.repository.UserRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@RestController
@Api(tags = "유저관련 API")
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

	private final UserRepository userRepository;

	@PostMapping("/login")
	@ApiOperation(value = "일반 로그인")
	public ResponseEntity login(@ApiParam(value = "email, password") @RequestBody Map<String, String> userData) {
		return ResponseEntity.ok().build();
	}
}
