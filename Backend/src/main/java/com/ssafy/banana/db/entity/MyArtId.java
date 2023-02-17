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
public class MyArtId implements Serializable {
	private static final long serialVersionUID = -3349915661325168656L;
	@NotNull
	@Column(name = "user_seq", nullable = false)
	private Long userSeq;

	@NotNull
	@Column(name = "art_seq", nullable = false)
	private Long artSeq;

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