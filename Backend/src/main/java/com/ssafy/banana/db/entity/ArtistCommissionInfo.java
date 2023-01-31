package com.ssafy.banana.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
@Table(name = "artist_commission_info")
public class ArtistCommissionInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "artist_commission_info_seq", nullable = false)
	private Long id;

	@Size(max = 100)
	@NotNull
	@Column(name = "commission_title", nullable = false, length = 100)
	private String commissionTitle;

	@Lob
	@Column(name = "commission_description")
	private String commissionDescription;

	@NotNull
	@Column(name = "commission_min_price", nullable = false)
	private int commissionMinPrice;

	@NotNull
	@Column(name = "commission_max_price", nullable = false)
	private int commissionMaxPrice;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "user_seq", nullable = false)
	private Artist artist;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "commission_category_seq", nullable = false)
	private CommissionCategory commissionCategory;

}