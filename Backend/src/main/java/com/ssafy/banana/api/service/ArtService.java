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

	public void setMasterpieceList(List<MasterpieceRequestDto> masterpieceRequestDtoList) {

		boolean isRepresent = false;
		Art art = null;

		for (int i = 0; i < masterpieceRequestDtoList.size(); i++) {
			MasterpieceRequestDto masterpieceRequestDto = masterpieceRequestDtoList.get(i);
			isRepresent = masterpieceRequestDto.isRepresent();
			art = artRepository.findById(masterpieceRequestDto.getArtSeq()).orElse(null);
			if (art != null) {
				art.setRepresent(isRepresent);
				artRepository.save(art);
			}
		}

	}

}
