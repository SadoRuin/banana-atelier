package com.ssafy.banana.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import com.ssafy.banana.security.UserPrincipal;
import com.ssafy.banana.util.RedisUtil;

import io.jsonwebtoken.io.Encoders;

public class JwtFilter extends GenericFilterBean {

	private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);
	public static final String AUTHORIZATION_HEADER = "Authorization";
	private TokenProvider tokenProvider;
	private RedisUtil redisUtil;

	public JwtFilter(TokenProvider tokenProvider, RedisUtil redisUtil) {
		this.tokenProvider = tokenProvider;
		this.redisUtil = redisUtil;
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws
		IOException,
		ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
		String accessToken = resolveToken(httpServletRequest);
		String requestURI = httpServletRequest.getRequestURI();

		if (StringUtils.hasText(accessToken) && redisUtil.getData(accessToken) == null) {
			if (tokenProvider.validateToken(accessToken)) {
				Authentication authentication = tokenProvider.getAuthentication(accessToken);
				SecurityContextHolder.getContext().setAuthentication(authentication);
				logger.debug("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}", authentication.getName(), requestURI);
			} else {
				logger.debug("액세스 토큰이 유효하지 않습니다, uri: {}", requestURI);
				Authentication authentication = tokenProvider.getAuthentication(accessToken);
				UserPrincipal userPrincipal = (UserPrincipal)authentication.getPrincipal();
				String key = "RT:" + Encoders.BASE64.encode(userPrincipal.getUsername().getBytes());
				if (redisUtil.getData(key) != null) {
					accessToken = tokenProvider.createAccessToken(authentication);
					SecurityContextHolder.getContext().setAuthentication(authentication);
					redisUtil.deleteData(key);
					tokenProvider.createRefreshToken(authentication);
					HttpServletResponse response = (HttpServletResponse)servletResponse;
					response.addHeader(AUTHORIZATION_HEADER, "Bearer " + accessToken);
				} else {
					logger.debug("리프레시 토큰이 유효하지 않습니다, uri: {}", requestURI);
				}
			}
		} else {
			logger.debug("로그아웃한 사용자입니다. 새로 로그인해야 합니다., uri: {}", requestURI);
		}

		filterChain.doFilter(servletRequest, servletResponse);
	}

	private String resolveToken(HttpServletRequest request) {
		String bearerToken = request.getHeader(AUTHORIZATION_HEADER);

		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}

		return null;
	}
}