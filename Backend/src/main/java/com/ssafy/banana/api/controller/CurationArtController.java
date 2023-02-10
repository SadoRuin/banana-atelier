package com.ssafy.banana.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.banana.api.service.CurationArtService;
import com.ssafy.banana.dto.response.CurationArtDataResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@Api(tags = "큐레이션 작품 관련 API")
@RequestMapping("/curation-art")
@RequiredArgsConstructor
public class CurationArtController {

	private final CurationArtService curationArtService;

	@GetMapping("/list/{curation_seq}")
	@ApiOperation(value = "큐레이션 디테일 작품 리스트")
	public ResponseEntity<List<CurationArtDataResponse>> getList(@PathVariable("curation_seq") long curation_seq) {
		return ResponseEntity.status(HttpStatus.OK).body(curationArtService.getCurationArtList(curation_seq));
	}

}
