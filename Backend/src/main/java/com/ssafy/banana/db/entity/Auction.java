package com.ssafy.banana.db.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.ssafy.banana.db.entity.enums.AuctionStatus;

@Entity
@Table(name = "auction")
public class Auction {
	@Id
	@Column(name = "curation_art_seq", nullable = false)
	private Long id;

	@MapsId
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "curation_art_seq", nullable = false)
	private CurationArt curationArt;

	@Column(name = "auction_start_price", nullable = false)
	private int auctionStartPrice;

	@Column(name = "auction_gap", nullable = false)
	private int auctionGap;

	@Column(name = "auction_start_time", nullable = false)
	private LocalDateTime auctionStartTime;

	@Column(name = "auction_end_time", nullable = false)
	private LocalDateTime auctionEndTime;

	@Column(name = "auction_paid_time", nullable = false)
	private LocalDateTime auctionPaidTime;

	@Column(name = "auction_status_time", nullable = false)
	private LocalDateTime auctionStatusTime;

	@Column(name = "auction_end_price", nullable = false)
	private int auctionEndPrice;

	@Enumerated(EnumType.STRING)
	@Column(name = "auction_status", nullable = false, length = 10)
	private AuctionStatus auctionStatus;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_seq", nullable = false)
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CurationArt getCurationArt() {
		return curationArt;
	}

	public void setCurationArt(CurationArt curationArt) {
		this.curationArt = curationArt;
	}

	public int getAuctionStartPrice() {
		return auctionStartPrice;
	}

	public void setAuctionStartPrice(int auctionStartPrice) {
		this.auctionStartPrice = auctionStartPrice;
	}

	public int getAuctionGap() {
		return auctionGap;
	}

	public void setAuctionGap(int auctionGap) {
		this.auctionGap = auctionGap;
	}

	public LocalDateTime getAuctionStartTime() {
		return auctionStartTime;
	}

	public void setAuctionStartTime(LocalDateTime auctionStartTime) {
		this.auctionStartTime = auctionStartTime;
	}

	public LocalDateTime getAuctionEndTime() {
		return auctionEndTime;
	}

	public void setAuctionEndTime(LocalDateTime auctionEndTime) {
		this.auctionEndTime = auctionEndTime;
	}

	public LocalDateTime getAuctionPaidTime() {
		return auctionPaidTime;
	}

	public void setAuctionPaidTime(LocalDateTime auctionPaidTime) {
		this.auctionPaidTime = auctionPaidTime;
	}

	public LocalDateTime getAuctionStatusTime() {
		return auctionStatusTime;
	}

	public void setAuctionStatusTime(LocalDateTime auctionStatusTime) {
		this.auctionStatusTime = auctionStatusTime;
	}

	public int getAuctionEndPrice() {
		return auctionEndPrice;
	}

	public void setAuctionEndPrice(int auctionEndPrice) {
		this.auctionEndPrice = auctionEndPrice;
	}

	public AuctionStatus getAuctionStatus() {
		return auctionStatus;
	}

	public void setAuctionStatus(AuctionStatus auctionStatus) {
		this.auctionStatus = auctionStatus;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}