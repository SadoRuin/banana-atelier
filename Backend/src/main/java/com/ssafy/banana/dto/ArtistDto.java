package com.ssafy.banana.dto;

import java.io.Serializable;

import com.ssafy.banana.db.entity.Artist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

/**
 * A DTO for the {@link Artist} entity
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Accessors(chain = true)
public class ArtistDto extends UserDto implements Serializable {
	private String instagramLink;
	private String twitterLink;
	private String blogLink;
	private String artistIntro;

	public static ArtistDto from(Artist artist) {
		if (artist == null)
			return null;

		return ArtistDto.builder()
			.email(artist.getUser().getEmail())
			.nickname(artist.getUser().getNickname())
			.profileImg(artist.getUser().getProfileImg())
			.artistLikeCount(artist.getUser().getArtistLikeCount())
			.role(artist.getUser().getRole())
			.instagramLink(artist.getInstagramLink())
			.twitterLink(artist.getTwitterLink())
			.blogLink(artist.getBlogLink())
			.artistIntro(artist.getArtistIntro())
			.build();
	}
}