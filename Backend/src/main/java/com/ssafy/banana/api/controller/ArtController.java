package com.ssafy.banana.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.banana.api.service.ArtService;
import com.ssafy.banana.db.entity.Art;
import com.ssafy.banana.db.entity.MyArt;
import com.ssafy.banana.dto.request.ArtRequest;
import com.ssafy.banana.dto.request.MasterpieceRequest;
import com.ssafy.banana.dto.request.MyArtRequest;
import com.ssafy.banana.dto.response.ArtDetailResponse;
import com.ssafy.banana.dto.response.ArtResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@Api(tags = "작품관련 API")
@RequestMapping("/api/arts")
@RequiredArgsConstructor
public class ArtController {

	private static final Long TEST_USER_SEQ = 60L;

	private final ArtService artService;

	@ApiOperation(value = "작품 업로드", notes = "나의 작품을 업로드합니다")
	@PostMapping
	public ResponseEntity uploadArt(@RequestBody ArtRequest artRequest) {

		Long userSeq = TEST_USER_SEQ;    // 수정 예정 (token으로 받아오기)
		String artThumbnail = "artThumbnail 구해오기";    // 수정 예정
		Art art = artService.uploadArt(artRequest, userSeq, artThumbnail);

		return ResponseEntity.status(HttpStatus.OK).body(art);
	}

	@ApiOperation(value = "전체 작품 리스트", notes = "전체 작품 목록을 반환합니다")
	@GetMapping
	public ResponseEntity<List<ArtResponse>> getAllArtList() {

		List<ArtResponse> artList = artService.getAllArtList();

		if (artList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(artList);

	}

	@ApiOperation(value = "나의 작품 리스트", notes = "작가 본인의 작품 목록을 반환합니다")
	@GetMapping("/{user_seq}")
	public ResponseEntity<List<ArtResponse>> getMyArtList(@PathVariable("user_seq") Long userSeq) {

		List<ArtResponse> artList = artService.getMyArtList(userSeq);

		if (artList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(artList);

	}

	//    // 보류
	//    @ApiOperation(value = "대표 작품 리스트", notes = "작가 본인의 대표작 목록을 반환합니다")
	//    @GetMapping("/{user_seq}/masterpiece")
	//    public ResponseEntity getMasterpieceList(@PathVariable("user_seq") Long userSeq){
	//
	//        return null;
	//    }

	@ApiOperation(value = "좋아요한 작품 리스트", notes = "유저가 좋아요를 누른 작품 목록을 반환합니다")
	@GetMapping("/{user_seq}/like")
	public ResponseEntity<List<ArtResponse>> getLikedArtList(@PathVariable("user_seq") Long userSeq) {

		List<ArtResponse> artList = artService.getLikedArtList(userSeq);
		if (artList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}

		return ResponseEntity.status(HttpStatus.OK).body(artList);
	}

	@ApiOperation(value = "대표 작품 설정", notes = "작가 본인의 대표작을 설정합니다")
	@PutMapping("/masterpiece")
	public ResponseEntity<?> setMasterpieceList(@RequestBody List<MasterpieceRequest> masterpieceRequestList) {

		artService.setMasterpieceList(masterpieceRequestList);

		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

	@ApiOperation(value = "카테고리별 작품 리스트", notes = "카테고리별 작품 목록을 반환합니다")
	@GetMapping("/category/{art_category_seq}")
	public ResponseEntity<List<ArtResponse>> getArtListbyCategory(
		@PathVariable("art_category_seq") Long artCategorySeq) {

		List<ArtResponse> artList = artService.getArtListbyCategory(artCategorySeq);

		return ResponseEntity.status(HttpStatus.OK).body(artList);
	}

	// @ApiOperation(value = "트렌딩 작품 리스트", notes = "최근에 좋아요를 많이 받은 작품 목록을 반환합니다")
	// @GetMapping("/trend")
	// public ResponseEntity<List<ArtResponseDto>> getTrendArtList() {
	//
	// 	return null;
	// }
	//
	// @ApiOperation(value = "새로 나온 작품 리스트", notes = "최근에 업로드 된 작품 목록을 반환합니다")
	// @GetMapping("/new")
	// public ResponseEntity<List<ArtResponseDto>> getNewArtList() {
	//
	// 	return null;
	// }

	@ApiOperation(value = "인기 작품 리스트", notes = "좋아요를 많이 받은 작품 목록을 반환합니다")
	@GetMapping("/popular")
	public ResponseEntity<List<ArtResponse>> getPopularArtList() {

		List<ArtResponse> artList = artService.getPopularArtList();

		if (artList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(artList);
	}

	@ApiOperation(value = "작품 상세 정보", notes = "작품의 상세 정보를 반환합니다")
	@GetMapping("/detail/{art_seq}")
	public ResponseEntity getArt(@PathVariable("art_seq") Long artSeq) {

		ArtDetailResponse artDetailResponse = artService.getArt(artSeq);

		return ResponseEntity.status(HttpStatus.OK).body(artDetailResponse);
	}

	@ApiOperation(value = "작품 좋아요 추가하기", notes = "작품에 좋아요를 설정합니다")
	@PostMapping("/like")
	public ResponseEntity addArtLike(@RequestBody MyArtRequest myArtRequest) {

		Long userSeq = TEST_USER_SEQ;    // 수정 예정 (token으로 받아오기)
		MyArt myArt = artService.addArtLike(myArtRequest, userSeq);

		return ResponseEntity.status(HttpStatus.OK).body(myArt);
	}

	// @ApiOperation(value = "작품 다운로드", notes = "작품을 다운로드합니다")
	// @GetMapping("/download/{art_seq}")
	// public ResponseEntity downloadArt(@PathVariable("art_seq") Long artSeq) {
	//
	// 	return null;
	// }

	@ApiOperation(value = "작품 수정", notes = "등록된 작품을 수정합니다")
	@PutMapping
	public ResponseEntity modifyArt(@RequestBody ArtRequest artRequest) {

		Long userSeq = TEST_USER_SEQ;    // 수정 예정 (token으로 받아오기)
		String artThumbnail = "artThumbnail 구해오기";    // 수정 예정
		Art art = artService.updateArt(artRequest, userSeq, artThumbnail);

		return ResponseEntity.status(HttpStatus.OK).body(art);
	}

	@ApiOperation(value = "작품 삭제", notes = "등록된 작품을 삭제합니다")
	@DeleteMapping("art_seq")
	public ResponseEntity deleteArt(@PathVariable("art_seq") Long artSeq) {

		Long userSeq = TEST_USER_SEQ;    // 수정 예정 (token으로 받아오기)
		Long result = artService.deleteArt(artSeq, userSeq);

		if (result == -1L) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

}
