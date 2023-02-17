package com.ssafy.banana.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.banana.db.entity.Art;
import com.ssafy.banana.db.entity.ArtCategory;
import com.ssafy.banana.db.entity.User;

import io.swagger.annotations.ApiModelProperty;
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

	@ApiModelProperty(name = "작품 번호", example = "123")
	@JsonProperty(value = "artSeq")
	private Long id;

	@ApiModelProperty(name = "작품 제목", example = "작품제목")
	private String artName;

	//    private String artDescription;

	//    private LocalDateTime artRegDate;

	@ApiModelProperty(name = "작품 조회수", example = "123")
	private int artHit;

	@ApiModelProperty(name = "작품 좋아요수", example = "123")
	private int artLikeCount;

	@ApiModelProperty(name = "작품 썸네일 파일명", example = "thumb_49d4ec90-a582-4271-a609-6ceaba1c5c83.png")
	private String artThumbnail;

	@ApiModelProperty(name = "작품 이미지 파일명", example = "49d4ec90-a582-4271-a609-6ceaba1c5c83.png")
	private String artImg;

	//    private boolean isDigital;

	@ApiModelProperty(name = "작품 판매 상태", example = "true: 판매됨, false: 기본")
	@JsonProperty(value = "isSold")
	private boolean isSold;

	@ApiModelProperty(name = "대표 작품 여부", example = "true: 대표 작품 설정됨, false: 기본")
	@JsonProperty(value = "isRepresent")
	private boolean isRepresent;

	@ApiModelProperty(name = "작품 카테고리", example = "{ id: 1, artCategoryName: 일러스트레이션 }")
	private ArtCategory artCategory;

	@ApiModelProperty(name = "작가 번호", example = "123")
	@JsonProperty(value = "userSeq")
	private Long artistSeq;

	@ApiModelProperty(name = "작가 프로필 이미지 파일명", example = "default_profile_4.png")
	private String profileImg;

	@ApiModelProperty(name = "작가 닉네임", example = "닉네임")
	@JsonProperty(value = "nickname")
	private String artistNickname;

	@ApiModelProperty(name = "작가 팔로워 수", example = "123")
	private int artistLikeCount;

	public ArtResponse(Art art, User user) {
		this.id = art.getId();
		this.artName = art.getArtName();
		this.artHit = art.getArtHit();
		this.artLikeCount = art.getArtLikeCount();
		this.artThumbnail = art.getArtThumbnail();
		this.artImg = art.getArtImg();
		this.isSold = art.isSold();
		this.isRepresent = art.isRepresent();

		Long artCategorySeq = art.getArtCategory().getId();
		String artCategoryName = art.getArtCategory().getArtCategoryName();
		this.artCategory = ArtCategory.builder()
			.id(artCategorySeq)
			.artCategoryName(artCategoryName)
			.build();
		
		this.artistSeq = art.getArtist().getId();
		this.profileImg = art.getArtist().getUser().getProfileImg();
		this.artistNickname = art.getArtist().getUser().getNickname();
		this.artistLikeCount = art.getArtist().getUser().getArtistLikeCount();
	}
}
