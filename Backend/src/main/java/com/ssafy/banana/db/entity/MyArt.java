package com.ssafy.banana.db.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "my_art")
public class MyArt {
	@EmbeddedId
	private MyArtId id;

	@MapsId("userSeq")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_seq", nullable = false)
	private User user;

	@MapsId("artSeq")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "art_seq", nullable = false)
	private Art art;

	public MyArtId getId() {
		return id;
	}

	public void setId(MyArtId id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Art getArt() {
		return art;
	}

	public void setArt(Art art) {
		this.art = art;
	}

}