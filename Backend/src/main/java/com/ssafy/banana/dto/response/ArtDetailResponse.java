package com.ssafy.banana.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.banana.db.entity.Art;
import com.ssafy.banana.db.entity.ArtCategory;
import com.ssafy.banana.db.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Accessors(chain = true)
public class ArtDetailResponse {

	@JsonProperty(value = "art_seq")
	private Long id;

	@JsonProperty(value = "art_name")
	private String artName;

	@JsonProperty(value = "art_description")
	private String artDescription;

	@JsonProperty(value = "art_reg_date")
	private LocalDateTime artRegDate;

	@JsonProperty(value = "art_hit")
	private int artHit;

	@JsonProperty(value = "art_like_count")
	private int artLikeCount;

	@JsonProperty(value = "art_thumbnail")
	private String artThumbnail;

	@JsonProperty(value = "art_img")
	private String artImg;

	//    private boolean isDigital;

	//    private boolean isSold;

	//    private boolean isRepresent;

	@JsonProperty(value = "art_category")
	private ArtCategory artCategory;

	@JsonProperty(value = "nickname")
	private String artistNickname;

	@JsonProperty(value = "artist_like_count")
	private int artistLikeCount;

	public ArtDetailResponse(Art art, User user) {
		this.id = art.getId();
		this.artName = art.getArtName();
		this.artDescription = art.getArtDescription();
		this.artRegDate = art.getArtRegDate();
		this.artHit = art.getArtHit();
		this.artLikeCount = art.getArtLikeCount();
		this.artThumbnail = art.getArtThumbnail();
		this.artImg = art.getArtImg();

		Long artCategorySeq = art.getArtCategory().getId();
		String artCategoryName = art.getArtCategory().getArtCategoryName();
		this.artCategory = ArtCategory.builder()
			.id(artCategorySeq)
			.artCategoryName(artCategoryName)
			.build();

		this.artistNickname = user.getNickname();
		this.artistLikeCount = user.getArtistLikeCount();
	}
}
