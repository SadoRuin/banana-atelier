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
@Table(name = "art")
public class Art {
	@Id
	@Column(name = "art_seq", nullable = false)
	private Long id;

	@Column(name = "art_name", nullable = false, length = 100)
	private String artName;

	@Column(name = "art_description", nullable = false, length = 500)
	private String artDescription;

	@Column(name = "art_reg_date", nullable = false)
	private LocalDateTime artRegDate;

	@Column(name = "art_hit", nullable = false)
	private int artHit;

	@Column(name = "art_like_count", nullable = false)
	private int artLikeCount;

	@Column(name = "art_thumbnail", nullable = false, length = 100)
	private String artThumbnail;

	@Column(name = "art_img", nullable = false, length = 100)
	private String artImg;

	@Column(name = "is_digital", nullable = false)
	private boolean isDigital;

	@Column(name = "is_sold", nullable = false)
	private boolean isSold;

	@Column(name = "is_represent", nullable = false)
	private boolean isRepresent;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "art_category_seq", nullable = false)
	private ArtCategory artCategory;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_seq", nullable = false)
	private Artist artist;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getArtName() {
		return artName;
	}

	public void setArtName(String artName) {
		this.artName = artName;
	}

	public String getArtDescription() {
		return artDescription;
	}

	public void setArtDescription(String artDescription) {
		this.artDescription = artDescription;
	}

	public LocalDateTime getArtRegDate() {
		return artRegDate;
	}

	public void setArtRegDate(LocalDateTime artRegDate) {
		this.artRegDate = artRegDate;
	}

	public int getArtHit() {
		return artHit;
	}

	public void setArtHit(int artHit) {
		this.artHit = artHit;
	}

	public int getArtLikeCount() {
		return artLikeCount;
	}

	public void setArtLikeCount(int artLikeCount) {
		this.artLikeCount = artLikeCount;
	}

	public String getArtThumbnail() {
		return artThumbnail;
	}

	public void setArtThumbnail(String artThumbnail) {
		this.artThumbnail = artThumbnail;
	}

	public String getArtImg() {
		return artImg;
	}

	public void setArtImg(String artImg) {
		this.artImg = artImg;
	}

	public boolean getIsDigital() {
		return isDigital;
	}

	public void setIsDigital(boolean isDigital) {
		this.isDigital = isDigital;
	}

	public boolean getIsSold() {
		return isSold;
	}

	public void setIsSold(boolean isSold) {
		this.isSold = isSold;
	}

	public boolean getIsRepresent() {
		return isRepresent;
	}

	public void setIsRepresent(boolean isRepresent) {
		this.isRepresent = isRepresent;
	}

	public ArtCategory getArtCategory() {
		return artCategory;
	}

	public void setArtCategory(ArtCategory artCategory) {
		this.artCategory = artCategory;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

}