package com.ssafy.banana.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.banana.api.service.ArtService;
import com.ssafy.banana.dto.DownloladFileDto;
import com.ssafy.banana.dto.request.ArtRequest;
import com.ssafy.banana.dto.request.SeqRequest;
import com.ssafy.banana.dto.response.ArtDetailResponse;
import com.ssafy.banana.dto.response.ArtResponse;
import com.ssafy.banana.dto.response.SuccessResponse;
import com.ssafy.banana.security.jwt.TokenProvider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@Api(tags = "작품관련 API")
@RequestMapping("/arts")
@RequiredArgsConstructor
public class ArtController {

	private static final String BLNAK = " ";
	private final TokenProvider tokenProvider;
	private final ArtService artService;

	@ApiOperation(value = "작품 업로드", notes = "나의 작품을 업로드합니다")
	@PostMapping
	public ResponseEntity uploadArt(
		@RequestPart(value = "artFile", required = false) MultipartFile artFile,
		@RequestPart ArtRequest artRequest,
		@RequestHeader String Authorization) {

		String token = Authorization.split(BLNAK)[1];
		artService.uploadArt(artFile, artRequest, token);

		return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse("작품이 업로드되었습니다."));
	}

	@ApiOperation(value = "전체 작품 리스트", notes = "전체 작품 목록을 반환합니다")
	@GetMapping("/all")
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

	@ApiOperation(value = "나의 작품 리스트", notes = "작가의 작품 목록을 반환합니다")
	@GetMapping("/{userSeq}")
	public ResponseEntity<List<ArtResponse>> getMyArtList(
		@PathVariable("userSeq") long userSeq) {

		List<ArtResponse> artList = artService.getMyArtList(userSeq);

		return ResponseEntity.status(HttpStatus.OK).body(artList);
	}

	@ApiOperation(value = "대표 작품 리스트", notes = "작가의 대표작 목록을 반환합니다")
	@GetMapping("/{userSeq}/masterpiece")
	public ResponseEntity getMasterpieceList(
		@PathVariable("userSeq") long artistSeq) {

		List<ArtResponse> artList = artService.getMasterpieceList(artistSeq);

		return ResponseEntity.status(HttpStatus.OK).body(artList);
	}

	@ApiOperation(value = "좋아요한 작품 리스트", notes = "유저가 좋아요를 누른 작품 목록을 반환합니다")
	@GetMapping("/{userSeq}/like")
	public ResponseEntity<List<ArtResponse>> getLikedArtList(
		@PathVariable("userSeq") long userSeq) {

		List<ArtResponse> artList = artService.getLikedArtList(userSeq);

		return ResponseEntity.status(HttpStatus.OK).body(artList);
	}

	@PreAuthorize("hasRole('ARTIST')")
	@ApiOperation(value = "대표 작품 설정", notes = "작가 본인의 대표작을 설정합니다")
	@PutMapping("/masterpiece")
	public ResponseEntity<?> setMasterpieceList(
		@RequestBody List<SeqRequest> artSeqRequestList,
		@RequestHeader String Authorization) {

		String token = Authorization.split(BLNAK)[1];
		long userSeq = tokenProvider.getSubject(token);
		artService.setMasterpieceList(artSeqRequestList, userSeq);

		return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse("대표 작품 설정 완료"));
	}

	@ApiOperation(value = "카테고리별 작품 리스트", notes = "카테고리별 작품 목록을 반환합니다")
	@GetMapping("/category/{artCategorySeq}")
	public ResponseEntity<List<ArtResponse>> getArtListbyCategory(
		@PathVariable long artCategorySeq) {

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
	@GetMapping("/detail/{artSeq}")
	public ResponseEntity getArt(
		@PathVariable long artSeq) {

		ArtDetailResponse artDetailResponse = artService.getArt(artSeq);

		return ResponseEntity.status(HttpStatus.OK).body(artDetailResponse);
	}

	@ApiOperation(value = "작품 좋아요 추가하기", notes = "작품에 좋아요를 설정합니다")
	@PostMapping("/like")
	public ResponseEntity addArtLike(
		@RequestBody SeqRequest seqRequest,
		@RequestHeader String Authorization) {

		String token = Authorization.split(BLNAK)[1];
		long userSeq = tokenProvider.getSubject(token);

		return ResponseEntity.status(HttpStatus.OK).body(artService.addArtLike(seqRequest, userSeq));
	}

	@ApiOperation(value = "작품 좋아요 삭제하기", notes = "작품에 좋아요를 취소합니다")
	@DeleteMapping("/like")
	public ResponseEntity deleteArtLike(
		@RequestBody SeqRequest seqRequest,
		@RequestHeader String Authorization) {

		String token = Authorization.split(BLNAK)[1];
		long userSeq = tokenProvider.getSubject(token);

		return ResponseEntity.status(HttpStatus.OK).body(artService.deleteArtLike(seqRequest, userSeq));
	}

	@ApiOperation(value = "작품 다운로드", notes = "작품을 다운로드합니다")
	@GetMapping("/download/{artSeq}")
	public ResponseEntity downloadArt(@PathVariable long artSeq) {

		DownloladFileDto downloladFileDto = artService.downloadArt(artSeq);

		return ResponseEntity.ok().headers(downloladFileDto.getHttpHeaders()).body(downloladFileDto.getImageFile());
	}

	@PreAuthorize("hasRole('ARTIST')")
	@ApiOperation(value = "작품 수정", notes = "등록된 작품을 수정합니다")
	@PutMapping
	public ResponseEntity updateArt(
		@RequestBody ArtRequest artRequest,
		@RequestHeader String Authorization) {

		String token = Authorization.split(BLNAK)[1];
		long userSeq = tokenProvider.getSubject(token);

		return ResponseEntity.status(HttpStatus.OK).body(artService.updateArt(artRequest, userSeq));
	}

	@PreAuthorize("hasRole('ARTIST')")
	@ApiOperation(value = "작품 삭제", notes = "등록된 작품을 삭제합니다")
	@ApiImplicitParam(name = "seq", value = "작품번호")
	@DeleteMapping("/delete")
	public ResponseEntity deleteArt(
		@RequestBody SeqRequest seqRequest,
		@RequestHeader String Authorization) {

		String token = Authorization.split(BLNAK)[1];
		artService.deleteArt(seqRequest.getSeq(), token);

		return ResponseEntity.ok(new SuccessResponse("작품이 삭제되었습니다."));
	}
}
