package com.ssafy.banana.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.banana.db.entity.Curation;
import com.ssafy.banana.dto.response.CurationAllListResponse;

public interface CurationRepository extends JpaRepository<Curation, Long> {
	@Query(value = "select new com.ssafy.banana.dto.response.CurationAllListResponse(c, a) "
		+ "from Curation c  "
		+ "join Artist a "
		+ "on c.artist.id = a.id ")
	List<CurationAllListResponse> findAllCuration();

}
