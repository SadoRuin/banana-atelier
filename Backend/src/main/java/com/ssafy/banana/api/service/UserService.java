package com.ssafy.banana.api.service;

import java.util.Random;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.banana.db.entity.User;
import com.ssafy.banana.db.entity.enums.Role;
import com.ssafy.banana.db.repository.UserRepository;
import com.ssafy.banana.dto.UserDto;
import com.ssafy.banana.dto.request.SignupRequest;
import com.ssafy.banana.exception.CustomException;
import com.ssafy.banana.exception.CustomExceptionType;
import com.ssafy.banana.util.EmailUtil;
import com.ssafy.banana.util.RedisUtil;
import com.ssafy.banana.util.SecurityUtil;

import io.jsonwebtoken.io.Encoders;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final RedisUtil redisUtil;
	private final EmailUtil emailUtil;

	@Transactional
	public void signup(SignupRequest signupRequest) {
		checkEmail(signupRequest.getEmail());

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
	public UserDto getUserInfo(long id) {
		return UserDto.from(userRepository.findById(id).orElse(null));
	}

	@Transactional(readOnly = true)
	public UserDto getMyUserInfo() {
		return UserDto.from(
			SecurityUtil.getCurrentUsername()
				.flatMap(userRepository::findByEmail)
				.orElseThrow(() -> new CustomException(CustomExceptionType.USER_NOT_FOUND))
		);
	}

	@Transactional(readOnly = true)
	public void checkEmail(String email) {
		if (userRepository.findByEmail(email).orElse(null) != null) {
			throw new CustomException(CustomExceptionType.USER_CONFLICT);
		}
	}

	public void sendVerificationMail(String email) {
		String key = "AC:" + Encoders.BASE64.encode(email.getBytes());
		String value = createCode();
		emailUtil.sendEmail(email, "[바나나공방] 회원가입 인증메일입니다.", value);
		redisUtil.setDataExpire(key, value, 5 * 60 * 1000);
	}

	@Transactional(readOnly = true)
	public void checkNickname(String nickname) {
		if (userRepository.findByNickname(nickname).orElse(null) != null) {
			throw new CustomException(CustomExceptionType.USER_CONFLICT);
		}
	}

	public static String createCode() {
		StringBuffer key = new StringBuffer();
		Random rnd = new Random();

		for (int i = 0; i < 8; i++) { // 인증코드 8자리
			int index = rnd.nextInt(3); // 0~2 까지 랜덤

			switch (index) {
				case 0:
					key.append((char)((int)(rnd.nextInt(26)) + 97));
					//  a~z  (ex. 1+97=98 => (char)98 = 'b')
					break;
				case 1:
					key.append((char)((int)(rnd.nextInt(26)) + 65));
					//  A~Z
					break;
				case 2:
					key.append((rnd.nextInt(10)));
					// 0~9
					break;
			}
		}
		return key.toString();
	}
}