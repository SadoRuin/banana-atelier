package com.ssafy.banana.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ApiModel("회원정보수정 요청 DTO")
public class UpdateUserRequest {
	@ApiModelProperty(name = "유저 nickname")
	private String nickname;
	@ApiModelProperty(name = "유저 password", example = "1q2w3e4r!")
	private String password;

}
