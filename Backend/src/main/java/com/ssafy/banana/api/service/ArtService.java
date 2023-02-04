package com.ssafy.banana.api.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.banana.db.entity.Art;
import com.ssafy.banana.db.entity.ArtCategory;
import com.ssafy.banana.db.entity.Artist;
import com.ssafy.banana.db.entity.MyArt;
import com.ssafy.banana.db.entity.MyArtId;
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
import com.ssafy.banana.exception.CustomException;
import com.ssafy.banana.exception.CustomExceptionType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArtService {

	private final ArtRepository artRepository;
	private final ArtCategoryRepository artCategoryRepository;
	private final UserRepository userRepository;
	private final MyArtRepository myArtRepository;
	private final ArtistRepository artistRepository;
	private final ArtistService artistService;

	@Transactional
	public Art uploadArt(ArtRequest artRequest, Long userSeq) {

		artistService.checkArtist(userSeq);

		ArtCategory artCategory = artCategoryRepository.findById(artRequest.getArtCategorySeq()).orElse(null);
		Artist artist = artistRepository.findById(userSeq).orElse(null);
		String artThumbnail = "artThumbnail 구해오기";    // 수정 예정

		if (artCategory == null || artist == null) {
			throw new CustomException(CustomExceptionType.RUNTIME_EXCEPTION);
		}
		if (artRequest.getUserSeq() != userSeq) {
			throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
		}

		if (artRequest.getUserSeq() == userSeq) {
			Art art = Art.builder()
				.artImg(artRequest.getArtImg())
				.artThumbnail(artThumbnail)
				.artName(artRequest.getArtName())
				.artDescription(artRequest.getArtDescription())
				.artCategory(artCategory)
				.artist(artist)
				.artRegDate(LocalDateTime.now())
				.build();

			artRepository.save(art);
			return art;
		}
		throw new CustomException(CustomExceptionType.NO_CONTENT);
	}

	public List<ArtResponse> getAllArtList() {

		return artRepository.findAllArts();
	}

	public List<ArtResponse> getMyArtList(Long userSeq) {

		return artRepository.findMyArts(userSeq);
	}

	public List<ArtResponse> getMasterpieceList(Long userSeq) {

		return artRepository.findMasterpieces(userSeq);
	}

	public List<ArtResponse> getLikedArtList(Long userSeq) {

		return artRepository.findLikedArt(userSeq);
	}

	public void setMasterpieceList(List<MasterpieceRequest> masterpieceRequestList, Long userSeq) {

		boolean isRepresent = false;
		Art art = null;

		for (int i = 0; i < masterpieceRequestList.size(); i++) {
			MasterpieceRequest masterpieceRequest = masterpieceRequestList.get(i);
			isRepresent = masterpieceRequest.isRepresent();
			art = artRepository.findById(masterpieceRequest.getArtSeq()).orElse(null);

			if (masterpieceRequest.getUserSeq() != userSeq) {
				throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
			}
			if (art == null) {
				throw new CustomException(CustomExceptionType.RUNTIME_EXCEPTION);
			}

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

		Art art = artRepository.findById(myArtRequest.getArtSeq()).orElse(null);
		User user = userRepository.findById(myArtRequest.getUserSeq()).orElse(null);

		if (art == null) {
			throw new CustomException(CustomExceptionType.NO_CONTENT);
		} else if (user == null) {
			throw new CustomException(CustomExceptionType.USER_NOT_FOUND);
		}

		if (user.getId() == userSeq) {
			MyArtId myArtId = MyArtId.builder()
				.userSeq(user.getId())
				.artSeq(art.getId())
				.build();

			MyArt myArt = MyArt.builder()
				.id(myArtId)
				.user(user)
				.art(art)
				.build();
			myArtRepository.save(myArt);
			return myArt;
		} else {
			throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
		}
	}

	@Transactional
	public Art updateArt(ArtRequest artRequest, Long userSeq) {

		Art art = artRepository.findById(artRequest.getArtSeq()).orElse(null);
		Long artistSeq = art.getArtist().getId();
		String artThumbnail = "artThumbnail 구해오기";    // 수정 예정

		if (artRequest.getUserSeq() == artistSeq && artistSeq == userSeq) {
			art.setArtName(artRequest.getArtName());
			art.setArtImg(artRequest.getArtImg());
			art.setArtThumbnail(artThumbnail);
			art.setArtDescription(artRequest.getArtDescription());
			art.getArtCategory().setId(artRequest.getArtCategorySeq());
			artRepository.save(art);
			return art;
		} else {
			throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
		}
	}

	@Transactional
	public Long deleteArt(Long artSeq, Long userSeq) {

		Art art = artRepository.findById(artSeq).orElse(null);
		Long artistSeq = art.getArtist().getId();

		if (userSeq == artistSeq) {
			//작품이 하나만 있다면 삭제 불가
			int myArtCount = artRepository.countArtByArtistSeq(userSeq);
			if (myArtCount > 1) {
				artRepository.deleteById(artSeq);
				return artSeq;
			} else {
				throw new CustomException(CustomExceptionType.DO_NOT_DELETE);
			}
		} else {
			throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
		}
	}

}
