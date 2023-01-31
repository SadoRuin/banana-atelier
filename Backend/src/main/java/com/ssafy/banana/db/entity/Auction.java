package com.ssafy.banana.db.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.ssafy.banana.db.entity.enums.AuctionStatus;

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
@Table(name = "auction")
public class Auction {
	@Id
	@Column(name = "curation_art_seq", nullable = false)
	private Long id;

	@MapsId
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "curation_art_seq", nullable = false)
	private CurationArt curationArt;

	@NotNull
	@Column(name = "auction_start_price", nullable = false)
	private int auctionStartPrice;

	@NotNull
	@Column(name = "auction_gap", nullable = false)
	private int auctionGap;

	@NotNull
	@Column(name = "auction_start_time", nullable = false)
	private LocalDateTime auctionStartTime;

	@NotNull
	@Column(name = "auction_end_time", nullable = false)
	private LocalDateTime auctionEndTime;

	@NotNull
	@Column(name = "auction_paid_time", nullable = false)
	private LocalDateTime auctionPaidTime;

	@NotNull
	@Column(name = "auction_status_time", nullable = false)
	private LocalDateTime auctionStatusTime;

	@NotNull
	@Column(name = "auction_end_price", nullable = false)
	private int auctionEndPrice;

	@Size(max = 10)
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "auction_status", nullable = false, length = 10)
	private AuctionStatus auctionStatus;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "user_seq", nullable = false)
	private User user;

}