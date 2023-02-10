package com.ssafy.banana.dto.response;

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
public class ArtResponse {

	@JsonProperty(value = "artSeq")
	private Long id;

	private String artName;

	//    private String artDescription;

	//    private LocalDateTime artRegDate;

	private int artHit;

	private int artLikeCount;

	private String artThumbnail;

	private String artImg;

	//    private boolean isDigital;

	//    private boolean isSold;

	//    private boolean isRepresent;

	private ArtCategory artCategory;

	@JsonProperty(value = "userSeq")
	private Long artistSeq;

	private String profileImg;

	@JsonProperty(value = "nickname")
	private String artistNickname;

	private int artistLikeCount;

	public ArtResponse(Art art, User user) {
		this.id = art.getId();
		this.artName = art.getArtName();
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

		this.artistSeq = user.getId();
		this.profileImg = user.getProfileImg();
		this.artistNickname = user.getNickname();
		this.artistLikeCount = user.getArtistLikeCount();
	}
}
