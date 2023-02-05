package com.ssafy.banana.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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
import com.ssafy.banana.util.RedisUtil;

import io.jsonwebtoken.io.Encoders;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	private final Logger logger = LoggerFactory.getLogger(AuthService.class);

	private final TokenProvider tokenProvider;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	private final RedisUtil redisUtil;
	@Value("${jwt.access-token-valid-time}")
	private long accessTokenValidTime;
	@Value("${jwt.refresh-token-valid-time}")
	private long refreshTokenValidTime;
	private final UserRepository userRepository;

	public LoginResponse login(LoginRequest loginRequest) {

		UsernamePasswordAuthenticationToken authenticationToken =
			new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());

		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

		String accessToken = tokenProvider.createToken(authentication, accessTokenValidTime);
		String refreshToken = tokenProvider.createToken(authentication, refreshTokenValidTime);
		String key = "RT:" + Encoders.BASE64.encode(loginRequest.getEmail().getBytes());
		redisUtil.setDataExpire(key, refreshToken, refreshTokenValidTime);
		UserPrincipal userPrincipal = (UserPrincipal)authentication.getPrincipal();
		userPrincipal.setPassword("");
		SecurityContextHolder.getContext()
			.setAuthentication(new UsernamePasswordAuthenticationToken(userPrincipal, ""));

		return LoginResponse.builder()
			.nickname(userPrincipal.getNickname())
			.profileImg(userPrincipal.getProfileImg())
			.role(userPrincipal.getRole())
			.token(accessToken)
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
		UserPrincipal userPrincipal = (UserPrincipal)SecurityContextHolder.getContext()
			.getAuthentication()
			.getPrincipal();
		String email = userPrincipal.getUsername();
		String key = "RT:" + Encoders.BASE64.encode(email.getBytes());
		if (redisUtil.getData(key) != null) {
			redisUtil.deleteData(key);
		}

		long expiration = tokenProvider.getExpiration(token);
		redisUtil.setDataExpire(token, token, expiration);

		SecurityContextHolder.getContext().setAuthentication(null);
		logger.info("로그아웃 유저 이메일 : '{}' , 유저 권한 : '{}'", userPrincipal.getUsername(), userPrincipal.getRole());
	}
}
