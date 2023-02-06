package com.ssafy.banana.api.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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

		if (artRequest.getUserSeq() != userSeq) {
			throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
		}
		artistService.checkArtist(userSeq);

		ArtCategory artCategory = artCategoryRepository.findById(artRequest.getArtCategorySeq()).orElse(null);
		Artist artist = artistRepository.findById(artRequest.getUserSeq()).orElse(null);
		String artThumbnail = "artThumbnail 구해오기";    // 수정 예정

		if (artCategory == null || artist == null) {
			throw new CustomException(CustomExceptionType.RUNTIME_EXCEPTION);
		}

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

	public List<ArtResponse> getAllArtList() {

		List<ArtResponse> allArtList = artRepository.findAllArts();
		if (!CollectionUtils.isEmpty(allArtList)) {
			return allArtList;
		} else {
			throw new CustomException(CustomExceptionType.NO_CONTENT);
		}
	}

	public List<ArtResponse> getNewArtList() {

		List<ArtResponse> newArtList = artRepository.findNewArts();
		if (!CollectionUtils.isEmpty(newArtList)) {
			return newArtList;
		} else {
			throw new CustomException(CustomExceptionType.NO_CONTENT);
		}
	}

	public List<ArtResponse> getMyArtList(Long userSeq, Long tokenUserSeq) {

		if (userSeq != tokenUserSeq) {
			throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
		}
		List<ArtResponse> myArtList = artRepository.findMyArts(userSeq);
		if (!CollectionUtils.isEmpty(myArtList)) {
			return myArtList;
		} else {
			throw new CustomException(CustomExceptionType.NO_CONTENT);
		}
	}

	public List<ArtResponse> getMasterpieceList(Long userSeq, Long tokenUserSeq) {

		if (userSeq != tokenUserSeq) {
			throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
		}
		List<ArtResponse> masterpieceList = artRepository.findMasterpieces(userSeq);
		if (!CollectionUtils.isEmpty(masterpieceList)) {
			return masterpieceList;
		} else {
			throw new CustomException(CustomExceptionType.NO_CONTENT);
		}
	}

	public List<ArtResponse> getLikedArtList(Long userSeq, Long tokenUserSeq) {

		if (userSeq != tokenUserSeq) {
			throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
		}
		List<ArtResponse> likedArtList = artRepository.findLikedArt(userSeq);
		if (!CollectionUtils.isEmpty(likedArtList)) {
			return likedArtList;
		} else {
			throw new CustomException(CustomExceptionType.NO_CONTENT);
		}
	}

	@Transactional
	public void setMasterpieceList(List<MasterpieceRequest> masterpieceRequestList, Long userSeq) {

		boolean isRepresent = false;
		Art art = null;

		for (int i = 0; i < masterpieceRequestList.size(); i++) {
			MasterpieceRequest masterpieceRequest = masterpieceRequestList.get(i);
			if (masterpieceRequest.getUserSeq() != userSeq) {
				throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
			}
			isRepresent = masterpieceRequest.isRepresent();
			art = artRepository.findById(masterpieceRequest.getArtSeq()).orElse(null);

			if (art == null) {
				throw new CustomException(CustomExceptionType.NO_CONTENT);
			}
			art.setRepresent(isRepresent);
			artRepository.save(art);
		}
	}

	public List<ArtResponse> getArtListbyCategory(Long artCategorySeq) {

		List<ArtResponse> artListbyCategory = artRepository.findArtsbyCategory(artCategorySeq);
		if (!CollectionUtils.isEmpty(artListbyCategory)) {
			return artListbyCategory;
		} else {
			throw new CustomException(CustomExceptionType.NO_CONTENT);
		}
	}

	public List<ArtResponse> getTrendArtList() {

		LocalDateTime twoWeeksAgo = LocalDateTime.now().minusWeeks(2);
		List<ArtResponse> trendArtList = artRepository.findAllOrderByArtLikeCountAndArtRegDate(twoWeeksAgo);
		if (!CollectionUtils.isEmpty(trendArtList)) {
			return trendArtList;
		} else {
			throw new CustomException(CustomExceptionType.NO_CONTENT);
		}
	}

	public List<ArtResponse> getPopularArtList() {

		List<ArtResponse> popularArtList = artRepository.findAllOrderByArtLikeCount();
		if (!CollectionUtils.isEmpty(popularArtList)) {
			return popularArtList;
		} else {
			throw new CustomException(CustomExceptionType.NO_CONTENT);
		}
	}

	public ArtDetailResponse getArt(Long artSeq) {

		Art art = artRepository.findById(artSeq).orElse(null);
		if (art == null) {
			throw new CustomException(CustomExceptionType.NO_CONTENT);
		}
		User artist = art.getArtist().getUser();

		return new ArtDetailResponse(art, artist);
	}

	@Transactional
	public Art addArtLike(MyArtRequest myArtRequest, Long userSeq) {

		if (myArtRequest.getUserSeq() != userSeq) {
			throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
		}
		Art art = artRepository.findById(myArtRequest.getArtSeq()).orElse(null);
		User user = userRepository.findById(myArtRequest.getUserSeq()).orElse(null);

		if (art == null) {
			throw new CustomException(CustomExceptionType.NO_CONTENT);
		} else if (user == null) {
			throw new CustomException(CustomExceptionType.USER_NOT_FOUND);
		}
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

		int artLikeCount = myArtRepository.countArtLike(art.getId());
		art.setArtLikeCount(artLikeCount);
		artRepository.save(art);

		return art;
	}

	@Transactional
	public Art deleteArtLike(MyArtRequest myArtRequest, Long userSeq) {

		if (myArtRequest.getUserSeq() != userSeq) {
			throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
		}
		MyArt myArt = myArtRepository.findMyArt(myArtRequest.getArtSeq(), myArtRequest.getUserSeq());
		if (myArt == null) {
			throw new CustomException(CustomExceptionType.RUNTIME_EXCEPTION);
		}
		myArtRepository.delete(myArt);

		Art art = artRepository.findById(myArtRequest.getArtSeq()).orElse(null);
		if (art == null) {
			throw new CustomException(CustomExceptionType.RUNTIME_EXCEPTION);
		}
		int artLikeCount = myArtRepository.countArtLike(art.getId());
		art.setArtLikeCount(artLikeCount);
		artRepository.save(art);

		return art;
	}

	@Transactional
	public Art updateArt(ArtRequest artRequest, Long userSeq) {

		if (artRequest.getUserSeq() != userSeq) {
			throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
		}
		Art art = artRepository.findById(artRequest.getArtSeq()).orElse(null);
		if (art == null) {
			throw new CustomException(CustomExceptionType.RUNTIME_EXCEPTION);
		}
		Long artistSeq = art.getArtist().getId();
		String artThumbnail = "artThumbnail 구해오기";    // 수정 예정

		if (artistSeq == userSeq) {
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
		if (art == null) {
			throw new CustomException(CustomExceptionType.RUNTIME_EXCEPTION);
		}
		Long artistSeq = art.getArtist().getId();

		if (userSeq != artistSeq) {
			throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
		}
		//작품이 하나만 있다면 삭제 불가
		int myArtCount = artRepository.countArtByArtistSeq(userSeq);
		if (myArtCount > 1) {
			artRepository.deleteById(artSeq);
			return artSeq;
		} else {
			throw new CustomException(CustomExceptionType.DO_NOT_DELETE);
		}
	}

}