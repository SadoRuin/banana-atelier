package com.ssafy.banana.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "commission_category")
public class CommissionCategory {
	@Id
	@Column(name = "commission_category_seq", nullable = false)
	private Long id;

	@Column(name = "commission_category_name", nullable = false, length = 100)
	private String commissionCategoryName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCommissionCategoryName() {
		return commissionCategoryName;
	}

	public void setCommissionCategoryName(String commissionCategoryName) {
		this.commissionCategoryName = commissionCategoryName;
	}

}