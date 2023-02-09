package com.ssafy.banana.db.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.banana.db.entity.Curation;
import com.ssafy.banana.db.entity.CurationArt;

@Repository
public interface CurationArtRepository extends JpaRepository<CurationArt, Long> {
	List<CurationArt> findAllByCuration(Curation curation);
}
