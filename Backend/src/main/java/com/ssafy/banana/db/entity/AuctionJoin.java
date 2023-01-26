package com.ssafy.banana.db.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "auction_join")
public class AuctionJoin {
	@EmbeddedId
	private AuctionJoinId id;

	@MapsId("userSeq")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_seq", nullable = false)
	private User user;

	@MapsId("curationArtSeq")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "curation_art_seq", nullable = false)
	private CurationArt curationArt;

	@Column(name = "auction_join_time", nullable = false)
	private LocalDateTime auctionJoinTime;

	public AuctionJoinId getId() {
		return id;
	}

	public void setId(AuctionJoinId id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public CurationArt getCurationArt() {
		return curationArt;
	}

	public void setCurationArt(CurationArt curationArt) {
		this.curationArt = curationArt;
	}

	public LocalDateTime getAuctionJoinTime() {
		return auctionJoinTime;
	}

	public void setAuctionJoinTime(LocalDateTime auctionJoinTime) {
		this.auctionJoinTime = auctionJoinTime;
	}

}