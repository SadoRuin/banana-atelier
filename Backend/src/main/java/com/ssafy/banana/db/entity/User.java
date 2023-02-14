package com.ssafy.banana.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ssafy.banana.db.entity.enums.Role;

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
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_seq", nullable = false)
	private Long id;

	@Size(max = 50)
	@NotNull
	@Column(name = "email", nullable = false, length = 50)
	private String email;

	@Size(max = 100)
	@NotNull
	@Column(name = "password", nullable = false, length = 100)
	private String password;

	@Size(max = 12)
	@NotNull
	@Column(name = "nickname", nullable = false, length = 12)
	private String nickname;

	@Size(max = 100)
	@NotNull
	@Column(name = "profile_img", nullable = false, length = 100)
	private String profileImg;

	@NotNull
	@Column(name = "artist_like_count", nullable = false)
	private int artistLikeCount;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false, length = 10)
	private Role role;

}