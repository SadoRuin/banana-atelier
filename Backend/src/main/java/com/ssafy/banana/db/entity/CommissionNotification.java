package com.ssafy.banana.db.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "commission_notification")
public class CommissionNotification {
	@Id
	@Column(name = "notification_seq", nullable = false)
	private Long id;

	@Column(name = "notification_content", length = 100)
	private String notificationContent;

	@Column(name = "notification_time", nullable = false)
	private LocalDateTime notificationTime;

	@Column(name = "notification_is_read", nullable = false)
	private boolean notificationIsRead;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_seq", nullable = false)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "artist_seq", nullable = false)
	private Artist artist;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "commission_seq", nullable = false)
	private Commission commission;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNotificationContent() {
		return notificationContent;
	}

	public void setNotificationContent(String notificationContent) {
		this.notificationContent = notificationContent;
	}

	public LocalDateTime getNotificationTime() {
		return notificationTime;
	}

	public void setNotificationTime(LocalDateTime notificationTime) {
		this.notificationTime = notificationTime;
	}

	public boolean getNotificationIsRead() {
		return notificationIsRead;
	}

	public void setNotificationIsRead(boolean notificationIsRead) {
		this.notificationIsRead = notificationIsRead;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public Commission getCommission() {
		return commission;
	}

	public void setCommission(Commission commission) {
		this.commission = commission;
	}

}