package com.ssafy.banana.db.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ssafy.banana.db.entity.enums.CommissionStatus;

@Entity
@Table(name = "commission")
public class Commission {
	@Id
	@Column(name = "commission_seq", nullable = false)
	private Long id;

	@Column(name = "commission_request_time", nullable = false)
	private LocalDateTime commissionRequestTime;

	@Column(name = "commission_accepted_time", nullable = false)
	private LocalDateTime commissionAcceptedTime;

	@Column(name = "commission_price", nullable = false)
	private int commissionPrice;

	@Column(name = "commission_paid_time", nullable = false)
	private LocalDateTime commissionPaidTime;

	@Column(name = "commission_meeting_start_time", nullable = false)
	private LocalDateTime commissionMeetingStartTime;

	@Column(name = "commission_meeting_end_time", nullable = false)
	private LocalDateTime commissionMeetingEndTime;

	@Column(name = "commission_end_time", nullable = false)
	private LocalDateTime commissionEndTime;

	@Lob
	@Column(name = "commission_requirement")
	private String commissionRequirement;

	@Enumerated(EnumType.STRING)
	@Column(name = "commission_status", nullable = false, length = 10)
	private CommissionStatus commissionStatus;

	@Column(name = "commission_rating", nullable = false)
	private byte commissionRating;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_seq", nullable = false)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "artist_seq", nullable = false)
	private Artist artist;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "artist_commission_info_seq", nullable = false)
	private ArtistCommissionInfo artistCommissionInfo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getCommissionRequestTime() {
		return commissionRequestTime;
	}

	public void setCommissionRequestTime(LocalDateTime commissionRequestTime) {
		this.commissionRequestTime = commissionRequestTime;
	}

	public LocalDateTime getCommissionAcceptedTime() {
		return commissionAcceptedTime;
	}

	public void setCommissionAcceptedTime(LocalDateTime commissionAcceptedTime) {
		this.commissionAcceptedTime = commissionAcceptedTime;
	}

	public int getCommissionPrice() {
		return commissionPrice;
	}

	public void setCommissionPrice(int commissionPrice) {
		this.commissionPrice = commissionPrice;
	}

	public LocalDateTime getCommissionPaidTime() {
		return commissionPaidTime;
	}

	public void setCommissionPaidTime(LocalDateTime commissionPaidTime) {
		this.commissionPaidTime = commissionPaidTime;
	}

	public LocalDateTime getCommissionMeetingStartTime() {
		return commissionMeetingStartTime;
	}

	public void setCommissionMeetingStartTime(LocalDateTime commissionMeetingStartTime) {
		this.commissionMeetingStartTime = commissionMeetingStartTime;
	}

	public LocalDateTime getCommissionMeetingEndTime() {
		return commissionMeetingEndTime;
	}

	public void setCommissionMeetingEndTime(LocalDateTime commissionMeetingEndTime) {
		this.commissionMeetingEndTime = commissionMeetingEndTime;
	}

	public LocalDateTime getCommissionEndTime() {
		return commissionEndTime;
	}

	public void setCommissionEndTime(LocalDateTime commissionEndTime) {
		this.commissionEndTime = commissionEndTime;
	}

	public String getCommissionRequirement() {
		return commissionRequirement;
	}

	public void setCommissionRequirement(String commissionRequirement) {
		this.commissionRequirement = commissionRequirement;
	}

	public CommissionStatus getCommissionStatus() {
		return commissionStatus;
	}

	public void setCommissionStatus(CommissionStatus commissionStatus) {
		this.commissionStatus = commissionStatus;
	}

	public byte getCommissionRating() {
		return commissionRating;
	}

	public void setCommissionRating(byte commissionRating) {
		this.commissionRating = commissionRating;
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

	public ArtistCommissionInfo getArtistCommissionInfo() {
		return artistCommissionInfo;
	}

	public void setArtistCommissionInfo(ArtistCommissionInfo artistCommissionInfo) {
		this.artistCommissionInfo = artistCommissionInfo;
	}

}