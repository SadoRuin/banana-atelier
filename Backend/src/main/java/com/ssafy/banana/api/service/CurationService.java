package com.ssafy.banana.api.service;

import java.time.LocalDateTime;
import java.util.List;


import org.springframework.stereotype.Service;

import com.ssafy.banana.db.entity.Artist;
import com.ssafy.banana.db.entity.Curation;
import com.ssafy.banana.db.entity.enums.CurationStatus;
import com.ssafy.banana.db.repository.CurationRepository;
import com.ssafy.banana.dto.request.CurationRequest;
import com.ssafy.banana.dto.response.CurationResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CurationService {

	CurationRepository curationRepository;

	//큐레이션 전체조회
	public List<CurationResponse> getCurationList() {
		return curationRepository.findAllCurations();
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

	//큐레이션 조회
	public CurationResponse getCuration(long curation_seq){
		Curation curation = curationRepository.findById(curation_seq).orElse(null);
		Artist artist = curation.getArtist();
		return new CurationResponse(curation,artist);
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
