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

import com.ssafy.banana.api.service.CurationService;
import com.ssafy.banana.db.entity.Art;
import com.ssafy.banana.db.repository.CurationArtRepository;
import com.ssafy.banana.dto.request.CurationRequest;
import com.ssafy.banana.dto.request.MyArtRequest;
import com.ssafy.banana.dto.response.CurationArtDataResponse;
import com.ssafy.banana.dto.response.CurationDataResponse;
import com.ssafy.banana.exception.CustomException;
import com.ssafy.banana.exception.CustomExceptionType;
import com.ssafy.banana.security.jwt.TokenProvider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@Api(tags = "큐레이션 관련 API")
@RequestMapping("/curations")
@RequiredArgsConstructor
public class CurationController {

	private final CurationService curationService;

	private static final String AUTHORIZATION = "Authorization";
	private final TokenProvider tokenProvider;
	private final CurationArtRepository curationArtService;

	@GetMapping("/main")
	@ApiOperation(value = "큐레이션 리스트")
	public ResponseEntity<List<CurationDataResponse.CurationSimple>> getList() {
		List<CurationDataResponse.CurationSimple> curationList =  curationService.getCurationList();
		return ResponseEntity.status(HttpStatus.OK).body(curationList);
	}

	@GetMapping("/details/{curation_seq}")
	@ApiOperation(value = "큐레이션 디테일 조회")
	public ResponseEntity<CurationDataResponse.Curation> getCuration(@PathVariable("curation_seq") long curation_seq){
		return ResponseEntity.status(HttpStatus.OK).body(curationService.getCuration(curation_seq));
	}
	//
	// @ApiOperation(value = "작품 좋아요 추가하기", notes = "작품에 좋아요를 설정합니다")
	// @PostMapping("/like")
	// public ResponseEntity addArtLike(@RequestBody MyArtRequest myArtRequest,
	// 	@RequestHeader(AUTHORIZATION) String token) {
	//
	// 	token = getToken(token);
	// 	if (!tokenProvider.validateToken(token)) {
	// 		throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
	// 	}
	// 	Long userSeq = tokenProvider.getSubject(token);
	// 	Art art = artService.addArtLike(myArtRequest, userSeq);
	//
	// 	return ResponseEntity.status(HttpStatus.OK).body(art);
	// }
	//
	// @ApiOperation(value = "작품 좋아요 삭제하기", notes = "작품에 좋아요를 취소합니다")
	// @DeleteMapping("/like")
	// public ResponseEntity deleteArtLike(@RequestBody MyArtRequest myArtRequest,
	// 	@RequestHeader(AUTHORIZATION) String token) {
	//
	// 	token = getToken(token);
	// 	if (!tokenProvider.validateToken(token)) {
	// 		throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
	// 	}
	// 	Long userSeq = tokenProvider.getSubject(token);
	// 	Art art = artService.deleteArtLike(myArtRequest, userSeq);
	//
	// 	return ResponseEntity.status(HttpStatus.OK).body(art);
	// }


	@PostMapping
	@ApiOperation(value = "큐레이션 등록")
	public ResponseEntity<?> registerCuration(@RequestBody CurationRequest curationRequest, @RequestHeader String Authorization){
		String token = Authorization.split(" ")[1];
		if (!tokenProvider.validateToken(token)) {
			throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
		}
		curationService.registerCuration(curationRequest);
		return ResponseEntity.status(HttpStatus.OK).body("큐레이션 등록 성공");
	}



	@PutMapping("/{curation_seq}")
	@ApiOperation(value = "큐레이션 수정")
	public ResponseEntity<?> updateCuration(@PathVariable long curation_seq, @RequestBody CurationRequest curationRequest, @RequestHeader String Authorization){
		String token = Authorization.split(" ")[1];
		if (!tokenProvider.validateToken(token)) {
			throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
		}
		curationService.updateCuration(curation_seq, curationRequest);
		return ResponseEntity.status(HttpStatus.OK).body("큐레이션 수정 성공");
	}

	@DeleteMapping("/{curation_seq}")
	@ApiOperation(value = "큐레이션 삭제")
	public ResponseEntity<?> deleteCuration(@PathVariable long curation_seq, @RequestHeader String Authorization){
		String token = Authorization.split(" ")[1];
		if (!tokenProvider.validateToken(token)) {
			throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
		}
		curationService.deleteCuration(curation_seq);
		return ResponseEntity.status(HttpStatus.OK).body("큐레이션 삭제 성공");
	}

}