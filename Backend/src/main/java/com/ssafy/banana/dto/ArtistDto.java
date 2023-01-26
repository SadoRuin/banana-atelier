package com.ssafy.banana.dto;

import java.io.Serializable;

import com.ssafy.banana.db.entity.Artist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * A DTO for the {@link Artist} entity
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class ArtistDto implements Serializable {
	private Long id;
	private UserDto user;
	private String instagramLink;
	private String twitterLink;
	private String blogLink;
	private String artistIntro;
}