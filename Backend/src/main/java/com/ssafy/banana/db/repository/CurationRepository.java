package com.ssafy.banana.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.banana.db.entity.Curation;


@Repository
public interface CurationRepository extends JpaRepository<Curation, Long> {


}
