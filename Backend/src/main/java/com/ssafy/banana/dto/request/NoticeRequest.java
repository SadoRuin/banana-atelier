package com.ssafy.banana.dto.request;

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
	private Long userSeq;
	private String noticeTitle;
	private String noticeContent;
	private Long noticeSeq;
}
