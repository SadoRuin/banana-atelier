package com.ssafy.banana.dto.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

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
@ApiModel("비밀번호만 데이터로 사용하는 요청 DTO")
public class PasswordRequest implements Serializable {
	@ApiModelProperty(name = "유저 password", example = "1q2w3e4r!")
	@NotNull(message = "비밀번호를 입력해야 합니다.")
	private String password;
}
