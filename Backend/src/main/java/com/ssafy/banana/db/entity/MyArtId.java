package com.ssafy.banana.db.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.Hibernate;

@Embeddable
public class MyArtId implements Serializable {
	private static final long serialVersionUID = -2102078239052971755L;
	@Column(name = "user_seq", nullable = false)
	private Long userSeq;

	@Column(name = "art_seq", nullable = false)
	private Long artSeq;

	public Long getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(Long userSeq) {
		this.userSeq = userSeq;
	}

	public Long getArtSeq() {
		return artSeq;
	}

	public void setArtSeq(Long artSeq) {
		this.artSeq = artSeq;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
			return false;
		MyArtId entity = (MyArtId)o;
		return Objects.equals(this.userSeq, entity.userSeq) &&
			Objects.equals(this.artSeq, entity.artSeq);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userSeq, artSeq);
	}

}