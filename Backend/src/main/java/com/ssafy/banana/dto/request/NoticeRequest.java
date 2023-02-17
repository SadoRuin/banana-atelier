package com.ssafy.banana.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Accessors(chain = true)
public class NoticeRequest {
	@ApiModelProperty(name = "공지사항 제목", example = "제목", required = true)
	private String noticeTitle;
	@ApiModelProperty(name = "공지사항 내용", example = "내용", required = true)
	private String noticeContent;
	@ApiModelProperty(name = "공지사항 번호", example = "123")
	private Long noticeSeq;
}
