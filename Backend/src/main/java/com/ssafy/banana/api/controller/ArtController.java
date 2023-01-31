package com.ssafy.banana.api.controller;


import com.ssafy.banana.api.service.ArtService;
import com.ssafy.banana.dto.response.ArtResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
전체 작품 리스트 나의 작품 리스트 좋아요한 작품 리스트
import java.util.List;

@RestController
@Api(tags = "작품관련 API")
@RequestMapping("/api/arts")
@RequiredArgsConstructor
public class ArtController {

    private final ArtService artService;

//    @PostMapping
//    public ResponseEntity uploadArt(@RequestBody ArtRequestDto artRequestDto){
//        return null;
//    }

    @ApiOperation(value = "전체 작품 리스트", notes = "전체 작품 목록을 반환합니다")
    @GetMapping
    public ResponseEntity<List<ArtResponseDto>> getAllArtList(){

        List<ArtResponseDto> artList = artService.getAllArtList();

        if (artList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(artList);

    }

    @ApiOperation(value = "나의 작품 리스트", notes = "작가 본인의 작품 목록을 반환합니다")
    @GetMapping("/{user_seq}")
    public ResponseEntity<List<ArtResponseDto>> getMyArtList(@PathVariable("user_seq") Long userSeq){

        List<ArtResponseDto> artList = artService.getMyArtList(userSeq);

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
    public ResponseEntity<List<ArtResponseDto>> getLikedArtList(@PathVariable("user_seq") Long userSeq){

        List<ArtResponseDto> artList = artService.getLikedArtList(userSeq);
        if (artList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(artList);
    }

	@ApiOperation(value = "대표 작품 설정", notes = "작가 본인의 대표작을 설정합니다")
	@PutMapping("/masterpiece")
	public ResponseEntity<?> setMasterpieceList(@RequestBody List<MasterpieceRequestDto> masterpieceRequestDtoList) {

		artService.setMasterpieceList(masterpieceRequestDtoList);

		return ResponseEntity.status(HttpStatus.OK).body(null);
	}

	//    @GetMapping("/category/{art_category_name}")
	//    public ResponseEntity getArtListbyCategory(@PathVariable("art_category_name") String artCategoryName){
	//
	//        return null;
	//    }
	//
	//    @GetMapping("/trend")
	//    public ResponseEntity getTrendArtList(){
	//
	//        return null;
	//    }
	//
	//    @GetMapping("/new")
	//    public ResponseEntity getNewArtList(){
	//
	//        return null;
	//    }
	//
	//    @GetMapping("/popular")
	//    public ResponseEntity getPopularArtList(){
	//
	//        return null;
	//    }
	//
	//    @GetMapping("/{art_seq}")
	//    public ResponseEntity getArt(@PathVariable("art_seq") Long artSeq){
	//
	//        return null;
	//    }
	//
	//
	//    @PostMapping("/like")
	//    @ApiOperation(value = "작품 좋아요 추가하기", notes = "")
	//    public ResponseEntity addArtLike(){
	//
	//        return null;
	//    }
	//
	//    @GetMapping("/download/{art_seq}")
	//    public ResponseEntity downloadArt(@PathVariable("art_seq") Long artSeq){
	//
	//        return null;
	//    }

}
