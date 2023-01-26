package com.ssafy.banana.db.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.Hibernate;

@Embeddable
public class AuctionJoinId implements Serializable {
	private static final long serialVersionUID = -6861373055621882603L;
	@Column(name = "user_seq", nullable = false)
	private Long userSeq;

	@Column(name = "curation_art_seq", nullable = false)
	private Long curationArtSeq;

	public Long getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(Long userSeq) {
		this.userSeq = userSeq;
	}

	public Long getCurationArtSeq() {
		return curationArtSeq;
	}

	public void setCurationArtSeq(Long curationArtSeq) {
		this.curationArtSeq = curationArtSeq;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
			return false;
		AuctionJoinId entity = (AuctionJoinId)o;
		return Objects.equals(this.curationArtSeq, entity.curationArtSeq) &&
			Objects.equals(this.userSeq, entity.userSeq);
	}

	@Override
	public int hashCode() {
		return Objects.hash(curationArtSeq, userSeq);
	}

}