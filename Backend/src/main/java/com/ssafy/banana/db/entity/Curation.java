package com.ssafy.banana.db.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "curation")
public class Curation {
	@Id
	@Column(name = "curation_seq", nullable = false)
	private Long id;

	@Column(name = "curation_start_time", nullable = false)
	private LocalDateTime curationStartTime;

	@Column(name = "curation_end_time", nullable = false)
	private LocalDateTime curationEndTime;

	@Column(name = "curation_name", nullable = false, length = 100)
	private String curationName;

	@Column(name = "curation_summury", length = 1000)
	private String curationSummury;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_seq", nullable = false)
	private Artist artist;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getCurationStartTime() {
		return curationStartTime;
	}

	public void setCurationStartTime(LocalDateTime curationStartTime) {
		this.curationStartTime = curationStartTime;
	}

	public LocalDateTime getCurationEndTime() {
		return curationEndTime;
	}

	public void setCurationEndTime(LocalDateTime curationEndTime) {
		this.curationEndTime = curationEndTime;
	}

	public String getCurationName() {
		return curationName;
	}

	public void setCurationName(String curationName) {
		this.curationName = curationName;
	}

	public String getCurationSummury() {
		return curationSummury;
	}

	public void setCurationSummury(String curationSummury) {
		this.curationSummury = curationSummury;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

}