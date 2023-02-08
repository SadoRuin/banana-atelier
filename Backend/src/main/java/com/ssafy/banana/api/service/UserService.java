package com.ssafy.banana.api.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.banana.db.entity.User;
import com.ssafy.banana.db.entity.enums.Role;
import com.ssafy.banana.db.repository.UserRepository;
import com.ssafy.banana.dto.UserDto;
import com.ssafy.banana.dto.request.SignupRequest;
import com.ssafy.banana.dto.request.UpdateUserRequest;
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
	private final SecurityUtil securityUtil;
	private final RedisUtil redisUtil;
	private final EmailUtil emailUtil;
	private final char[] specialChars = {'!', '@', '$', '%', '(', ')'};
	@Value("${images.profile}")
	private String profileImagePath;

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
			.build();

		userRepository.save(user);
	}

	@Transactional(readOnly = true)
	public UserDto getUserInfo(long id) {
		return UserDto.from(
			userRepository.findById(id)
				.orElseThrow(() -> new CustomException(CustomExceptionType.USER_NOT_FOUND))
		);
	}

	@Transactional(readOnly = true)
	public UserDto getMyUserInfo() {
		return UserDto.from(
			securityUtil.getCurrentUsername()
				.flatMap(userRepository::findByEmail)
				.orElseThrow(() -> new CustomException(CustomExceptionType.USER_NOT_FOUND))
		);
	}

	@Transactional(readOnly = true)
	public void checkEmail(String email) {
		if (userRepository.findByEmail(email).isPresent()) {
			throw new CustomException(CustomExceptionType.USER_CONFLICT);
		}
	}

	public void sendVerificationMail(String email) {
		String key = "AC:" + Encoders.BASE64.encode(email.getBytes());
		String value = createCode(8);
		String content = "";
		content += "<div style='margin:20px;'>";
		content += "<h1> 안녕하세요 바나나공방입니다. </h1>";
		content += "<br>";
		content += "<p>아래 코드를 복사해 입력해주세요</p>";
		content += "<br>";
		content += "<p>감사합니다.</p>";
		content += "<br>";
		content += "<div align='center' style='border:1px solid black; font-family:verdana';>";
		content += "<h3 style='color:blue;'>회원가입 인증 코드입니다.</h3>";
		content += "<div style='font-size:130%'>";
		content += "CODE : <strong>";
		content += value + "</strong><div><br/> ";
		content += "</div>";
		emailUtil.sendEmail(email, "[바나나공방] 회원가입 인증메일입니다.", content);
		redisUtil.setDataExpire(key, value, 5 * 60 * 1000);
	}

	@Transactional(readOnly = true)
	public void checkNickname(String nickname) {
		if (userRepository.findByNickname(nickname).isPresent()) {
			throw new CustomException(CustomExceptionType.USER_CONFLICT);
		}
	}

	public void findPassword(String email) {
		User user = userRepository.findByEmail(email)
			.orElseThrow(() -> new CustomException(CustomExceptionType.USER_NOT_FOUND));
		Random rand = new Random();
		String newPassword = createCode(14) + specialChars[rand.nextInt(6)];
		String content = "";
		content += "<div style='margin:20px;'>";
		content += "<h1> 안녕하세요 바나나공방입니다. </h1>";
		content += "<br>";
		content += "<p>회원님의 임시비밀번호를 발송해드립니다.</p>";
		content += "<br>";
		content += "<p>임시비밀번호로 로그인 후 비밀번호를 꼭 변경해주세요.</p>";
		content += "<br>";
		content += "<p>감사합니다.</p>";
		content += "<br>";
		content += "<div align='center' style='border:1px solid black; font-family:verdana';>";
		content += "<h3 style='color:blue;'>회원님의 임시비밀번호입니다.</h3>";
		content += "<div style='font-size:130%'>";
		content += "CODE : <strong>";
		content += newPassword + "</strong><div><br/> ";
		content += "</div>";
		emailUtil.sendEmail(email, "[바나나공방] 비밀번호 찾기 입니다.", content);
		user.setPassword(passwordEncoder.encode(newPassword));
		userRepository.save(user);
	}

	public String createCode(int digit) {
		StringBuffer key = new StringBuffer();
		Random rnd = new Random();

		for (int i = 0; i < digit; i++) { // 인증코드 8자리
			int index = rnd.nextInt(4); // 0~2 까지 랜덤

			switch (index) {
				case 0:
					key.append((char)(rnd.nextInt(26) + 97));
					//  a~z  (ex. 1+97=98 => (char)98 = 'b')
					break;
				case 1:
					key.append((char)(rnd.nextInt(26) + 65));
					//  A~Z
					break;
				case 2:
					key.append((rnd.nextInt(10)));
					// 0~9
					break;
				case 3:
					key.append(specialChars[rnd.nextInt(6)]);
					// 0~9
					break;
			}
		}
		return key.toString();
	}

	public void updateUser(UpdateUserRequest updateUserRequest, MultipartFile imageFile) {
		User user = securityUtil.getCurrentUsername()
			.flatMap(userRepository::findByEmail)
			.orElseThrow(() -> new CustomException(CustomExceptionType.USER_NOT_FOUND));

		System.out.println("updateUserRequest.getNickname() = " + updateUserRequest.getNickname());
		System.out.println("updateUserRequest.getPassword() = " + updateUserRequest.getPassword());
		System.out.println("imageFile = " + imageFile.getOriginalFilename());

		// userRepository.save(user);
	}
}