package com.ssafy.banana.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.banana.api.service.UserService;
import com.ssafy.banana.dto.DownloladFileDto;
import com.ssafy.banana.dto.UserDto;
import com.ssafy.banana.dto.request.EmailRequest;
import com.ssafy.banana.dto.request.SeqRequest;
import com.ssafy.banana.dto.request.SignupRequest;
import com.ssafy.banana.dto.request.UpdateUserRequest;
import com.ssafy.banana.dto.response.ExceptionResponse;
import com.ssafy.banana.dto.response.SuccessResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@Api(tags = "유저관련 API")
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping("/signup")
	@ApiOperation(value = "회원가입")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "email", value = "유저 이메일", required = true),
		@ApiImplicitParam(name = "password", value = "비밀번호", required = true),
		@ApiImplicitParam(name = "nickname", value = "닉네임", required = true)
	})
	public ResponseEntity<SuccessResponse> signup(@Valid @RequestBody SignupRequest signupRequest) {
		userService.signup(signupRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse("회원가입이 완료되었습니다."));
	}

	@GetMapping("/check/email/{email}")
	@ApiOperation(value = "이메일 중복체크")
	@ApiImplicitParam(name = "email", value = "유저 이메일", required = true)
	@ApiResponses({
		@ApiResponse(code = 200, message = "중복체크 통과"),
		@ApiResponse(code = 409, message = "이미 가입된 이메일 존재", response = ExceptionResponse.class)
	})
	public ResponseEntity<SuccessResponse> checkEmail(@PathVariable String email) {
		userService.checkEmail(email);
		return ResponseEntity.ok(new SuccessResponse("사용가능한 이메일입니다."));
	}

	@GetMapping("/verify/{email}")
	@ApiOperation(value = "인증메일 발송")
	@ApiImplicitParam(name = "email", value = "유저 이메일", required = true)
	@ApiResponses({
		@ApiResponse(code = 200, message = "인증코드 발송"),
		@ApiResponse(code = 400, message = "메일 발송 오류", response = ExceptionResponse.class)
	})
	public ResponseEntity<SuccessResponse> verify(@PathVariable String email) {
		userService.sendVerificationMail(email);
		return ResponseEntity.ok(new SuccessResponse("인증 메일이 발송되었습니다."));
	}

	@GetMapping("/check/nickname/{nickname}")
	@ApiOperation(value = "닉네임 중복체크")
	@ApiImplicitParam(name = "nickname", value = "유저 닉네임", required = true)
	@ApiResponses({
		@ApiResponse(code = 200, message = "중복체크 통과", response = SuccessResponse.class),
		@ApiResponse(code = 409, message = "이미 가입된 닉네임 존재", response = ExceptionResponse.class)
	})
	public ResponseEntity<SuccessResponse> checkNickname(@PathVariable String nickname) {
		userService.checkNickname(nickname);
		return ResponseEntity.ok(new SuccessResponse("사용가능한 닉네임입니다."));
	}

	@PatchMapping("/find-password")
	@ApiOperation(value = "비밀번호 찾기")
	@ApiImplicitParam(name = "email", value = "유저 이메일", required = true)
	@ApiResponses({
		@ApiResponse(code = 200, message = "이메일로 임시비밀번호 발송", response = SuccessResponse.class),
		@ApiResponse(code = 400, message = "이메일 발송 오류 발생", response = ExceptionResponse.class),
		@ApiResponse(code = 404, message = "회원 정보가 없습니다.", response = ExceptionResponse.class)
	})
	public ResponseEntity<SuccessResponse> findPassword(@RequestBody EmailRequest emailRequest) {
		userService.findPassword(emailRequest.getEmail());
		return ResponseEntity.ok(new SuccessResponse("임시비밀번호가 발송되었습니다."));
	}

	@GetMapping("/profile")
	@ApiOperation(value = "본인 회원정보 조회")
	@ApiResponses({
		@ApiResponse(code = 200, message = "회원정보 조회 성공", response = UserDto.class),
		@ApiResponse(code = 404, message = "회원 정보가 없습니다.", response = ExceptionResponse.class)
	})
	public ResponseEntity<? extends UserDto> getMyUserInfo() {
		return ResponseEntity.ok(userService.getMyUserInfo().getT());
	}

	@GetMapping("/profile/{userSeq}")
	@ApiOperation(value = "회원정보 조회")
	@ApiImplicitParam(name = "user_seq", value = "유저 회원번호", required = true)
	@ApiResponses({
		@ApiResponse(code = 200, message = "회원정보 조회 성공", response = UserDto.class),
		@ApiResponse(code = 404, message = "회원 정보가 없습니다.", response = ExceptionResponse.class)
	})
	public ResponseEntity<? extends UserDto> getUserInfo(@PathVariable long userSeq) {
		return ResponseEntity.ok(userService.getUserInfo(userSeq).getT());
	}

	@PatchMapping("/update")
	@ApiOperation(value = "회원정보 수정")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "nickname", value = "닉네임"),
		@ApiImplicitParam(name = "password", value = "비밀번호"),
		@ApiImplicitParam(name = "imageFile", value = "프로필이미지 파일")
	})
	@ApiResponses({
		@ApiResponse(code = 201, message = "회원정보 수정 성공", response = SuccessResponse.class),
		@ApiResponse(code = 400, message = "프로필 이미지 업로드 or 기존 이미지 삭제 실패.", response = ExceptionResponse.class),
		@ApiResponse(code = 403, message = "파일 확장자 오류.", response = ExceptionResponse.class),
		@ApiResponse(code = 404, message = "회원 정보가 없습니다.", response = ExceptionResponse.class)
	})
	public ResponseEntity<SuccessResponse> updateUser(@RequestPart UpdateUserRequest updateUserRequest,
		@RequestPart MultipartFile imageFile) {
		userService.updateUser(updateUserRequest, imageFile);
		return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse("회원정보가 수정되었습니다."));
	}

	@DeleteMapping("/delete")
	@ApiOperation(value = "회원 탈퇴")
	@ApiResponses({
		@ApiResponse(code = 200, message = "회원 탈퇴 성공", response = SuccessResponse.class),
		@ApiResponse(code = 401, message = "액세스토큰 오류", response = ExceptionResponse.class),
		@ApiResponse(code = 404, message = "회원 정보가 없습니다.", response = ExceptionResponse.class)
	})
	public ResponseEntity<SuccessResponse> deleteUser(@RequestHeader String Authorization) {
		String token = Authorization.split(" ")[1];
		userService.deleteUser(token);
		return ResponseEntity.ok(new SuccessResponse("탈퇴되었습니다."));
	}

	@PostMapping("/follow")
	@ApiOperation(value = "작가 팔로우")
	@ApiImplicitParam(name = "seq", value = "작가번호")
	@ApiResponses({
		@ApiResponse(code = 200, message = "팔로우 성공", response = SuccessResponse.class),
		@ApiResponse(code = 404, message = "회원 정보가 없습니다.", response = ExceptionResponse.class),
		@ApiResponse(code = 409, message = "이미 팔로우한 작가입니다.", response = ExceptionResponse.class)
	})
	public ResponseEntity<SuccessResponse> followArtist(@RequestHeader String Authorization,
		@RequestBody SeqRequest seqRequest) {
		String token = Authorization.split(" ")[1];
		userService.followArtist(token, seqRequest);
		return ResponseEntity.ok(new SuccessResponse("팔로우 되었습니다."));
	}

	@GetMapping("/follow")
	@ApiOperation(value = "작가 팔로우 리스트")
	@ApiResponses({
		@ApiResponse(code = 200, message = "팔로우 리스트 불러오기 성공", response = List.class),
		@ApiResponse(code = 404, message = "회원 정보가 없습니다.", response = ExceptionResponse.class)
	})
	public ResponseEntity<List<Long>> getFollowList(@RequestHeader String Authorization) {
		String token = Authorization.split(" ")[1];
		return ResponseEntity.ok(userService.getFollowList(token));
	}

	@DeleteMapping("/follow")
	@ApiOperation(value = "작가 언팔로우")
	@ApiImplicitParam(name = "seq", value = "작가번호")
	@ApiResponses({
		@ApiResponse(code = 200, message = "언팔로우 성공", response = SuccessResponse.class),
		@ApiResponse(code = 404, message = "회원 정보가 없습니다. or 팔로우 정보가 없습니다.", response = ExceptionResponse.class)
	})
	public ResponseEntity<SuccessResponse> unFollowArtist(@RequestHeader String Authorization,
		@RequestBody SeqRequest seqRequest) {
		String token = Authorization.split(" ")[1];
		userService.unFollowArtist(token, seqRequest);
		return ResponseEntity.ok(new SuccessResponse("팔로우가 해제되었습니다."));
	}

	@GetMapping("/download")
	@ApiOperation(value = "프로필 이미지 다운로드")
	@ApiResponses({
		@ApiResponse(code = 200, message = "다운로드 성공", response = byte[].class),
		@ApiResponse(code = 400, message = "다운로드 과정 중 오류", response = ExceptionResponse.class),
		@ApiResponse(code = 404, message = "회원 정보가 없습니다. or 팔로우 정보가 없습니다.", response = ExceptionResponse.class)
	})
	public ResponseEntity<byte[]> download(@RequestHeader String Authorization) {
		String token = Authorization.split(" ")[1];
		DownloladFileDto downloladFileDto = userService.download(token);

		return ResponseEntity.ok().headers(downloladFileDto.getHttpHeaders()).body(downloladFileDto.getImageFile());
	}
}
