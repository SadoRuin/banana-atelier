package com.ssafy.banana.api.service;

import com.ssafy.banana.db.repository.ArtRepository;
import com.ssafy.banana.dto.request.MasterpieceRequestDto;
import com.ssafy.banana.dto.response.ArtResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtService {

    private final ArtRepository artRepository;

    public List<ArtResponseDto> getAllArtList() {
        return artRepository.findAllArts();
    }

    public List<ArtResponseDto> getMyArtList(Long userSeq) {
        return artRepository.findMyArts(userSeq);
    }

    public List<ArtResponseDto> getLikedArtList(Long userSeq) {
        return artRepository.findLikedArt(userSeq);
    }

//    public List<ArtResponseDto> setMasterpieceList(MasterpieceRequestDto masterpieceRequestDto) {
//
//        boolean isRepresent = masterpieceRequestDto.getIsRepresent();
//
//    }
}
