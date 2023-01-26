package com.ssafy.banana.dto;

import java.io.Serializable;

import com.ssafy.banana.db.entity.User;
import com.ssafy.banana.db.entity.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * A DTO for the {@link User} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class UserDto implements Serializable {
	private Long id;
	private String email;
	private String password;
	private String nickname;
	private String profileImg;
	private int artistLikeCount;
	private Role role;
}