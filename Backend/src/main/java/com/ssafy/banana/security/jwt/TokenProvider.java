package com.ssafy.banana.security.jwt;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.ssafy.banana.db.repository.UserRepository;
import com.ssafy.banana.exception.CustomException;
import com.ssafy.banana.exception.CustomExceptionType;
import com.ssafy.banana.security.UserPrincipal;
import com.ssafy.banana.util.RedisUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

@Component
public class TokenProvider implements InitializingBean {

	private final Logger logger = LoggerFactory.getLogger(TokenProvider.class);
	private static final String AUTHORITIES_KEY = "auth";
	@Value("${jwt.access-token-valid-time}")
	private long accessTokenValidTime;
	@Value("${jwt.refresh-token-valid-time}")
	private long refreshTokenValidTime;
	private String secret;
	private Key key;
	private UserRepository userRepository;
	private RedisUtil redisUtil;

	public TokenProvider(
		@Value("${jwt.secret}") String secret, UserRepository userRepository, RedisUtil redisUtil) {
		this.secret = secret;
		this.userRepository = userRepository;
		this.redisUtil = redisUtil;
	}

	@Override
	public void afterPropertiesSet() {
		byte[] keyBytes = Decoders.BASE64.decode(secret);
		this.key = Keys.hmacShaKeyFor(keyBytes);
	}

	public String createAccessToken(Authentication authentication) {
		UserPrincipal userPrincipal = (UserPrincipal)authentication.getPrincipal();

		Date now = new Date();
		Date expriyDate = new Date(now.getTime() + accessTokenValidTime * 1000);

		return Jwts.builder()
			.setSubject(userPrincipal.getName())
			.setIssuedAt(now)
			.setExpiration(expriyDate)
			.claim(AUTHORITIES_KEY, userPrincipal.getRole())
			.signWith(key, SignatureAlgorithm.HS512)
			.compact();
	}

	public void createRefreshToken(Authentication authentication) {
		UserPrincipal userPrincipal = (UserPrincipal)authentication.getPrincipal();

		Date now = new Date();
		Date expriyDate = new Date(now.getTime() + refreshTokenValidTime * 1000);

		String refreshToken = Jwts.builder()
			.setSubject(userPrincipal.getName())
			.setIssuedAt(now)
			.setExpiration(expriyDate)
			.claim(AUTHORITIES_KEY, userPrincipal.getRole())
			.signWith(key, SignatureAlgorithm.HS512)
			.compact();

		String key = "RT:" + Encoders.BASE64.encode(userPrincipal.getUsername().getBytes());
		redisUtil.setDataExpire(key, refreshToken, refreshTokenValidTime);
	}

	public Authentication getAuthentication(String token) {
		Long id = getSubject(token);

		UserPrincipal userPrincipal = userRepository.findById(id)
			.map(user -> UserPrincipal.create(user))
			.orElseThrow(() -> new CustomException(CustomExceptionType.USER_NOT_FOUND));

		Collection<? extends GrantedAuthority> authorities =
			Arrays.stream(userPrincipal.getRole().toString().split(","))
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());

		userPrincipal.setPassword("");

		return new UsernamePasswordAuthenticationToken(userPrincipal, token, authorities);
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;
		} catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
			logger.info("잘못된 JWT 서명입니다.");
		} catch (ExpiredJwtException e) {
			logger.info("만료된 JWT 토큰입니다.");
		} catch (UnsupportedJwtException e) {
			logger.info("지원되지 않는 JWT 토큰입니다.");
		} catch (IllegalArgumentException e) {
			logger.info("JWT 토큰이 잘못되었습니다.");
		}
		return false;
	}

	public long getSubject(String token) {
		try {
			String userSeq = Jwts
				.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
			return Long.parseLong(userSeq);
		} catch (ExpiredJwtException e) {
			return Long.parseLong(e.getClaims().getSubject());
		}
	}

	public long getExpiration(String token) {
		try {
			Date expiration = Jwts
				.parserBuilder()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getExpiration();
			return expiration.getTime();
		} catch (ExpiredJwtException e) {
			return e.getClaims().getExpiration().getTime();
		}
	}
}
