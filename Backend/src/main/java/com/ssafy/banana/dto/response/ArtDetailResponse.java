package com.ssafy.banana.dto.response;

import java.time.LocalDateTime;

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
public class ArtDetailResponse {

	@ApiModelProperty(name = "작품 번호", example = "123")
	@JsonProperty(value = "artSeq")
	private Long id;

	@ApiModelProperty(name = "작품 제목", example = "작품제목")
	private String artName;

	@ApiModelProperty(name = "작품 설명", example = "설명입니다")
	private String artDescription;

	// @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	// @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")

	@ApiModelProperty(name = "작품 등록 일시", example = "[2023, 2, 13, 17, 50, 25]")
	private LocalDateTime artRegDate;

	@ApiModelProperty(name = "작품 조회수", example = "123")
	private int artHit;

	@ApiModelProperty(name = "작품 다운로드수", example = "123")
	private int artDownloadCount;

	@ApiModelProperty(name = "작품 좋아요수", example = "123")
	private int artLikeCount;

	@ApiModelProperty(name = "작품 썸네일 파일명", example = "thumb_49d4ec90-a582-4271-a609-6ceaba1c5c83.png")
	private String artThumbnail;

	@ApiModelProperty(name = "작품 이미지 파일명", example = "49d4ec90-a582-4271-a609-6ceaba1c5c83.png")
	private String artImg;

	//    private boolean isDigital;

	//    private boolean isSold;

	//    private boolean isRepresent;

	@ApiModelProperty(name = "작품 카테고리", example = "{ id: 1, artCategoryName: 일러스트레이션 }")
	private ArtCategory artCategory;

	@ApiModelProperty(name = "작가 번호", example = "123")
	private Long userSeq;

	@ApiModelProperty(name = "작가 프로필 이미지 파일명", example = "default_profile_4.png")
	private String profileImg;

	@ApiModelProperty(name = "작가 닉네임", example = "닉네임")
	@JsonProperty(value = "nickname")
	private String artistNickname;

	@ApiModelProperty(name = "작가 팔로워 수", example = "123")
	private int artistLikeCount;

	public ArtDetailResponse(Art art, User user) {
		this.id = art.getId();
		this.artName = art.getArtName();
		this.artDescription = art.getArtDescription();
		this.artRegDate = art.getArtRegDate();
		this.artHit = art.getArtHit();
		this.artDownloadCount = art.getArtDownloadCount();
		this.artLikeCount = art.getArtLikeCount();
		this.artThumbnail = art.getArtThumbnail();
		this.artImg = art.getArtImg();

		Long artCategorySeq = art.getArtCategory().getId();
		String artCategoryName = art.getArtCategory().getArtCategoryName();
		this.artCategory = ArtCategory.builder()
			.id(artCategorySeq)
			.artCategoryName(artCategoryName)
			.build();

		this.userSeq = user.getId();
		this.profileImg = user.getProfileImg();
		this.artistNickname = user.getNickname();
		this.artistLikeCount = user.getArtistLikeCount();
	}
}
