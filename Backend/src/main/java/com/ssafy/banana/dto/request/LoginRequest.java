package com.ssafy.banana.dto.request;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A DTO for the {@link com.ssafy.banana.db.entity.User} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ApiModel("로그인요청 DTO")
public class LoginRequest implements Serializable {
	@ApiModelProperty(name = "유저 email", example = "banana@gmail.com")
	@NotNull(message = "email may not be empty")
	@Email(message = "이메일 형식이 아닙니다.")
	private String email;
	@ApiModelProperty(name = "유저 password", example = "1q2w3e4r!")
	@NotNull(message = "비밀번호를 입력해야 합니다.")
	private String password;
}