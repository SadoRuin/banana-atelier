package com.ssafy.banana.api.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.banana.db.entity.User;
import com.ssafy.banana.db.entity.enums.Role;
import com.ssafy.banana.db.repository.UserRepository;
import com.ssafy.banana.dto.UserDto;
import com.ssafy.banana.exception.DuplicateUserException;
import com.ssafy.banana.exception.NotFoundUserException;
import com.ssafy.banana.util.SecurityUtil;

@Service
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Transactional
	public UserDto signup(UserDto userDto) {
		if (userRepository.findByEmail(userDto.getEmail()).orElse(null) != null) {
			throw new DuplicateUserException("이미 가입되어 있는 유저입니다.");
		}

		User user = User.builder()
			.email(userDto.getEmail())
			.password(passwordEncoder.encode(userDto.getPassword()))
			.nickname(userDto.getNickname())
			.artistLikeCount(0)
			.role(Role.USER)
			.isAuthorized(false)
			.build();

		return UserDto.from(userRepository.save(user));
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
}