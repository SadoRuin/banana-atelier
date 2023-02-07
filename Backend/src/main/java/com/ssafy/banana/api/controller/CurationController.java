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

import com.ssafy.banana.api.service.CurationArtService;
import com.ssafy.banana.api.service.CurationService;
import com.ssafy.banana.db.entity.CurationArt;
import com.ssafy.banana.dto.request.CurationRequest;
import com.ssafy.banana.dto.response.CurationAllListResponse;
import com.ssafy.banana.dto.response.CurationDetailResponse;
import com.ssafy.banana.dto.response.CurationResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@Api(tags = "큐레이션 관련 API")
@RequestMapping("/curations")
@RequiredArgsConstructor
public class CurationController {

	private final CurationService curationService;
	private final CurationArtService curationArtService;

	@GetMapping
	@ApiOperation(value = "큐레이션 리스트")
	public ResponseEntity<List<CurationAllListResponse>> getList() {
		List<CurationAllListResponse> curationList =  curationService.getCurationList();
		if (curationList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(curationList);
	}

	@PostMapping
	@ApiOperation(value = "큐레이션 등록")
	public ResponseEntity<?> registerCuration(@RequestBody CurationRequest curationRequest){
		curationService.registerCuration(curationRequest);
		return ResponseEntity.status(HttpStatus.OK).body("큐레이션 등록 성공");
	}

	@GetMapping("/{curation_seq}")
	@ApiOperation(value = "큐레이션 디테일 조회")
	public ResponseEntity<CurationDetailResponse> getCuration(@PathVariable long curation_seq){
		CurationResponse curationResponse = curationService.getCuration(curation_seq);
		List<CurationArt> curationArtList = curationArtService.getCurationArtList(curation_seq);
		CurationDetailResponse curationDetailResponse = new CurationDetailResponse(curationResponse,curationArtList );
		return ResponseEntity.status(HttpStatus.OK).body(curationDetailResponse);
	}

	@PutMapping("/{curation_seq}")
	@ApiOperation(value = "큐레이션 수정")
	public ResponseEntity<?> updateCuration(@PathVariable long curation_seq, @RequestBody CurationRequest curationRequest){
		curationService.updateCuration(curation_seq, curationRequest);
		return ResponseEntity.status(HttpStatus.OK).body("큐레이션 수정 성공");
	}

	@DeleteMapping("/{curation_seq}")
	@ApiOperation(value = "큐레이션 삭제")
	public ResponseEntity<?> deleteCuration(@PathVariable long curation_seq){
		curationService.deleteCuration(curation_seq);
		return ResponseEntity.status(HttpStatus.OK).body("큐레이션 삭제 성공");
	}

	// @GetMapping("/details/{curation_seq}")
	// @ApiOperation(value = "큐레이션 작품 정보 리스트")
	// public ResponseEntity<List<CurationArtResponse>> getCurationArtList(@PathVariable long curation_seq) {
	// 	List<CurationArtResponse> curationArtList =  curationService.getCurationArtList(curation_seq);
	// 	if (curationArtList.isEmpty()) {
	// 		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	// 	}
	// 	return ResponseEntity.status(HttpStatus.OK).body(curationArtList);
	// }
}
