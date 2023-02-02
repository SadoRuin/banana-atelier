package com.ssafy.banana.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.banana.db.entity.Curation;

public interface CurationRepository extends JpaRepository<Curation, Long> {

}
