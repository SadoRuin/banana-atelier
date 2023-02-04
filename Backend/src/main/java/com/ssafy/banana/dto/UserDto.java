package com.ssafy.banana.dto;

import java.io.Serializable;

import com.ssafy.banana.db.entity.User;
import com.ssafy.banana.db.entity.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A DTO for the {@link com.ssafy.banana.db.entity.User} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDto implements Serializable {
	private String email;
	private String nickname;
	private int artistLikeCount;
	private Role role;
	private boolean isAuthorized;

	public static UserDto from(User user) {
		if (user == null)
			return null;

		return UserDto.builder()
			.email(user.getEmail())
			.nickname(user.getNickname())
			.artistLikeCount(user.getArtistLikeCount())
			.role(user.getRole())
			.isAuthorized(user.isAuthorized())
			.build();
	}
}