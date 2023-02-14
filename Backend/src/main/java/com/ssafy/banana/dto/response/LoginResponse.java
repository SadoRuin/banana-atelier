package com.ssafy.banana.dto.response;

import com.ssafy.banana.db.entity.enums.Role;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("로그인응답 DTO")
public class LoginResponse {

	@ApiModelProperty(name = "유저 번호", example = "123")
	private long userSeq;
	@ApiModelProperty(name = "유저 닉네임", example = "닉넴")
	private String nickname;
	@ApiModelProperty(name = "프로필 이미지명", example = "default_profile_1.png")
	private String profileImg;
	@ApiModelProperty(name = "유저 권한", example = "USER or ARTIST")
	private Role role;
	@ApiModelProperty(name = "액세스 토큰", example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI4MyIsImlhdCI6MTY3NTU3NjQ2NCwiZXhwIjoxNjc1NTc4MjY0LCJhdXRoIjoiVVNFUiJ9.sC4JNLDVike7-GHADhf_DWRjddJcrLuESU669LEn5ZMf7UsrEr-l2sDx9R3dejCbEen6M3r5WMBeLv6inAJAI")
	private String token;
	@ApiModelProperty(name = "액세스 토큰 만료시간")
	private long expiration;
}