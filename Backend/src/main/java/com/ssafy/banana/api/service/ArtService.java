package com.ssafy.banana.api.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.banana.db.entity.Art;
import com.ssafy.banana.db.entity.ArtCategory;
import com.ssafy.banana.db.entity.Artist;
import com.ssafy.banana.db.entity.MyArt;
import com.ssafy.banana.db.entity.User;
import com.ssafy.banana.db.repository.ArtCategoryRepository;
import com.ssafy.banana.db.repository.ArtRepository;
import com.ssafy.banana.db.repository.ArtistRepository;
import com.ssafy.banana.db.repository.MyArtRepository;
import com.ssafy.banana.db.repository.UserRepository;
import com.ssafy.banana.dto.request.ArtRequest;
import com.ssafy.banana.dto.request.MasterpieceRequest;
import com.ssafy.banana.dto.request.MyArtRequest;
import com.ssafy.banana.dto.response.ArtDetailResponse;
import com.ssafy.banana.dto.response.ArtResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArtService {

	private final ArtRepository artRepository;
	private final ArtCategoryRepository artCategoryRepository;
	private final UserRepository userRepository;
	private final MyArtRepository myArtRepository;
	private final ArtistRepository artistRepository;

	public Art uploadArt(ArtRequest artRequest, Long userSeq, String artThumbnail) {

		ArtCategory artCategory = artCategoryRepository.findById(artRequest.getArtCategorySeq()).orElse(null);
		Artist artist = artistRepository.findById(userSeq).orElse(null);
		Art art = null;

		if (artCategory != null) {

			art = Art.builder()
				.artImg(artRequest.getArtImg())
				.artThumbnail(artThumbnail)
				.artName(artRequest.getArtName())
				.artDescription(artRequest.getArtDescription())
				.artCategory(artCategory)
				.artist(artist)
				.artRegDate(LocalDateTime.now())
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

	public ArtDetailResponse getArt(Long artSeq) {

		Art art = artRepository.findById(artSeq).orElse(null);
		User artist = art.getArtist().getUser();

		return new ArtDetailResponse(art, artist);
	}

	public MyArt addArtLike(MyArtRequest myArtRequest, Long userSeq) {

		User user = userRepository.findById(userSeq).orElse(null);
		Art art = artRepository.findById(myArtRequest.getArtSeq()).orElse(null);

		MyArt myArt = null;

		if (user != null && art != null) {
			myArt = MyArt.builder()
				.user(userRepository.findById(myArtRequest.getUserSeq()).orElse(null))
				.art(artRepository.findById(myArtRequest.getArtSeq()).orElse(null))
				.build();
			myArtRepository.save(myArt);
		}

		return myArt;
	}

	public Art updateArt(ArtRequest artRequest, Long userSeq, String artThumbnail) {

		Art art = artRepository.findById(artRequest.getArtSeq()).orElse(null);
		Long artistSeq = art.getArtist().getId();

		if (userSeq == artistSeq) {
			art.setArtName(artRequest.getArtName());
			art.setArtImg(artRequest.getArtImg());
			art.setArtThumbnail(artThumbnail);
			art.setArtDescription(artRequest.getArtDescription());
			art.getArtCategory().setId(artRequest.getArtCategorySeq());
			artRepository.save(art);
		} else {
			return null;
		}
		return art;
	}

	public Long deleteArt(Long artSeq, Long userSeq) {

		Art art = artRepository.findById(artSeq).orElse(null);
		Long artistSeq = art.getArtist().getId();

		if (userSeq == artistSeq) {
			artRepository.deleteById(artSeq);
			return artSeq;
		}

		return -1L;
	}
}
