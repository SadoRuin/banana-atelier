package com.ssafy.banana.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.banana.db.entity.Curation;
import com.ssafy.banana.db.entity.enums.CurationStatus;

@Repository
public interface CurationRepository extends JpaRepository<Curation, Long> {
	List<Curation> findAllByCurationStatus(CurationStatus curationStatus);

	List<Curation> findAllByArtist_Id(Long artist_id);

	List<Curation> findAllByCurationNameContainingOrCurationSummaryContaining(String curationName,
		String curationSummary);

}
