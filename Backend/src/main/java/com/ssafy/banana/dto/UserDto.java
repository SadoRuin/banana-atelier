package com.ssafy.banana.dto;

import java.io.Serializable;

import com.ssafy.banana.db.entity.User;
import com.ssafy.banana.db.entity.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * A DTO for the {@link com.ssafy.banana.db.entity.User} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class UserDto implements Serializable {
	private String email;
	private String nickname;
	private String profileImg;
	private int artistLikeCount;
	private Role role;

	public static UserDto from(User user) {
		if (user == null)
			return null;

		return UserDto.builder()
			.email(user.getEmail())
			.nickname(user.getNickname())
			.profileImg(user.getProfileImg())
			.artistLikeCount(user.getArtistLikeCount())
			.role(user.getRole())
			.build();
	}
}
