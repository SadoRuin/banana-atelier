package com.ssafy.banana.db.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssafy.banana.db.entity.MyArt;
import com.ssafy.banana.db.entity.MyArtId;

public interface MyArtRepository extends JpaRepository<MyArt, MyArtId> {

	@Query("select ma "
		+ "from MyArt ma "
		+ "where ma.art.id = :artSeq and ma.user.id = :userSeq")
	Optional<MyArt> findMyArt(@Param("artSeq") Long artSeq, @Param("userSeq") Long userSeq);

	@Query("select count(ma.art.id) "
		+ "from MyArt ma "
		+ "where ma.art.id = :artSeq")
	int countArtLike(@Param("artSeq") Long artSeq);
}
