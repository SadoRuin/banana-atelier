package com.ssafy.banana.api.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ssafy.banana.dto.request.LoginRequest;
import com.ssafy.banana.dto.response.LoginResponse;
import com.ssafy.banana.security.UserPrincipal;
import com.ssafy.banana.security.jwt.TokenProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	private final TokenProvider tokenProvider;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;

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
}
