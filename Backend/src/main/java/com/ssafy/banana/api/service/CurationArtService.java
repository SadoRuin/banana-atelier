package com.ssafy.banana.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.banana.db.entity.CurationArt;
import com.ssafy.banana.db.repository.CurationArtRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CurationArtService {
	CurationArtRepository curationArtRepository;

	public List<CurationArt> getCurationArtList(long curation_seq){
		List<CurationArt> curationArtList = null;
		boolean flag = true;
		while(flag){
			CurationArt curationArt = curationArtRepository.findById(curation_seq).orElse(null);
			if(curationArt == null){
				flag = false;
				break;
			}else{
				curationArtList.add(curationArt);
			}
		}
		return curationArtList;
	}
}
