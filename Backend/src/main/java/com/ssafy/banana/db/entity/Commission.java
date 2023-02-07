package com.ssafy.banana.db.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.ssafy.banana.db.entity.enums.CommissionStatus;

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
@Table(name = "commission")
public class Commission {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "commission_seq", nullable = false)
	private Long id;

	@NotNull
	@Column(name = "commission_request_time", nullable = false)
	private LocalDateTime commissionRequestTime;

	@NotNull
	@Column(name = "commission_accepted_time", nullable = false)
	private LocalDateTime commissionAcceptedTime;

	@NotNull
	@Column(name = "commission_price", nullable = false)
	private int commissionPrice;

	@NotNull
	@Column(name = "commission_paid_time", nullable = false)
	private LocalDateTime commissionPaidTime;

	@NotNull
	@Column(name = "commission_meeting_start_time", nullable = false)
	private LocalDateTime commissionMeetingStartTime;

	@NotNull
	@Column(name = "commission_meeting_end_time", nullable = false)
	private LocalDateTime commissionMeetingEndTime;

	@NotNull
	@Column(name = "commission_end_time", nullable = false)
	private LocalDateTime commissionEndTime;

	@Lob
	@Column(name = "commission_requirement")
	private String commissionRequirement;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "commission_status", nullable = false, length = 10)
	private CommissionStatus commissionStatus;

	@NotNull
	@Column(name = "commission_rating", nullable = false)
	private double commissionRating;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "user_seq", nullable = false)
	private User user;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "artist_seq", nullable = false)
	private Artist artist;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "artist_commission_info_seq", nullable = false)
	private ArtistCommissionInfo artistCommissionInfo;

}