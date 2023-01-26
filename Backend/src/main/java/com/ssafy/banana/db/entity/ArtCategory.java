package com.ssafy.banana.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "art_category")
public class ArtCategory {
	@Id
	@Column(name = "art_category_seq", nullable = false)
	private Long id;

	@Column(name = "art_category_name", nullable = false, length = 100)
	private String artCategoryName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getArtCategoryName() {
		return artCategoryName;
	}

	public void setArtCategoryName(String artCategoryName) {
		this.artCategoryName = artCategoryName;
	}

}