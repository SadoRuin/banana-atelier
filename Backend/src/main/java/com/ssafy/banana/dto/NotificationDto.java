package com.ssafy.banana.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.banana.dto.enums.NotificationType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A DTO for the {@link com.ssafy.banana.db.entity.NoticeNotification} entity
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ApiModel("알림 DTO")
public class NotificationDto {
	@ApiModelProperty(name = "알림 번호", example = "123")
	private long notificationSeq;
	@ApiModelProperty(name = "알림 내용", example = "OOO 작가가 새로운 공지를 게시했습니다.")
	private String notificationContent;
	@ApiModelProperty(name = "알림 등록 시간", example = "yyyy-MM-dd'T'HH:mm:ss")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
	private LocalDateTime notificationTime;
	@ApiModelProperty(name = "알림 읽음 여부 T: 읽음/ F: 안읽음", example = "false")
	private boolean notificationIsRead;
	@ApiModelProperty(name = "알림을 등록한 작가의 번호", example = "123")
	private long artistSeq;
	@ApiModelProperty(name = "알림 타입", example = "NOTICE or CURATION")
	private NotificationType notificationType;
}