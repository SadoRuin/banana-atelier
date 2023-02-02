package com.ssafy.banana.api.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ssafy.banana.db.repository.UserRepository;
import com.ssafy.banana.dto.request.LoginRequest;
import com.ssafy.banana.dto.request.VerifyRequest;
import com.ssafy.banana.dto.response.LoginResponse;
import com.ssafy.banana.exception.CustomException;
import com.ssafy.banana.exception.CustomExceptionType;
import com.ssafy.banana.security.UserPrincipal;
import com.ssafy.banana.security.jwt.TokenProvider;
import com.ssafy.banana.util.EmailUtil;
import com.ssafy.banana.util.RedisUtil;

import io.jsonwebtoken.io.Encoders;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	private final TokenProvider tokenProvider;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	private final EmailUtil emailUtil;
	private final RedisUtil redisUtil;
	private final UserRepository userRepository;

	public LoginResponse login(LoginRequest loginRequest) {

		UsernamePasswordAuthenticationToken authenticationToken =
			new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());

		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = tokenProvider.createToken(authentication);
		UserPrincipal userPrincipal = (UserPrincipal)authentication.getPrincipal();

		return LoginResponse.builder()
			.nickname(userPrincipal.getNickname())
			.profileImg(userPrincipal.getProfileImg())
			.role(userPrincipal.getRole())
			.token(jwt)
			.build();
	}

	public void verifyEmail(VerifyRequest verifyRequest) {
		String key = Encoders.BASE64.encode(verifyRequest.getEmail().getBytes());
		String value = redisUtil.getData(key);
		if (value == null) {
			throw new CustomException(CustomExceptionType.EXPIRED_AUTH_INFO);
		} else if (value.equals(verifyRequest.getCode())) {
			redisUtil.deleteData(key);
		} else {
			throw new CustomException(CustomExceptionType.EMAIL_CODE_ERROR);
		}
	}

}
