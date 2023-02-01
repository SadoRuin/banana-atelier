package com.ssafy.banana.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ssafy.banana.db.entity.Art;
import com.ssafy.banana.db.entity.ArtCategory;
import com.ssafy.banana.db.repository.ArtCategoryRepository;
import com.ssafy.banana.db.repository.ArtRepository;
import com.ssafy.banana.db.repository.ArtistRepository;
import com.ssafy.banana.dto.request.ArtRequest;
import com.ssafy.banana.dto.request.MasterpieceRequest;
import com.ssafy.banana.dto.response.ArtResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArtService {

	private final ArtRepository artRepository;
	private final ArtCategoryRepository artCategoryRepository;
	private final ArtistRepository artistRepository;

	public Art uploadArt(ArtRequest artRequest) {

		Optional<ArtCategory> artCategory = artCategoryRepository.findById(artRequest.getArtCategorySeq());
		Art art = null;

		if (artCategory != null) {

			art = Art.builder()
				.artImg(artRequest.getArtImg())
				.artName(artRequest.getArtName())
				.artDescription(artRequest.getArtDescription())
				.artCategory(artCategory.orElse(null))
				.build();

			artRepository.save(art);
		}
		return art;
	}

	public List<ArtResponse> getAllArtList() {
		return artRepository.findAllArts();
	}

	public List<ArtResponse> getMyArtList(Long userSeq) {

		return artRepository.findMyArts(userSeq);
	}

	public List<ArtResponse> getLikedArtList(Long userSeq) {

		return artRepository.findLikedArt(userSeq);
	}

	public void setMasterpieceList(List<MasterpieceRequest> masterpieceRequestList) {

		boolean isRepresent = false;
		Art art = null;

		for (int i = 0; i < masterpieceRequestList.size(); i++) {
			MasterpieceRequest masterpieceRequest = masterpieceRequestList.get(i);
			isRepresent = masterpieceRequest.isRepresent();
			art = artRepository.findById(masterpieceRequest.getArtSeq()).orElse(null);
			if (art != null) {
				art.setRepresent(isRepresent);
				artRepository.save(art);
			}
		}

	}

	public List<ArtResponse> getArtListbyCategory(Long artCategorySeq) {

		return artRepository.findArtsbyCategory(artCategorySeq);

	}

	public List<ArtResponse> getPopularArtList() {

		return artRepository.findAllOrderByArtLikeCount();
	}

	public Art updateArt(ArtRequest artRequest) {

		Art art = artRepository.findById(artRequest.getArtSeq()).orElse(null);
		Long artistSeq = artistRepository.findById(artRequest.getUserSeq()).orElse(null).getId();

		if (artRequest.getUserSeq() == artistSeq) {
			art.setArtImg(artRequest.getArtImg());
			art.setArtDescription(artRequest.getArtDescription());
			art.getArtCategory().setId(artRequest.getArtCategorySeq());

		} else {
			return null;
		}
		return art;
	}
}
