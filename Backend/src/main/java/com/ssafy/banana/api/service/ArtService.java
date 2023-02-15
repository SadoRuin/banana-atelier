package com.ssafy.banana.api.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.banana.db.entity.Art;
import com.ssafy.banana.db.entity.ArtCategory;
import com.ssafy.banana.db.entity.Artist;
import com.ssafy.banana.db.entity.MyArt;
import com.ssafy.banana.db.entity.MyArtId;
import com.ssafy.banana.db.entity.User;
import com.ssafy.banana.db.repository.ArtCategoryRepository;
import com.ssafy.banana.db.repository.ArtRepository;
import com.ssafy.banana.db.repository.MyArtRepository;
import com.ssafy.banana.db.repository.UserRepository;
import com.ssafy.banana.dto.DownloladFileDto;
import com.ssafy.banana.dto.request.ArtRequest;
import com.ssafy.banana.dto.request.SeqRequest;
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

		String artFileName = awsS3Service.uploadArtImage(userSeq, artFile);

		Art art = Art.builder()
			.artImg(artFileName)
			.artThumbnail(awsS3Service.uploadArtThumbnail(userSeq, artFileName, artFile))
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

	public List<ArtResponse> getMyArtList(long userSeq) {

		List<ArtResponse> myArtList = artRepository.findMyArts(userSeq);
		if (!CollectionUtils.isEmpty(myArtList)) {
			return myArtList;
		} else {
			throw new CustomException(CustomExceptionType.NO_CONTENT);
		}
	}

	public List<ArtResponse> getMasterpieceList(long userSeq) {

		List<ArtResponse> masterpieceList = artRepository.findMasterpieces(userSeq);
		if (!CollectionUtils.isEmpty(masterpieceList)) {
			return masterpieceList;
		} else {
			throw new CustomException(CustomExceptionType.NO_CONTENT);
		}
	}

	public List<ArtResponse> getLikedArtList(long userSeq) {

		List<ArtResponse> likedArtList = artRepository.findLikedArt(userSeq);
		if (!CollectionUtils.isEmpty(likedArtList)) {
			return likedArtList;
		} else {
			throw new CustomException(CustomExceptionType.NO_CONTENT);
		}
	}

	@Transactional
	public void setMasterpieceList(List<SeqRequest> artSeqRequestList, long userSeq) {

		for (int i = 0; i < artSeqRequestList.size(); i++) {
			// 요청 작품 확인
			Art art = artRepository.findById(artSeqRequestList.get(i).getSeq())
				.orElseThrow(() -> new CustomException(CustomExceptionType.NO_CONTENT));
			// 요청 작가 확인
			if (art.getArtist().getId() != userSeq) {
				throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
			}
		}
		// 작가의 모든 작품
		List<Art> artList = artRepository.findAllByArtist_Id(userSeq)
			.orElseThrow(() -> new CustomException(CustomExceptionType.NO_CONTENT));
		for (int i = 0; i < artList.size(); i++) {
			Art art = artList.get(i);
			if (art.isRepresent() == true) {
				art.setRepresent(false);
			}
		}
		Art art = null;
		for (int i = 0; i < artSeqRequestList.size(); i++) {
			for (int j = 0; j < artList.size(); j++) {
				// 설정 요청 들어온 작품만 true로 바꿔줌
				art = artList.get(j);
				if (artSeqRequestList.get(i).getSeq() == art.getId()) {
					art.setRepresent(true);
					artRepository.save(art);
				}
			}
		}
	}

	public List<ArtResponse> getArtListbyCategory(long artCategorySeq) {

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

	@Transactional
	public ArtDetailResponse getArt(long artSeq) {

		Art art = artRepository.findById(artSeq)
			.orElseThrow(() -> new CustomException(CustomExceptionType.NO_CONTENT));
		User artist = art.getArtist().getUser();
		art = countHit(art);

		return new ArtDetailResponse(art, artist);
	}

	public Art countHit(Art art) {
		art.setArtHit(art.getArtHit() + 1);
		artRepository.save(art);

		return art;
	}

	@Transactional
	public Art addArtLike(SeqRequest seqRequest, long userSeq) {

		Art art = artRepository.findById(seqRequest.getSeq())
			.orElseThrow(() -> new CustomException(CustomExceptionType.NO_CONTENT));
		User user = userRepository.findById(userSeq)
			.orElseThrow(() -> new CustomException(CustomExceptionType.USER_NOT_FOUND));

		MyArtId myArtId = MyArtId.builder()
			.userSeq(user.getId())
			.artSeq(art.getId())
			.build();
		if (myArtRepository.findById(myArtId).isPresent()) {
			throw new CustomException(CustomExceptionType.ART_LIKE_CONFLICT);
		}
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
	public Art deleteArtLike(SeqRequest seqRequest, long userSeq) {

		MyArt myArt = myArtRepository.findMyArt(seqRequest.getSeq(), userSeq)
			.orElseThrow(() -> new CustomException(CustomExceptionType.NO_CONTENT));
		myArtRepository.delete(myArt);

		Art art = artRepository.findById(seqRequest.getSeq())
			.orElseThrow(() -> new CustomException(CustomExceptionType.NO_CONTENT));

		int artLikeCount = myArtRepository.countArtLike(art.getId());
		art.setArtLikeCount(artLikeCount);
		artRepository.save(art);

		return art;
	}

	public DownloladFileDto downloadArt(long artSeq) {

		Art art = artRepository.findById(artSeq)
			.orElseThrow(() -> new CustomException(CustomExceptionType.RUNTIME_EXCEPTION));

		DownloladFileDto downloladFileDto = awsS3Service.downloadArtImage(art.getArtist().getId(), art.getArtImg());

		art.setArtDownloadCount(art.getArtDownloadCount() + 1);
		artRepository.save(art);

		return downloladFileDto;
	}

	@Transactional
	public Art updateArt(ArtRequest artRequest, long userSeq) {

		Art art = artRepository.findById(artRequest.getArtSeq())
			.orElseThrow(() -> new CustomException(CustomExceptionType.RUNTIME_EXCEPTION));
		long artistSeq = art.getArtist().getId();
		ArtCategory artCategory = artCategoryRepository.findById(artRequest.getArtCategorySeq())
			.orElseThrow(() -> new CustomException(CustomExceptionType.RUNTIME_EXCEPTION));

		if (userSeq == artistSeq) {
			art.setArtName(artRequest.getArtName());
			art.setArtDescription(artRequest.getArtDescription());
			art.setArtCategory(artCategory);
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
