package com.ssafy.banana.api.service;

import static java.time.LocalDateTime.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.banana.db.entity.Artist;
import com.ssafy.banana.db.entity.Curation;
import com.ssafy.banana.db.entity.enums.CurationStatus;
import com.ssafy.banana.db.repository.ArtRepository;
import com.ssafy.banana.db.repository.ArtistRepository;
import com.ssafy.banana.db.repository.CurationArtRepository;
import com.ssafy.banana.db.repository.CurationRepository;
import com.ssafy.banana.dto.request.CurationRequest;
import com.ssafy.banana.dto.response.CurationDataResponse;
import com.ssafy.banana.exception.CustomException;
import com.ssafy.banana.exception.CustomExceptionType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CurationService {

	private final CurationRepository curationRepository;

	private final CurationArtRepository curationArtRepository;

	private final ArtistRepository artistRepository;

	private final ArtRepository artRepository;

	//큐레이션 전체조회
	public List<CurationDataResponse.CurationSimple> getCurationList() {
		return curationRepository.findAll()
			.stream()
			.map(CurationDataResponse.CurationSimple::new)
			.collect(Collectors.toList());
	}

	//큐레이션 디테일 조회
	public CurationDataResponse.Curation getCuration(long curation_seq) {
		Curation curation = curationRepository.findById(curation_seq).orElseThrow(null);
		return new CurationDataResponse.Curation(curation);
	}

	// @Transactional
	// public Art addArtLike(MyArtRequest myArtRequest, Long userSeq) {
	//
	// 	if (myArtRequest.getUserSeq() != userSeq) {
	// 		throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
	// 	}
	// 	Art art = artRepository.findById(myArtRequest.getArtSeq()).orElse(null);
	// 	User user = userRepository.findById(myArtRequest.getUserSeq()).orElse(null);
	//
	// 	if (art == null) {
	// 		throw new CustomException(CustomExceptionType.NO_CONTENT);
	// 	} else if (user == null) {
	// 		throw new CustomException(CustomExceptionType.USER_NOT_FOUND);
	// 	}
	// 	MyArtId myArtId = MyArtId.builder()
	// 		.userSeq(user.getId())
	// 		.artSeq(art.getId())
	// 		.build();
	// 	MyArt myArt = MyArt.builder()
	// 		.id(myArtId)
	// 		.user(user)
	// 		.art(art)
	// 		.build();
	// 	myArtRepository.save(myArt);
	//
	// 	int artLikeCount = myArtRepository.countArtLike(art.getId());
	// 	art.setArtLikeCount(artLikeCount);
	// 	artRepository.save(art);
	//
	// 	return art;
	// }
	//
	// @Transactional
	// public Art deleteArtLike(MyArtRequest myArtRequest, Long userSeq) {
	//
	// 	if (myArtRequest.getUserSeq() != userSeq) {
	// 		throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
	// 	}
	// 	MyArt myArt = myArtRepository.findMyArt(myArtRequest.getArtSeq(), myArtRequest.getUserSeq());
	// 	if (myArt == null) {
	// 		throw new CustomException(CustomExceptionType.RUNTIME_EXCEPTION);
	// 	}
	// 	myArtRepository.delete(myArt);
	//
	// 	Art art = artRepository.findById(myArtRequest.getArtSeq()).orElse(null);
	// 	if (art == null) {
	// 		throw new CustomException(CustomExceptionType.RUNTIME_EXCEPTION);
	// 	}
	// 	int artLikeCount = myArtRepository.countArtLike(art.getId());
	// 	art.setArtLikeCount(artLikeCount);
	// 	artRepository.save(art);
	//
	// 	return art;
	// }

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
				artRepository.findById(curationRequest.getArtSeqList().get(0)).orElse(null).getArtThumbnail())
			.artist(artist)
			.build();

		curationRepository.save(curation);
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
	}

	@Transactional
	//큐레이션 삭제
	public void deleteCuration(long userSeq, long curation_seq) {
		Curation curation = curationRepository.findById(curation_seq).orElse(null);

		if (userSeq != curation.getArtist().getId()) {
			throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
		}
		curationRepository.deleteById(curation_seq);
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

}
