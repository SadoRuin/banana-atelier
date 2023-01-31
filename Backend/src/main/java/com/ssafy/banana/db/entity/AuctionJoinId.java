package com.ssafy.banana.db.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import org.hibernate.Hibernate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class AuctionJoinId implements Serializable {
	private static final long serialVersionUID = -2232878839612329324L;
	@NotNull
	@Column(name = "user_seq", nullable = false)
	private Long userSeq;

	@NotNull
	@Column(name = "curation_art_seq", nullable = false)
	private Long curationArtSeq;

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