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
@Table(name = "curation")
public class Curation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "curation_seq", nullable = false)
	private Long id;

	@NotNull
	@Column(name = "curation_start_time", nullable = false)
	private LocalDateTime curationStartTime;

	@NotNull
	@Column(name = "curation_end_time", nullable = false)
	private LocalDateTime curationEndTime;

	@Size(max = 100)
	@NotNull
	@Column(name = "curation_name", nullable = false, length = 100)
	private String curationName;

	@Size(max = 1000)
	@Column(name = "curation_summary", length = 1000)
	private String curationSummary;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "user_seq", nullable = false)
	private Artist artist;

}