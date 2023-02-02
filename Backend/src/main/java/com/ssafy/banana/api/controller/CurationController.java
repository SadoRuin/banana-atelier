package com.ssafy.banana.api.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.banana.api.service.CurationService;
import com.ssafy.banana.dto.CurationDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@RestController
@Api(tags = "큐레이션 관련 API")
@RequestMapping("/curations")
@RequiredArgsConstructor
public class CurationController {

	private final CurationService curationService;

	@GetMapping("/list")
	@ApiOperation(value = "큐레이션 리스트")
	public ResponseEntity<CurationDto> signup(
		@ApiParam(value = "email, password, nickname") @Valid @RequestBody CurationDto curationDto) {
		return ResponseEntity.ok().build();
	}

}
