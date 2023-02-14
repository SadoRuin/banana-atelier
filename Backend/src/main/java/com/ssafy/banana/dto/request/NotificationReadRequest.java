package com.ssafy.banana.dto.request;

import com.ssafy.banana.dto.enums.NotificationType;

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
@ApiModel("알림읽음요청 DTO")
public class NotificationReadRequest {
	@ApiModelProperty(name = "알림 번호", example = "123")
	private long notificationSeq;
	@ApiModelProperty(name = "알림 타입", example = "NOTICE or CURATION")
	private NotificationType notificationType;

}