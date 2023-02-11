package com.ssafy.banana.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
@Entity
@Table(name = "curation_art")
public class CurationArt {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "curation_art_seq", nullable = false)
	private Long id;

	@Column(name = "is_auction", nullable = false)
	private int isAuction;

	@Column(name = "auction_gap", nullable = false)
	private int auctionGap;

	@Column(name = "auction_people_cnt")
	private int auctionPeopleCnt;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "art_seq", nullable = false)
	private Art art;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "curation_seq", nullable = false)
	private Curation curation;

}
