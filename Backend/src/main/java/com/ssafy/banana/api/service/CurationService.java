package com.ssafy.banana.api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.banana.db.entity.Art;
import com.ssafy.banana.db.entity.Curation;
import com.ssafy.banana.db.entity.MyArt;
import com.ssafy.banana.db.entity.MyArtId;
import com.ssafy.banana.db.entity.User;
import com.ssafy.banana.db.entity.enums.CurationStatus;

import com.ssafy.banana.db.repository.CurationArtRepository;
import com.ssafy.banana.db.repository.CurationRepository;
import com.ssafy.banana.dto.request.CurationRequest;
import com.ssafy.banana.dto.request.MyArtRequest;
import com.ssafy.banana.dto.response.CurationDataResponse;
import com.ssafy.banana.exception.CustomException;
import com.ssafy.banana.exception.CustomExceptionType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CurationService {

	@Autowired
	CurationRepository curationRepository;
	@Autowired
	CurationArtRepository curationArtRepository;

	//큐레이션 전체조회
	public List<CurationDataResponse.CurationSimple> getCurationList() {
		return curationRepository.findAll().stream().map(CurationDataResponse.CurationSimple::new).collect(Collectors.toList());
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
	public void registerCuration(CurationRequest curationRequest){
		CurationStatus status = null;
		if(curationRequest.getCurationEndTime().isBefore(curationRequest.getCurationStartTime())&& curationRequest.getCurationStartTime().isAfter(
			LocalDateTime.now())){
			status = CurationStatus.INIT;
		} else if (curationRequest.getCurationEndTime().isBefore(curationRequest.getCurationStartTime())
			&& curationRequest.getCurationStartTime().isBefore(
			LocalDateTime.now())) {
			status = CurationStatus.ONAIR;
		}
		else if(curationRequest.getCurationStartTime().isBefore(curationRequest.getCurationEndTime())){
			status = CurationStatus.END;
		}
		Curation curation = Curation.builder()
			.curationStartTime(curationRequest.getCurationStartTime())
			.curationEndTime((curationRequest.getCurationEndTime()))
			.curationName(curationRequest.getCurationName())
			.curationSummary(curationRequest.getCurationSummary())
			.curationStatus(status)
			.artist(curationRequest.getArtist())
			.build();
		curationRepository.save(curation);
	}



	//큐레이션 수정
	@Transactional
	public void updateCuration(long curation_seq, CurationRequest curationRequest){
		CurationStatus status = null;
		if(curationRequest.getCurationEndTime().isBefore(curationRequest.getCurationStartTime())&& curationRequest.getCurationStartTime().isAfter(
			LocalDateTime.now())){
			status = CurationStatus.INIT;
		} else if (curationRequest.getCurationEndTime().isBefore(curationRequest.getCurationStartTime())
			&& curationRequest.getCurationStartTime().isBefore(
			LocalDateTime.now())) {
			status = CurationStatus.ONAIR;
		}
		else if(curationRequest.getCurationStartTime().isBefore(curationRequest.getCurationEndTime())){
			status = CurationStatus.END;
		}
		Curation curation = curationRepository.findById(curation_seq).orElse(null);
		curation.setCurationStartTime(curationRequest.getCurationStartTime());
		curation.setCurationEndTime(curationRequest.getCurationEndTime());
		curation.setCurationName(curationRequest.getCurationName());
		curation.setCurationSummary(curationRequest.getCurationSummary());
		curation.setCurationStatus(status);
		curation.setArtist(curationRequest.getArtist());
		curationRepository.save(curation);
	}

	@Transactional
	//큐레이션 삭제
	public void deleteCuration(long curation_seq){
		curationRepository.deleteById(curation_seq);
	}

}
