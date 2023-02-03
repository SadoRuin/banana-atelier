package com.ssafy.banana.security.jwt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.ssafy.banana.exception.CustomException;
import com.ssafy.banana.exception.CustomExceptionType;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
	@Override
	public void commence(HttpServletRequest request,
		HttpServletResponse response,
		AuthenticationException authException) {
		// 유효한 자격증명을 제공하지 않고 접근하려 할때 401
		// response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "액세스 토큰 오류입니다.");
		throw new CustomException(CustomExceptionType.ACCESS_TOKEN_ERROR);
	}

}
