package com.ssafy.banana.api.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ssafy.banana.dto.TokenDto;
import com.ssafy.banana.dto.request.LoginRequest;
import com.ssafy.banana.dto.request.VerifyRequest;
import com.ssafy.banana.dto.response.LoginResponse;
import com.ssafy.banana.exception.CustomException;
import com.ssafy.banana.exception.CustomExceptionType;
import com.ssafy.banana.security.UserPrincipal;
import com.ssafy.banana.security.jwt.TokenProvider;
import com.ssafy.banana.util.RedisUtil;
import com.ssafy.banana.util.SecurityUtil;

import io.jsonwebtoken.io.Encoders;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	private final Logger logger = LoggerFactory.getLogger(AuthService.class);

	private final TokenProvider tokenProvider;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	private final SecurityUtil securityUtil;
	private final RedisUtil redisUtil;

	public LoginResponse login(LoginRequest loginRequest) {

		UsernamePasswordAuthenticationToken authenticationToken =
			new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());

		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

		String accessToken = tokenProvider.createAccessToken(authentication);
		tokenProvider.createRefreshToken(authentication);
		UserPrincipal userPrincipal = (UserPrincipal)authentication.getPrincipal();
		userPrincipal.setPassword("");
		SecurityContextHolder.getContext()
			.setAuthentication(new UsernamePasswordAuthenticationToken(userPrincipal, ""));

		return LoginResponse.builder()
			.nickname(userPrincipal.getNickname())
			.profileImg(userPrincipal.getProfileImg())
			.role(userPrincipal.getRole())
			.token(accessToken)
			.expiration(tokenProvider.getExpiration(accessToken))
			.build();
	}

	public void verifyEmail(VerifyRequest verifyRequest) {
		String key = "AC:" + Encoders.BASE64.encode(verifyRequest.getEmail().getBytes());
		String value = redisUtil.getData(key);
		if (value == null) {
			throw new CustomException(CustomExceptionType.EXPIRED_AUTH_INFO);
		} else if (value.equals(verifyRequest.getCode())) {
			redisUtil.deleteData(key);
		} else {
			throw new CustomException(CustomExceptionType.EMAIL_CODE_ERROR);
		}
	}

	public void logout(String token) {
		logger.info(token);
		String email = securityUtil.getCurrentUsername()
			.orElseThrow(() -> new CustomException(CustomExceptionType.USER_NOT_FOUND));
		String key = "RT:" + Encoders.BASE64.encode(email.getBytes());
		if (redisUtil.getData(key) != null) {
			redisUtil.deleteData(key);
		}

		long expiration = tokenProvider.getExpiration(token);
		Date now = new Date();
		redisUtil.setDataExpire(token, token, expiration - now.getTime());

		SecurityContextHolder.getContext().setAuthentication(null);
		logger.info("로그아웃 유저 이메일 : '{}'", email);
	}

	public TokenDto reissue(String token) {
		Authentication authentication = tokenProvider.getAuthentication(token);
		UserPrincipal userPrincipal = (UserPrincipal)authentication.getPrincipal();
		String email = userPrincipal.getUsername();
		String key = "RT:" + Encoders.BASE64.encode(email.getBytes());
		String refreshToken = redisUtil.getData(key);
		if (refreshToken == null) {
			throw new CustomException(CustomExceptionType.REFRESH_TOKEN_ERROR);
		}

		String accessToken = tokenProvider.createAccessToken(authentication);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		return TokenDto.builder()
			.token(accessToken)
			.expiration(tokenProvider.getExpiration(accessToken))
			.build();
	}
}
