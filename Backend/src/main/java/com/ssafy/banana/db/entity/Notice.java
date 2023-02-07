package com.ssafy.banana.db.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "notice")
public class Notice {
	@Id
	@Column(name = "notice_seq", nullable = false)
	private Long id;

	@Column(name = "notice_title", nullable = false, length = 100)
	private String noticeTitle;

	@Lob
	@Column(name = "notice_content")
	private String noticeContent;

	@Column(name = "notice_time")
	private LocalDateTime noticeTime;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_seq", nullable = false)
	private Artist artist;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public LocalDateTime getNoticeTime() {
		return noticeTime;
	}

	public void setNoticeTime(LocalDateTime noticeTime) {
		this.noticeTime = noticeTime;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

}