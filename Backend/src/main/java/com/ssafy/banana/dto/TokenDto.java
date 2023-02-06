package com.ssafy.banana.dto;

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
@ApiModel("토큰과 만료시간을 담는 DTO")
public class TokenDto {
	@ApiModelProperty(name = "액세스 토큰", example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI4MyIsImlhdCI6MTY3NTU3NjQ2NCwiZXhwIjoxNjc1NTc4MjY0LCJhdXRoIjoiVVNFUiJ9.sC4JNLDVike7-GHADhf_DWRjddJcrLuESU669LEn5ZMf7UsrEr-l2sDx9R3dejCbEen6M3r5WMBeLv6inAJAI")
	private String token;
	@ApiModelProperty(name = "액세스 토큰 만료시간")
	private long expiration;
}
