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
@Table(name = "auction_bid_log")
public class AuctionBidLog {
	@Id
	@Column(name = "auction_bid_log", nullable = false)
	private Long id;

	@Column(name = "auction_bid_price", nullable = false)
	private int auctionBidPrice;

	@Column(name = "auction_bid_time", nullable = false)
	private LocalDateTime auctionBidTime;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_seq", nullable = false)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "curation_art_seq", nullable = false)
	private Auction curationArt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getAuctionBidPrice() {
		return auctionBidPrice;
	}

	public void setAuctionBidPrice(int auctionBidPrice) {
		this.auctionBidPrice = auctionBidPrice;
	}

	public LocalDateTime getAuctionBidTime() {
		return auctionBidTime;
	}

	public void setAuctionBidTime(LocalDateTime auctionBidTime) {
		this.auctionBidTime = auctionBidTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Auction getCurationArt() {
		return curationArt;
	}

	public void setCurationArt(Auction curationArt) {
		this.curationArt = curationArt;
	}

}