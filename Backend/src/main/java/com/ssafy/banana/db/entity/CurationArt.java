package com.ssafy.banana.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "curation_art")
public class CurationArt {
	@Id
	@Column(name = "curation_art_seq", nullable = false)
	private Long id;

	@Column(name = "is_auction", nullable = false)
	private boolean isAuction;

	@Column(name = "auction_people_cnt")
	private String auctionPeopleCnt;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "art_seq", nullable = false)
	private Art art;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "curation_seq", nullable = false)
	private Curation curation;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean getIsAuction() {
		return isAuction;
	}

	public void setIsAuction(boolean isAuction) {
		this.isAuction = isAuction;
	}

	public String getAuctionPeopleCnt() {
		return auctionPeopleCnt;
	}

	public void setAuctionPeopleCnt(String auctionPeopleCnt) {
		this.auctionPeopleCnt = auctionPeopleCnt;
	}

	public Art getArt() {
		return art;
	}

	public void setArt(Art art) {
		this.art = art;
	}

	public Curation getCuration() {
		return curation;
	}

	public void setCuration(Curation curation) {
		this.curation = curation;
	}

}