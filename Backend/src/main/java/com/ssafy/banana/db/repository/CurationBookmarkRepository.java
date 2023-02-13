package com.ssafy.banana.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssafy.banana.db.entity.CurationBookmark;

public interface CurationBookmarkRepository extends JpaRepository<CurationBookmark, Long> {

	@Query("select cb "
		+ "from CurationBookmark cb "
		+ "where cb.curation.id = :curationSeq and cb.user.id = :userSeq")
	CurationBookmark findCurationBookmark(@Param("curationSeq") Long curationSeq, @Param("userSeq") Long userSeq);

	@Query("select count(cb.curation.id) "
		+ "from CurationBookmark cb "
		+ "where cb.curation.id = :curationSeq")
	int countCurationBookmark(@Param("curationSeq") Long curationSeq);

	List<CurationBookmark> findAllByUser_Id(Long userId);
}
