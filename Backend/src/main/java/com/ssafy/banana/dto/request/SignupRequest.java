package com.ssafy.banana.dto.request;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
@ApiModel("회원가입요청 DTO")
public class SignupRequest implements Serializable {
	@ApiModelProperty(name = "유저 email", example = "banana@gmail.com")
	@NotNull(message = "email may not be empty")
	@Email(message = "이메일 형식이 아닙니다.")
	private String email;
	@ApiModelProperty(name = "유저 password", example = "1q2w3e4r!")
	@NotNull(message = "비밀번호를 입력해야 합니다.")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$",
		message = "비밀번호는 영문, 숫자, 특수문자가 적어도 1개 이상씩 포함된 8자 ~ 16자의 비밀번호여야 합니다.")
	private String password;
	@ApiModelProperty(name = "유저 nickname")
	@NotNull(message = "nickname may not be empty")
	@Pattern(regexp = "^[0-9a-zA-Z가-힣][0-9a-zA-Z가-힣\\s]*$",
		message = "닉네임은 숫자, 영어, 한글만 가능합니다.")
	@Size(min = 1, max = 12)
	private String nickname;
}