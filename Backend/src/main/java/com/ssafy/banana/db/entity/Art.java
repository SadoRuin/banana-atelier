package com.ssafy.banana.db.entity;

import java.time.LocalDateTime;

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
@Table(name = "art")
public class Art {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "art_seq", nullable = false)
	private Long id;

	@Size(max = 100)
	@NotNull
	@Column(name = "art_name", nullable = false, length = 100)
	private String artName;

	@Size(max = 500)
	@NotNull
	@Column(name = "art_description", nullable = false, length = 500)
	private String artDescription;

	@NotNull
	@Column(name = "art_reg_date", nullable = false)
	private LocalDateTime artRegDate;

	@NotNull
	@Column(name = "art_hit", nullable = false)
	private int artHit;

	@NotNull
	@Column(name = "art_like_count", nullable = false)
	private int artLikeCount;

	@Size(max = 100)
	@NotNull
	@Column(name = "art_thumbnail", nullable = false, length = 100)
	private String artThumbnail;

	@Size(max = 100)
	@NotNull
	@Column(name = "art_img", nullable = false, length = 100)
	private String artImg;

	@NotNull
	@Column(name = "is_digital", nullable = false)
	private boolean isDigital;

	@NotNull
	@Column(name = "is_sold", nullable = false)
	private boolean isSold;

	@NotNull
	@Column(name = "is_represent", nullable = false)
	private boolean isRepresent;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "art_category_seq", nullable = false)
	private ArtCategory artCategory;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "user_seq", nullable = false)
	private Artist artist;

}