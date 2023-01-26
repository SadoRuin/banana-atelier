package com.ssafy.banana.db.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.Hibernate;

@Embeddable
public class MyArtistId implements Serializable {
	private static final long serialVersionUID = -4570212737085067285L;
	@Column(name = "user_seq", nullable = false)
	private Long userSeq;

	@Column(name = "artist_seq", nullable = false)
	private Long artistSeq;

	public Long getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(Long userSeq) {
		this.userSeq = userSeq;
	}

	public Long getArtistSeq() {
		return artistSeq;
	}

	public void setArtistSeq(Long artistSeq) {
		this.artistSeq = artistSeq;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
			return false;
		MyArtistId entity = (MyArtistId)o;
		return Objects.equals(this.artistSeq, entity.artistSeq) &&
			Objects.equals(this.userSeq, entity.userSeq);
	}

	@Override
	public int hashCode() {
		return Objects.hash(artistSeq, userSeq);
	}

}