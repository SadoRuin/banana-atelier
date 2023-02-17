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

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ApiModel("이메일만 데이터로 사용하는 요청 DTO")
public class EmailRequest implements Serializable {
	@ApiModelProperty(name = "유저 email", example = "banana@gmail.com")
	@NotNull(message = "email may not be empty")
	@Email(message = "이메일 형식이 아닙니다.")
	private String email;
}
