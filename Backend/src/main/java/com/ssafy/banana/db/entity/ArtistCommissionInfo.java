package com.ssafy.banana.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "artist_commission_info")
public class ArtistCommissionInfo {
	@Id
	@Column(name = "artist_commission_info_seq", nullable = false)
	private Long id;

	@Column(name = "commission_title", nullable = false, length = 100)
	private String commissionTitle;

	@Lob
	@Column(name = "commission_description")
	private String commissionDescription;

	@Column(name = "commission_min_price", nullable = false)
	private int commissionMinPrice;

	@Column(name = "commission_max_price", nullable = false)
	private int commissionMaxPrice;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_seq", nullable = false)
	private Artist artist;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "commission_category_seq", nullable = false)
	private CommissionCategory commissionCategory;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCommissionTitle() {
		return commissionTitle;
	}

	public void setCommissionTitle(String commissionTitle) {
		this.commissionTitle = commissionTitle;
	}

	public String getCommissionDescription() {
		return commissionDescription;
	}

	public void setCommissionDescription(String commissionDescription) {
		this.commissionDescription = commissionDescription;
	}

	public int getCommissionMinPrice() {
		return commissionMinPrice;
	}

	public void setCommissionMinPrice(int commissionMinPrice) {
		this.commissionMinPrice = commissionMinPrice;
	}

	public int getCommissionMaxPrice() {
		return commissionMaxPrice;
	}

	public void setCommissionMaxPrice(int commissionMaxPrice) {
		this.commissionMaxPrice = commissionMaxPrice;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public CommissionCategory getCommissionCategory() {
		return commissionCategory;
	}

	public void setCommissionCategory(CommissionCategory commissionCategory) {
		this.commissionCategory = commissionCategory;
	}

}