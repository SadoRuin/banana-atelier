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
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.banana.api.service.CurationService;
import com.ssafy.banana.db.entity.Curation;
import com.ssafy.banana.db.repository.CurationArtRepository;
import com.ssafy.banana.dto.request.CurationRequest;
import com.ssafy.banana.dto.request.MyCurationRequest;
import com.ssafy.banana.dto.response.CurationDataResponse;
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
		List<CurationDataResponse.CurationSimple> curationList = curationService.getCurationList();
		return ResponseEntity.status(HttpStatus.OK).body(curationList);
	}

	@GetMapping("/details/{curation_seq}")
	@ApiOperation(value = "큐레이션 디테일 조회")
	public ResponseEntity<CurationDataResponse.Curation> getCuration(@PathVariable("curation_seq") long curation_seq) {
		return ResponseEntity.status(HttpStatus.OK).body(curationService.getCuration(curation_seq));
	}

	@PreAuthorize("hasRole('ARTIST')")
	@PostMapping
	@ApiOperation(value = "큐레이션 등록")
	public ResponseEntity registerCuration(@RequestBody CurationRequest curationRequest,
		@RequestHeader String Authorization) {
		String token = Authorization.split(" ")[1];
		Long userSeq = tokenProvider.getSubject(token);

		curationService.registerCuration(userSeq, curationRequest);

		return ResponseEntity.status(HttpStatus.OK).body("register complete");
	}

	@PreAuthorize("hasRole('ARTIST')")
	@PutMapping("/{curation_seq}")
	@ApiOperation(value = "큐레이션 수정")
	public ResponseEntity updateCuration(@PathVariable long curation_seq,
		@RequestBody CurationRequest curationRequest,
		@RequestHeader String Authorization) {
		String token = Authorization.split(" ")[1];
		Long userSeq = tokenProvider.getSubject(token);

		curationService.updateCuration(userSeq, curationRequest, curation_seq);
		return ResponseEntity.status(HttpStatus.OK).body("update completed");
	}

	@PreAuthorize("hasRole('ARTIST')")
	@DeleteMapping("/{curation_seq}")
	@ApiOperation(value = "큐레이션 삭제")
	public ResponseEntity deleteCuration(@PathVariable long curation_seq, @RequestHeader String Authorization) {
		String token = Authorization.split(" ")[1];
		Long userSeq = tokenProvider.getSubject(token);

		curationService.deleteCuration(userSeq, curation_seq);
		return ResponseEntity.status(HttpStatus.OK).body("delete complete");
	}

	@ApiOperation(value = "북마크 추가하기", notes = "큐레이션에 북마크를 설정합니다")
	@PostMapping("/bookmark")
	public ResponseEntity addCurationBookmark(@RequestBody MyCurationRequest myCurationRequest,
		@RequestHeader String Authorization) {
		String token = Authorization.split(" ")[1];

		Long userSeq = tokenProvider.getSubject(token);
		Curation curation = curationService.addCurationBookmark(myCurationRequest, userSeq);

		return ResponseEntity.status(HttpStatus.OK).body(curation);
	}

	@ApiOperation(value = "큐레이션 북마크 삭제하기", notes = "큐레이션에 북마크를 취소합니다")
	@DeleteMapping("/bookmark")
	public ResponseEntity deleteArtLike(@RequestBody MyCurationRequest myCurationRequest,
		@RequestHeader String Authorization) {
		String token = Authorization.split(" ")[1];
		Long userSeq = tokenProvider.getSubject(token);
		Curation curation = curationService.deleteCurationBookmark(myCurationRequest, userSeq);

		return ResponseEntity.status(HttpStatus.OK).body(curation);
	}

}
