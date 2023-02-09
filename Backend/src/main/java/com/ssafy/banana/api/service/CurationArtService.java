package com.ssafy.banana.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.banana.db.entity.Curation;
import com.ssafy.banana.db.entity.CurationArt;
import com.ssafy.banana.db.repository.CurationArtRepository;
import com.ssafy.banana.db.repository.CurationRepository;
import com.ssafy.banana.dto.response.CurationArtDataResponse;
import com.ssafy.banana.dto.response.CurationDataResponse;

import io.lettuce.core.dynamic.annotation.Param;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CurationArtService {
	@Autowired
	CurationArtRepository curationArtRepository;
	@Autowired
	CurationRepository curationRepository;


	public List<CurationArtDataResponse.CurationArtSimple> getCurationArtList(long curationSeq) {
		System.out.println("11111111111");
		Curation curation = curationRepository.findById(curationSeq).orElse(null);
		System.out.println("여기");
		System.out.println(curation);
		return curationArtRepository.findAllByCuration(curation).stream().map(CurationArtDataResponse.CurationArtSimple::new).collect(
			Collectors.toList());
	}
}
