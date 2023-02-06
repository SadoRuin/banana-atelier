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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nimbusds.oauth2.sdk.util.StringUtils;
import com.ssafy.banana.api.service.ArtService;
import com.ssafy.banana.db.entity.Art;
import com.ssafy.banana.dto.request.ArtRequest;
import com.ssafy.banana.dto.request.MasterpieceRequest;
import com.ssafy.banana.dto.request.MyArtRequest;
import com.ssafy.banana.dto.response.ArtDetailResponse;
import com.ssafy.banana.dto.response.ArtResponse;
import com.ssafy.banana.exception.CustomException;
import com.ssafy.banana.exception.CustomExceptionType;
import com.ssafy.banana.security.jwt.TokenProvider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@Api(tags = "작품관련 API")
@RequestMapping("/arts")
@RequiredArgsConstructor
public class ArtController {

	private static final String AUTHORIZATION = "Authorization";
	private final TokenProvider tokenProvider;
	private final ArtService artService;

	@ApiOperation(value = "작품 업로드", notes = "나의 작품을 업로드합니다")
	@PostMapping
	public ResponseEntity uploadArt(@RequestBody ArtRequest artRequest,
		@RequestHeader(AUTHORIZATION) String token) {

		token = getToken(token);
		if (!tokenProvider.validateToken(token)) {
			throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
		}
		Long userSeq = tokenProvider.getSubject(token);
		Art art = artService.uploadArt(artRequest, userSeq);

		return ResponseEntity.status(HttpStatus.OK).body(art);
	}

	@ApiOperation(value = "전체 작품 리스트", notes = "전체 작품 목록을 반환합니다")
	@GetMapping
	public ResponseEntity<List<ArtResponse>> getAllArtList() {

		List<ArtResponse> artList = artService.getAllArtList();

		return ResponseEntity.status(HttpStatus.OK).body(artList);
	}

	@ApiOperation(value = "신규 작품 리스트", notes = "신규 작품 목록을 반환합니다")
	@GetMapping("/new")
	public ResponseEntity<List<ArtResponse>> getNewArtList() {

		List<ArtResponse> artList = artService.getNewArtList();

		return ResponseEntity.status(HttpStatus.OK).body(artList);
	}

	@ApiOperation(value = "나의 작품 리스트", notes = "작가 본인의 작품 목록을 반환합니다")
	@GetMapping("/{user_seq}")
	public ResponseEntity<List<ArtResponse>> getMyArtList(@PathVariable("user_seq") Long userSeq,
		@RequestHeader(AUTHORIZATION) String token) {

		token = getToken(token);
		if (!tokenProvider.validateToken(token)) {
			throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
		}
		Long tokenUserSeq = tokenProvider.getSubject(token);
		List<ArtResponse> artList = artService.getMyArtList(userSeq, tokenUserSeq);

		return ResponseEntity.status(HttpStatus.OK).body(artList);
	}

	@ApiOperation(value = "대표 작품 리스트", notes = "작가 본인의 대표작 목록을 반환합니다")
	@GetMapping("/{user_seq}/masterpiece")
	public ResponseEntity getMasterpieceList(@PathVariable("user_seq") Long userSeq,
		@RequestHeader(AUTHORIZATION) String token) {

		token = getToken(token);
		if (!tokenProvider.validateToken(token)) {
			throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
		}
		Long tokenUserSeq = tokenProvider.getSubject(token);
		List<ArtResponse> artList = artService.getMasterpieceList(userSeq, tokenUserSeq);

		return ResponseEntity.status(HttpStatus.OK).body(artList);
	}

	@ApiOperation(value = "좋아요한 작품 리스트", notes = "유저가 좋아요를 누른 작품 목록을 반환합니다")
	@GetMapping("/{user_seq}/like")
	public ResponseEntity<List<ArtResponse>> getLikedArtList(@PathVariable("user_seq") Long userSeq,
		@RequestHeader(AUTHORIZATION) String token) {

		token = getToken(token);
		if (!tokenProvider.validateToken(token)) {
			throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
		}
		Long tokenUserSeq = tokenProvider.getSubject(token);
		List<ArtResponse> artList = artService.getLikedArtList(userSeq, tokenUserSeq);

		return ResponseEntity.status(HttpStatus.OK).body(artList);
	}

