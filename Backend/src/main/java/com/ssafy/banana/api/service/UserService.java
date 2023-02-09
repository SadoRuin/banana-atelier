package com.ssafy.banana.api.service;

import java.util.Random;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.banana.db.entity.Artist;
import com.ssafy.banana.db.entity.MyArtist;
import com.ssafy.banana.db.entity.MyArtistId;
import com.ssafy.banana.db.entity.User;
import com.ssafy.banana.db.entity.enums.Role;
import com.ssafy.banana.db.repository.ArtistRepository;
import com.ssafy.banana.db.repository.MyArtistRepository;
import com.ssafy.banana.db.repository.UserRepository;
import com.ssafy.banana.dto.DownloladFileDto;
import com.ssafy.banana.dto.UserDto;
import com.ssafy.banana.dto.request.SeqRequest;
import com.ssafy.banana.dto.request.SignupRequest;
import com.ssafy.banana.dto.request.UpdateUserRequest;
import com.ssafy.banana.exception.CustomException;
import com.ssafy.banana.exception.CustomExceptionType;
import com.ssafy.banana.security.UserPrincipal;
import com.ssafy.banana.security.jwt.TokenProvider;
import com.ssafy.banana.util.EmailUtil;
import com.ssafy.banana.util.RedisUtil;
import com.ssafy.banana.util.SecurityUtil;

import io.jsonwebtoken.io.Encoders;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final AuthService authService;
	private final UserRepository userRepository;
	private final MyArtistRepository myArtistRepository;
	private final PasswordEncoder passwordEncoder;
	private final TokenProvider tokenProvider;
	private final SecurityUtil securityUtil;
	private final RedisUtil redisUtil;
	private final EmailUtil emailUtil;
	private final FileService fileService;
	private final AwsS3Service awsS3Service;
	private final char[] specialChars = {'!', '@', '$', '%', '(', ')'};
	private final ArtistRepository artistRepository;

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
			.role(Role.ROLE_USER)
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

	@Transactional
	public void updateUser(UpdateUserRequest updateUserRequest, MultipartFile imageFile) {
		User user = securityUtil.getCurrentUsername()
			.flatMap(userRepository::findByEmail)
			.orElseThrow(() -> new CustomException(CustomExceptionType.USER_NOT_FOUND));

		if (!updateUserRequest.getNickname().equals("")) {
			user.setNickname(updateUserRequest.getNickname());
		}
		if (!updateUserRequest.getPassword().equals("")) {
			user.setPassword(passwordEncoder.encode(updateUserRequest.getPassword()));
		}
		if (!imageFile.isEmpty()) {
			user.setProfileImg(awsS3Service.uploadProfileImage(user.getId(), imageFile));
		}

		userRepository.save(user);
	}

	@Transactional
	public void deleteUser(String token) {
		long id = tokenProvider.getSubject(token);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal userPrincipal = (UserPrincipal)authentication.getPrincipal();
		if (id == userPrincipal.getId()) {
			User user = userRepository.findById(id)
				.orElseThrow(() -> new CustomException(CustomExceptionType.USER_NOT_FOUND));
			authService.logout(token);
			userRepository.delete(user);
		} else {
			throw new CustomException(CustomExceptionType.ACCESS_TOKEN_ERROR);
		}
	}

	@Transactional
	public void followArtist(String token, SeqRequest seqRequest) {
		long userSeq = tokenProvider.getSubject(token);
		long artistSeq = seqRequest.getUserSeq();
		User user = userRepository.findById(userSeq)
			.orElseThrow(() -> new CustomException(CustomExceptionType.USER_NOT_FOUND));

		Artist artist = artistRepository.findById(artistSeq)
			.orElseThrow(() -> new CustomException(CustomExceptionType.USER_NOT_FOUND));

		MyArtistId myArtistId = MyArtistId.builder()
			.userSeq(userSeq)
			.artistSeq(artistSeq)
			.build();
		if (myArtistRepository.findById(myArtistId).isPresent()) {
			throw new CustomException(CustomExceptionType.ARTIST_FOLLOW_CONFLICT);
		}

		MyArtist myArtist = MyArtist.builder()
			.id(myArtistId)
			.artist(artist)
			.user(user)
			.build();

		myArtistRepository.save(myArtist);

		int like = artist.getUser().getArtistLikeCount();
		artist.getUser().setArtistLikeCount(like + 1);
		artistRepository.save(artist);
	}

	@Transactional
	public void unFollowArtist(String token, SeqRequest seqRequest) {
		long userSeq = tokenProvider.getSubject(token);
		long artistSeq = seqRequest.getUserSeq();

		Artist artist = artistRepository.findById(artistSeq)
			.orElseThrow(() -> new CustomException(CustomExceptionType.USER_NOT_FOUND));

		MyArtistId myArtistId = MyArtistId.builder()
			.userSeq(userSeq)
			.artistSeq(artistSeq)
			.build();
		MyArtist myArtist = myArtistRepository.findById(myArtistId)
			.orElseThrow(() -> new CustomException(CustomExceptionType.NO_CONTENT));

		myArtistRepository.delete(myArtist);

		int like = artist.getUser().getArtistLikeCount();
		artist.getUser().setArtistLikeCount(like - 1);
		artistRepository.save(artist);
	}

	public DownloladFileDto download(String token, String fileName) {
		long userSeq = tokenProvider.getSubject(token);
		return awsS3Service.downloadProfileImage(userSeq, fileName);
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
}