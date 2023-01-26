package com.ssafy.banana.db.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "commission_comment")
public class CommissionComment {
	@Id
	@Column(name = "commission_seq", nullable = false)
	private Long id;

	@MapsId
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "commission_seq", nullable = false)
	private Commission commission;

	@Lob
	@Column(name = "commission_comment_content")
	private String commissionCommentContent;

	@Column(name = "commission_comment_time", nullable = false)
	private LocalDateTime commissionCommentTime;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_seq", nullable = false)
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Commission getCommission() {
		return commission;
	}

	public void setCommission(Commission commission) {
		this.commission = commission;
	}

	public String getCommissionCommentContent() {
		return commissionCommentContent;
	}

	public void setCommissionCommentContent(String commissionCommentContent) {
		this.commissionCommentContent = commissionCommentContent;
	}

	public LocalDateTime getCommissionCommentTime() {
		return commissionCommentTime;
	}

	public void setCommissionCommentTime(LocalDateTime commissionCommentTime) {
		this.commissionCommentTime = commissionCommentTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}