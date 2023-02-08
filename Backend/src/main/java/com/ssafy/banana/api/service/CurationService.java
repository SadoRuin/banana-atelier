package com.ssafy.banana.api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.banana.db.entity.Artist;
import com.ssafy.banana.db.entity.Curation;
import com.ssafy.banana.db.entity.enums.CurationStatus;

import com.ssafy.banana.db.repository.CurationRepository;
import com.ssafy.banana.dto.CurationDto;
import com.ssafy.banana.dto.request.CurationRequest;
import com.ssafy.banana.dto.response.CurationAllListResponse;
import com.ssafy.banana.dto.response.CurationDataResponse;
import com.ssafy.banana.exception.CustomException;
import com.ssafy.banana.exception.CustomExceptionType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CurationService {

	@Autowired
	CurationRepository curationRepository;

	//큐레이션 전체조회
	public List<CurationDataResponse.CurationSimple> getCurationList() {
		return curationRepository.findAll().stream().map(CurationDataResponse.CurationSimple::new).collect(Collectors.toList());
	}

	//큐레이션 등록
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

	//큐레이션 디테일 조회
	public CurationDataResponse.Curation getCuration(long curation_seq){
		Curation curation = curationRepository.findById(curation_seq).orElseThrow(null);
		return new CurationDataResponse.Curation(curation);
	}

	//큐레이션 수정
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

	//큐레이션 삭제
	public void deleteCuration(long curation_seq){
		curationRepository.deleteById(curation_seq);
	}

}
