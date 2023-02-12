package com.ssafy.banana.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.banana.api.service.ArtistService;
import com.ssafy.banana.dto.request.UpdateArtistRequest;
import com.ssafy.banana.dto.response.ExceptionResponse;
import com.ssafy.banana.dto.response.SuccessResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@Api(tags = "작가관련 API")
@RequestMapping("/artists")
@RequiredArgsConstructor
public class ArtistController {

	private final ArtistService artistService;

	@PatchMapping("/update")
	@ApiOperation(value = "작가정보 수정")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "instagramLink", value = "인스타그램 링크"),
		@ApiImplicitParam(name = "twitterLink", value = "트위터 링크"),
		@ApiImplicitParam(name = "youtubeLink", value = "유튜브 링크"),
		@ApiImplicitParam(name = "blogLink", value = "블로그 링크"),
		@ApiImplicitParam(name = "artistIntro", value = "작가 자기소개")
	})
	@ApiResponses({
		@ApiResponse(code = 201, message = "작가정보 수정 성공", response = SuccessResponse.class),
		@ApiResponse(code = 404, message = "작가 정보가 없습니다.", response = ExceptionResponse.class)
	})
	public ResponseEntity<SuccessResponse> updateArtist(@RequestHeader String Authorization,
		@RequestBody UpdateArtistRequest updateArtistRequest) {
		String token = Authorization.split(" ")[1];
		artistService.updateUser(token, updateArtistRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse("작가정보가 수정되었습니다."));
	}
}
