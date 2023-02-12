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
@ApiModel("알림등록요청 DTO")
public class NotificationRequest {
	@ApiModelProperty(name = "공지사항 번호 or 큐레이션 번호", example = "123")
	private long seq;

}
