package com.ssafy.banana.db.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "my_artist")
public class MyArtist {
	@EmbeddedId
	private MyArtistId id;

	@MapsId("userSeq")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_seq", nullable = false)
	private User user;

	@MapsId("artistSeq")
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "artist_seq", nullable = false)
	private Artist artist;

	public MyArtistId getId() {
		return id;
	}

	public void setId(MyArtistId id) {
		this.id = id;
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

}