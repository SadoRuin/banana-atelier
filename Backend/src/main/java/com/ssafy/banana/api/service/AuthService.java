package com.ssafy.banana.api.service;

import java.util.UUID;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ssafy.banana.db.entity.User;
import com.ssafy.banana.db.repository.UserRepository;
import com.ssafy.banana.dto.request.LoginRequest;
import com.ssafy.banana.dto.response.LoginResponse;
import com.ssafy.banana.exception.DuplicateUserException;
import com.ssafy.banana.exception.ExpiredException;
import com.ssafy.banana.exception.NotFoundUserException;
import com.ssafy.banana.security.UserPrincipal;
import com.ssafy.banana.security.jwt.TokenProvider;
import com.ssafy.banana.util.EmailUtil;
import com.ssafy.banana.util.RedisUtil;

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

	public void sendVerificationMail(String email) {
		userRepository.findByEmail(email)
			.orElseThrow(() -> {
				System.out.println(userRepository.findByEmail(email));
				return new NotFoundUserException("가입되지 않은 사용자입니다.");
			});

		UUID uuid = UUID.randomUUID();
		redisUtil.setDataExpire(uuid.toString(), email, 60);
		emailUtil.sendEmail(email, "[바나나공방] 회원가입 인증메일입니다.", uuid.toString());
	}

	public void verifyEmail(String key) {
		String email = redisUtil.getData(key);
		if (email == null) {
			throw new ExpiredException("인증시간이 만료되었습니다.");
		}
		User user = userRepository.findByEmailAndIsAuthorized(email, false)
			.orElseThrow(() -> new DuplicateUserException(email + " -> 이미 이메일 인증이 완료된 사용자입니다."));
		user.setAuthorized(true);
		userRepository.save(user);
		redisUtil.deleteData(key);
	}

}
