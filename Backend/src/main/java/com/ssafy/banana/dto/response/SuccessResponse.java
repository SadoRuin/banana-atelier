package com.ssafy.banana.dto.response;

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
@ApiModel("요청성공시 메시지 응답 DTO")
public class SuccessResponse {
	@ApiModelProperty(name = "상태", example = "success")
	private final String code = "success";
	@ApiModelProperty(name = "메시지", example = "ㅇㅇ이 왼료되었습니다.")
	private String message;

}
