package com.ssafy.banana.api.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

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
import com.ssafy.banana.dto.DownloladFileDto;
import com.ssafy.banana.dto.request.ArtRequest;
import com.ssafy.banana.dto.request.MasterpieceRequest;
import com.ssafy.banana.dto.request.MyArtRequest;
import com.ssafy.banana.dto.response.ArtDetailResponse;
import com.ssafy.banana.dto.response.ArtResponse;
import com.ssafy.banana.exception.CustomException;
import com.ssafy.banana.exception.CustomExceptionType;
import com.ssafy.banana.security.jwt.TokenProvider;

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
	private final TokenProvider tokenProvider;
	private final AwsS3Service awsS3Service;

	@Transactional
	public Art uploadArt(MultipartFile artFile, ArtRequest artRequest, String token) {
		long userSeq = tokenProvider.getSubject(token);

		// 작가 체크
		Artist artist = artistService.checkArtist(userSeq);

		ArtCategory artCategory = artCategoryRepository.findById(artRequest.getArtCategorySeq())
			.orElseThrow(() -> new CustomException(CustomExceptionType.RUNTIME_EXCEPTION));

		Art art = Art.builder()
			.artImg(awsS3Service.uploadArtImage(userSeq, artFile))
			.artThumbnail(awsS3Service.uploadArtThumbnail(userSeq, artFile))
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

		LocalDateTime twoWeeksAgo = LocalDateTime.now().minusWeeks(2);
		List<ArtResponse> newArtList = artRepository.findNewArts(twoWeeksAgo);
		if (!CollectionUtils.isEmpty(newArtList)) {
			return newArtList;
		} else {
			throw new CustomException(CustomExceptionType.NO_CONTENT);
		}
	}

	public List<ArtResponse> getMyArtList(Long userSeq) {

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
			art = artRepository.findById(masterpieceRequest.getArtSeq())
				.orElseThrow(() -> new CustomException(CustomExceptionType.NO_CONTENT));

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

		Art art = artRepository.findById(artSeq)
			.orElseThrow(() -> new CustomException(CustomExceptionType.NO_CONTENT));
		User artist = art.getArtist().getUser();

		return new ArtDetailResponse(art, artist);
	}

	@Transactional
	public Art addArtLike(MyArtRequest myArtRequest, Long userSeq) {

		if (myArtRequest.getUserSeq() != userSeq) {
			throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
		}
		Art art = artRepository.findById(myArtRequest.getArtSeq())
			.orElseThrow(() -> new CustomException(CustomExceptionType.NO_CONTENT));
		User user = userRepository.findById(myArtRequest.getUserSeq())
			.orElseThrow(() -> new CustomException(CustomExceptionType.USER_NOT_FOUND));

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
		if (ObjectUtils.isEmpty(myArt)) {
			throw new CustomException(CustomExceptionType.RUNTIME_EXCEPTION);
		}
		myArtRepository.delete(myArt);

		Art art = artRepository.findById(myArtRequest.getArtSeq())
			.orElseThrow(() -> new CustomException(CustomExceptionType.RUNTIME_EXCEPTION));

		int artLikeCount = myArtRepository.countArtLike(art.getId());
		art.setArtLikeCount(artLikeCount);
		artRepository.save(art);

		return art;
	}

	public DownloladFileDto downloadArt(long artSeq) {

		Art art = artRepository.findById(artSeq)
			.orElseThrow(() -> new CustomException(CustomExceptionType.RUNTIME_EXCEPTION));

		return awsS3Service.downloadArtImage(art.getArtist().getId(), art.getArtImg());
	}

	@Transactional
	public Art updateArt(ArtRequest artRequest, Long userSeq) {

		if (artRequest.getUserSeq() != userSeq) {
			throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
		}
		Art art = artRepository.findById(artRequest.getArtSeq())
			.orElseThrow(() -> new CustomException(CustomExceptionType.RUNTIME_EXCEPTION));
		Long artistSeq = art.getArtist().getId();

		if (artistSeq == userSeq) {
			art.setArtName(artRequest.getArtName());
			art.setArtDescription(artRequest.getArtDescription());
			art.getArtCategory().setId(artRequest.getArtCategorySeq());
			artRepository.save(art);
			return art;
		} else {
			throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
		}
	}

	@Transactional
	public void deleteArt(long artSeq, String token) {
		long userSeq = tokenProvider.getSubject(token);
		Art art = artRepository.findById(artSeq)
			.orElseThrow(() -> new CustomException(CustomExceptionType.NO_CONTENT));
		long artistSeq = art.getArtist().getId();

		if (userSeq != artistSeq) {
			throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
		}

		//작품이 하나만 있다면 삭제 불가
		int myArtCount = artRepository.countArtByArtistSeq(artistSeq);
		if (myArtCount > 1) {
			// db record 삭제
			artRepository.deleteById(artSeq);
			// 실제 파일 삭제
			awsS3Service.deleteArtImage(artistSeq, art.getArtImg());
			awsS3Service.deleteArtThumbnail(artistSeq, art.getArtThumbnail());
		} else {
			throw new CustomException(CustomExceptionType.DO_NOT_DELETE);
		}
	}

}
