package com.ssafy.banana.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.banana.db.entity.Notice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class NoticeResponse {

	@JsonProperty(value = "notice_seq")
	private Long id;

	@JsonProperty(value = "notice_title")
	private String noticeTitle;

	@JsonProperty(value = "notice_content")
	private String noticeContent;

	@JsonProperty(value = "notice_time")
	private LocalDateTime noticeTime;

	@JsonProperty(value = "user_seq")
	private Long userSeq;

	public NoticeResponse(Notice notice) {
		this.id = notice.getId();
		this.noticeTitle = notice.getNoticeTitle();
		this.noticeContent = notice.getNoticeContent();
		this.noticeTime = notice.getNoticeTime();
		this.userSeq = notice.getArtist().getUser().getId();
	}
}