	@ApiOperation(value = "대표 작품 설정", notes = "작가 본인의 대표작을 설정합니다")
	@PutMapping("/masterpiece")
	public ResponseEntity<?> setMasterpieceList(@RequestBody List<MasterpieceRequest> masterpieceRequestList,
		@RequestHeader(AUTHORIZATION) String token) {

		token = getToken(token);
		if (!tokenProvider.validateToken(token)) {
			throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
		}
		Long userSeq = tokenProvider.getSubject(token);
		artService.setMasterpieceList(masterpieceRequestList, userSeq);

		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

	@ApiOperation(value = "카테고리별 작품 리스트", notes = "카테고리별 작품 목록을 반환합니다")
	@GetMapping("/category/{art_category_seq}")
	public ResponseEntity<List<ArtResponse>> getArtListbyCategory(
		@PathVariable("art_category_seq") Long artCategorySeq) {

		List<ArtResponse> artList = artService.getArtListbyCategory(artCategorySeq);

		return ResponseEntity.status(HttpStatus.OK).body(artList);
	}

	@ApiOperation(value = "트렌딩 작품 리스트", notes = "최근 2주 동안에 좋아요를 많이 받은 작품 목록을 반환합니다")
	@GetMapping("/trend")
	public ResponseEntity<List<ArtResponse>> getTrendArtList() {

		List<ArtResponse> artList = artService.getTrendArtList();

		return ResponseEntity.status(HttpStatus.OK).body(artList);
	}

	@ApiOperation(value = "인기 작품 리스트", notes = "좋아요를 많이 받은 작품 목록을 반환합니다")
	@GetMapping("/popular")
	public ResponseEntity<List<ArtResponse>> getPopularArtList() {

		List<ArtResponse> artList = artService.getPopularArtList();

		return ResponseEntity.status(HttpStatus.OK).body(artList);
	}

	@ApiOperation(value = "작품 상세 정보", notes = "작품의 상세 정보를 반환합니다")
	@GetMapping("/detail/{art_seq}")
	// @PreAuthorize("hasRole('USER')")
	public ResponseEntity getArt(@PathVariable("art_seq") Long artSeq,
		@RequestHeader(value = AUTHORIZATION, required = false) String token) {

		if (StringUtils.isBlank(token)) {
			throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
		}
		token = getToken(token);
		if (!tokenProvider.validateToken(token)) {
			throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
		}
		ArtDetailResponse artDetailResponse = artService.getArt(artSeq);

		return ResponseEntity.status(HttpStatus.OK).body(artDetailResponse);
	}

	@ApiOperation(value = "작품 좋아요 추가하기", notes = "작품에 좋아요를 설정합니다")
	@PostMapping("/like")
	public ResponseEntity addArtLike(@RequestBody MyArtRequest myArtRequest,
		@RequestHeader(AUTHORIZATION) String token) {

		token = getToken(token);
		if (!tokenProvider.validateToken(token)) {
			throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
		}
		Long userSeq = tokenProvider.getSubject(token);
		Art art = artService.addArtLike(myArtRequest, userSeq);

		return ResponseEntity.status(HttpStatus.OK).body(art);
	}

	@ApiOperation(value = "작품 좋아요 삭제하기", notes = "작품에 좋아요를 취소합니다")
	@DeleteMapping("/like")
	public ResponseEntity deleteArtLike(@RequestBody MyArtRequest myArtRequest,
		@RequestHeader(AUTHORIZATION) String token) {

		token = getToken(token);
		if (!tokenProvider.validateToken(token)) {
			throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
		}
		Long userSeq = tokenProvider.getSubject(token);
		Art art = artService.deleteArtLike(myArtRequest, userSeq);

		return ResponseEntity.status(HttpStatus.OK).body(art);
	}

	// @ApiOperation(value = "작품 다운로드", notes = "작품을 다운로드합니다")
	// @GetMapping("/download/{art_seq}")
	// public ResponseEntity downloadArt(@PathVariable("art_seq") Long artSeq) {
	//
	// 	return null;
	// }

	@ApiOperation(value = "작품 수정", notes = "등록된 작품을 수정합니다")
	@PutMapping
	public ResponseEntity updateArt(@RequestBody ArtRequest artRequest,
		@RequestHeader(AUTHORIZATION) String token) {

		token = getToken(token);
		if (!tokenProvider.validateToken(token)) {
			throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
		}
		Long userSeq = tokenProvider.getSubject(token);
		Art art = artService.updateArt(artRequest, userSeq);

		return ResponseEntity.status(HttpStatus.OK).body(art);
	}

	@ApiOperation(value = "작품 삭제", notes = "등록된 작품을 삭제합니다")
	@DeleteMapping("/{art_seq}")
	public ResponseEntity deleteArt(@PathVariable("art_seq") Long artSeq,
		@RequestHeader(AUTHORIZATION) String token) {

		token = getToken(token);
		if (!tokenProvider.validateToken(token)) {
			throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
		}
		Long userSeq = tokenProvider.getSubject(token);
		Long result = artService.deleteArt(artSeq, userSeq);

		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	private static String getToken(String token) {
		if (token.substring(0, 7).equals("Bearer ")) {
			token = token.substring("Bearer ".length());
		}
		return token;
	}

}
