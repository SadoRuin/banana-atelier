package com.ssafy.banana.api.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.banana.db.entity.User;
import com.ssafy.banana.db.entity.enums.Role;
import com.ssafy.banana.db.repository.UserRepository;
import com.ssafy.banana.dto.UserDto;
import com.ssafy.banana.dto.request.SignupRequest;
import com.ssafy.banana.exception.DuplicateUserException;
import com.ssafy.banana.exception.NotFoundUserException;
import com.ssafy.banana.util.RedisUtil;
import com.ssafy.banana.util.SecurityUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final RedisUtil redisUtil;

	@Transactional
	public void signup(SignupRequest signupRequest) {
		if (userRepository.findByEmail(signupRequest.getEmail()).orElse(null) != null) {
			throw new DuplicateUserException("이미 가입되어 있는 유저입니다.");
		}

		int imgNum = (int)(Math.random() * 4) + 1;
		String profileImg = "default_profile_" + imgNum + ".png";

		User user = User.builder()
			.email(signupRequest.getEmail())
			.password(passwordEncoder.encode(signupRequest.getPassword()))
			.nickname(signupRequest.getNickname())
			.profileImg(profileImg)
			.artistLikeCount(0)
			.role(Role.USER)
			.isAuthorized(false)
			.build();

		userRepository.save(user);
	}

	@Transactional(readOnly = true)
	public UserDto getUserInfo(String email) {
		return UserDto.from(userRepository.findByEmail(email).orElse(null));
	}

	@Transactional(readOnly = true)
	public UserDto getMyUserInfo() {
		return UserDto.from(
			SecurityUtil.getCurrentUsername()
				.flatMap(userRepository::findByEmail)
				.orElseThrow(() -> new NotFoundUserException("User not found"))
		);
	}

	public void verifyEmail(String key) {
		String email = redisUtil.getData(key);
		User user = userRepository.findByEmailAndIsAuthorized(email, false)
			.orElseThrow(() -> new DuplicateUserException(email + " -> 이미 이메일 인증이 완료된 사용자입니다."));
		user.setAuthorized(true);
		userRepository.save(user);
		redisUtil.deleteData(key);
	}
}