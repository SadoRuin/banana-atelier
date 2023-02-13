package com.ssafy.banana.api.service;

import static java.time.LocalDateTime.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.banana.db.entity.Artist;
import com.ssafy.banana.db.entity.Curation;
import com.ssafy.banana.db.entity.CurationArt;
import com.ssafy.banana.db.entity.CurationBookmark;
import com.ssafy.banana.db.entity.CurationBookmarkId;
import com.ssafy.banana.db.entity.User;
import com.ssafy.banana.db.entity.enums.CurationStatus;
import com.ssafy.banana.db.repository.ArtRepository;
import com.ssafy.banana.db.repository.ArtistRepository;
import com.ssafy.banana.db.repository.CurationArtRepository;
import com.ssafy.banana.db.repository.CurationBookmarkRepository;
import com.ssafy.banana.db.repository.CurationRepository;
import com.ssafy.banana.db.repository.MyArtistRepository;
import com.ssafy.banana.db.repository.UserRepository;
import com.ssafy.banana.dto.request.CurationRequest;
import com.ssafy.banana.dto.request.MyCurationRequest;
import com.ssafy.banana.dto.response.CurationDataResponse;
import com.ssafy.banana.exception.CustomException;
import com.ssafy.banana.exception.CustomExceptionType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CurationService {

	private final CurationRepository curationRepository;
	private final ArtistRepository artistRepository;
	private final ArtRepository artRepository;
	private final UserRepository userRepository;
	private final CurationBookmarkRepository curationBookmarkRepository;
	private final CurationArtRepository curationArtRepository;
	private final MyArtistRepository myArtistRepository;

	//큐레이션 상태별 조회
	public List<CurationDataResponse.CurationSimple> getCurationList(CurationStatus curationStatus) {
		return curationRepository.findAllByCurationStatus(curationStatus)
			.stream()
			.map(CurationDataResponse.CurationSimple::new)
			.collect(Collectors.toList());
	}

	//특정 사용자의 큐레이션 조회
	public List<CurationDataResponse.CurationSimple> getUSerCurationList(Long userSeq) {
		return curationRepository.findAllByArtist_Id(userSeq)
			.stream()
			.map(CurationDataResponse.CurationSimple::new)
			.collect(Collectors.toList());
	}

	//큐레이션 디테일 조회
	public CurationDataResponse.Curation getCuration(long curation_seq) {
		Curation curation = curationRepository.findById(curation_seq).orElseThrow(null);
		return new CurationDataResponse.Curation(curation);
	}

	//큐레이션 등록
	@Transactional
	public void registerCuration(long userSeq, CurationRequest curationRequest) {

		if (userSeq != curationRequest.getArtistSeq()) {
			throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
		}
		LocalDateTime endTime = LocalDateTime.now();
		LocalDateTime startTime = curationRequest.getCurationStartTime();

		Artist artist = artistRepository.findById(curationRequest.getArtistSeq())
			.orElseThrow(() -> new CustomException(CustomExceptionType.RUNTIME_EXCEPTION));

		Curation curation = Curation.builder()
			.curationStartTime(curationRequest.getCurationStartTime())
			.curationEndTime(endTime)
			.curationName(curationRequest.getCurationName())
			.curationSummary(curationRequest.getCurationSummary())
			.curationStatus(getStatus(endTime, startTime))
			.curationThumbnail(
				artRepository.findById(curationRequest.getCurationArtList().get(0).getArtSeq())
					.orElse(null)
					.getArtThumbnail())
			.artist(artist)
			.build();
		curationRepository.save(curation);

		for (int i = 0; i < curationRequest.getCurationArtList().size(); i++) {
			CurationArt curationArt = CurationArt.builder()
				.isAuction(curationRequest.getCurationArtList().get(i).getIsAuction())
				.auctionGap(curationRequest.getCurationArtList().get(i).getAuctionGap())
				.art(artRepository.findById(curationRequest.getCurationArtList().get(i).getArtSeq()).orElse(null))
				.curation(curationRepository.findById(curation.getId())
					.orElse(null))
				.build();
			curationArtRepository.save(curationArt);
		}
	}

	//큐레이션 수정
	@Transactional
	public void updateCuration(long userSeq, CurationRequest curationRequest, long curationSeq) {
		if (userSeq != curationRequest.getArtistSeq()) {
			throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
		}

		LocalDateTime endTime = curationRequest.getCurationStartTime();
		LocalDateTime startTime = curationRequest.getCurationStartTime();

		Curation curation = curationRepository.findById(curationSeq).orElse(null);
		curation.setId(curationSeq);
		curation.setCurationStartTime(startTime);
		curation.setCurationEndTime(endTime);
		curation.setCurationName(curationRequest.getCurationName());
		curation.setCurationSummary(curationRequest.getCurationSummary());
		curation.setCurationStatus(getStatus(endTime, startTime));
		curation.setArtist(artistRepository.findById(curationRequest.getArtistSeq()).orElse(null));
		curationRepository.save(curation);
		curationArtRepository.deleteAllByCuration_Id(curation.getId());

		for (int i = 0; i < curationRequest.getCurationArtList().size(); i++) {
			CurationArt curationArt = CurationArt.builder()
				.isAuction(curationRequest.getCurationArtList().get(i).getIsAuction())
				.auctionGap(curationRequest.getCurationArtList().get(i).getAuctionGap())
				.art(artRepository.findById(curationRequest.getCurationArtList().get(i).getArtSeq()).orElse(null))
				.curation(curationRepository.findById(curation.getId())
					.orElse(null))
				.build();
			curationArtRepository.save(curationArt);
		}
	}

	@Transactional
	//큐레이션 삭제
	public void deleteCuration(long userSeq, long curation_seq) {
		Curation curation = curationRepository.findById(curation_seq).orElse(null);

		if (userSeq != curation.getArtist().getId()) {
			throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
		}
		curationRepository.deleteById(curation_seq);
		curationArtRepository.deleteAllByCuration_Id(curation_seq);
	}

	CurationStatus getStatus(LocalDateTime endTime, LocalDateTime startTime) {
		if (endTime.isBefore(startTime) && startTime.isAfter(
			now())) {
			return CurationStatus.INIT;
		} else if (endTime.isBefore(startTime)
			&& startTime.isBefore(
			now())) {
			return CurationStatus.ON;
		}
		return CurationStatus.END;
	}

	//큐레이션 북마크 추가
	@Transactional
	public Curation addCurationBookmark(MyCurationRequest myCurationRequest, Long userSeq) {

		if (myCurationRequest.getUserSeq() != userSeq) {
			throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
		}
		Curation curation = curationRepository.findById(myCurationRequest.getCurationSeq()).orElse(null);
		User user = userRepository.findById(myCurationRequest.getUserSeq()).orElse(null);

		if (curation == null) {
			throw new CustomException(CustomExceptionType.NO_CONTENT);
		} else if (user == null) {
			throw new CustomException(CustomExceptionType.USER_NOT_FOUND);
		}
		CurationBookmarkId curationBookmarkId = CurationBookmarkId.builder()
			.userSeq(user.getId())
			.curationSeq(curation.getId())
			.build();
		CurationBookmark curationBookmark = CurationBookmark.builder()
			.id(curationBookmarkId)
			.user(user)
			.curation(curation)
			.build();
		curationBookmarkRepository.save(curationBookmark);

		int curationBookmarkCount = curationBookmarkRepository.countCurationBookmark(curation.getId());
		curation.setCurationBmCount(curationBookmarkCount);
		curationRepository.save(curation);

		return curation;
	}

	//큐레이션 북마크 취소
	@Transactional
	public Curation deleteCurationBookmark(MyCurationRequest myCurationRequest, Long userSeq) {

		if (myCurationRequest.getUserSeq() != userSeq) {
			throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
		}
		CurationBookmark curationBookmark = curationBookmarkRepository.findCurationBookmark(
			myCurationRequest.getCurationSeq(), myCurationRequest.getUserSeq());
		if (curationBookmark == null) {
			throw new CustomException(CustomExceptionType.RUNTIME_EXCEPTION);
		}
		curationBookmarkRepository.delete(curationBookmark);

		Curation curation = curationRepository.findById(myCurationRequest.getCurationSeq()).orElse(null);
		if (curation == null) {
			throw new CustomException(CustomExceptionType.RUNTIME_EXCEPTION);
		}
		int curationBookmarkCount = curationBookmarkRepository.countCurationBookmark(curation.getId());
		curation.setCurationBmCount(curationBookmarkCount);
		curationRepository.save(curation);

		return curation;
	}

	//큐레이션명 및 큐레이션 설명에서 해당 내용 검색
	public List<CurationDataResponse.CurationSimple> getCurationSearchList(String word) {
		return curationRepository.findAllByCurationNameContainingOrCurationSummaryContaining(word, word)
			.stream()
			.map(CurationDataResponse.CurationSimple::new)
			.collect(Collectors.toList());
	}

	//큐레이션 북마크 리스트
	public List<CurationDataResponse.CurationSimple> getCurationBookmarkList(Long userSeq, Long tokenUserSeq) {
		if (userSeq != tokenUserSeq) {
			throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
		}
		List<CurationDataResponse.CurationSimple> bookMarkedCurations = curationBookmarkRepository.findAllByUser_Id(
				userSeq)
			.stream()
			.map(CurationDataResponse.CurationSimple::new)
			.collect(Collectors.toList());

		if (bookMarkedCurations.size() > 0) {
			return bookMarkedCurations;
		} else {
			throw new CustomException(CustomExceptionType.NO_CONTENT);
		}
	}

	// //팔로잉한 작가의 큐레이션 리스트
	// public List<CurationDataResponse.CurationSimple> getCurationFollowingList(Long userSeq, Long tokenUserSeq) {
	// 	if (userSeq != tokenUserSeq) {
	// 		throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
	// 	}
	//
	// 	List<MyArtist> artistList = myArtistRepository.findAllByUser_Id(userSeq);
	// 	List<CurationDataResponse.CurationSimple> curationSimpleList = null;
	//
	// 	for (int i = 0; i < artistList.size(); i++) {
	// 		System.out.println("artist id : " + artistList.get(i).getArtist().getId());
	// 	}
	//
	// 	if (artistList.size() > 0) {
	// 		for (int artist = 0; artist < artistList.size(); artist++) {
	// 			System.out.println("+++++++++++++i+++++++++:" + artist);
	// 			List<CurationDataResponse.CurationSimple> followingCurations = curationRepository.findAllByArtist_Id(
	// 					artistList.get(artist).getArtist().getId())
	// 				.stream()
	// 				.map(CurationDataResponse.CurationSimple::new)
	// 				.collect(Collectors.toList());
	// 			System.out.println("팔로우한 사람의 큐레이션 갯수:" + followingCurations.size());
	// 			curationSimpleList.addAll(followingCurations);
	// 			System.out.println("지금은" + artist + "번째");
	// 		}
	// 		if (curationSimpleList.size() > 0) {
	// 			return curationSimpleList;
	// 		} else
	// 			throw new CustomException(CustomExceptionType.NO_CONTENT);
	// 	} else {
	// 		throw new CustomException(CustomExceptionType.NO_CONTENT);
	// 	}
	// }

}
