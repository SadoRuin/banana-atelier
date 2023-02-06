package com.ssafy.banana.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssafy.banana.db.entity.MyArt;

public interface MyArtRepository extends JpaRepository<MyArt, Long> {

	@Query("select ma "
		+ "from MyArt ma "
		+ "where ma.art.id = :artSeq and ma.user.id = :userSeq")
	MyArt findMyArt(@Param("artSeq") Long artSeq, @Param("userSeq") Long userSeq);

	@Query("select count(ma.art.id) "
		+ "from MyArt ma "
		+ "where ma.art.id = :artSeq")
	int countArtLike(@Param("artSeq") Long artSeq);
}
