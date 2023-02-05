package com.ssafy.banana.dto.response;

import io.swagger.annotations.Api;
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
@ApiModel("예외발생시 응답 DTO")
public class ExceptionResponse {
	@ApiModelProperty(name = "오류코드", example = "E001")
	private String code;
	@ApiModelProperty(name = "오류메시지", example = "잘못된 요청입니다.")
	private String message;

}
