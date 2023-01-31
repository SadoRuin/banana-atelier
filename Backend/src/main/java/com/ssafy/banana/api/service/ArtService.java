package com.ssafy.banana.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ssafy.banana.db.entity.Art;
import com.ssafy.banana.db.entity.ArtCategory;
import com.ssafy.banana.db.repository.ArtCategoryRepository;
import com.ssafy.banana.db.repository.ArtRepository;
import com.ssafy.banana.dto.request.ArtRequestDto;
import com.ssafy.banana.dto.request.MasterpieceRequestDto;
import com.ssafy.banana.dto.response.ArtResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArtService {

	private final ArtRepository artRepository;
	private final ArtCategoryRepository artCategoryRepository;

	public void uploadArt(ArtRequestDto artRequestDto) {

		Optional<ArtCategory> artCategory = artCategoryRepository.findById(artRequestDto.getArtCategorySeq());

		if (artCategory != null) {

			Art art = Art.builder()
				.artImg(artRequestDto.getArtImg())
				.artName(artRequestDto.getArtName())
				.artDescription(artRequestDto.getArtDescription())
				.artCategory(artCategory.orElse(null))
				.build();

			artRepository.save(art);
		}

	}

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

	public List<ArtResponseDto> getArtListbyCategory(Long artCategorySeq) {

		return artRepository.findArtsbyCategory(artCategorySeq);

	}
}
