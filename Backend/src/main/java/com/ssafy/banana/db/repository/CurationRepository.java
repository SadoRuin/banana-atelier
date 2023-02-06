package com.ssafy.banana.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.banana.db.entity.Curation;
import com.ssafy.banana.dto.response.ArtResponse;
import com.ssafy.banana.dto.response.CurationResponse;

public interface CurationRepository extends JpaRepository<Curation, Long> {
	@Query("select new com.ssafy.banana.dto.response.CurationResponse(c, a) from Curation c join Artist a on c.artist.id = a.id")
	List<CurationResponse> findAllCurations();
}
