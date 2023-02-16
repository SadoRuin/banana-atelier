package com.ssafy.banana.db.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.banana.db.entity.Notice;
import com.ssafy.banana.dto.response.NoticeResponse;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {

	// @Query("select new com.ssafy.banana.dto.response.NoticeResponse(n) "
	// 	+ "from Notice n "
	// 	+ "where n.artist.id = :userSeq")
	// List<NoticeResponse> findByUserSeq(@Param("userSeq") Long userSeq);
	Optional<List<Notice>> findAllByArtist_IdOrderByIdDesc(long userSeq);

	@Query("select new com.ssafy.banana.dto.response.NoticeResponse(n) "
		+ "from Notice n "
		+ "where n.artist.id "
		+ "in (select ma.artist.id "
		+ "from MyArtist ma "
		+ "where ma.user.id = :userSeq )"
		+ "order by n.id desc ")
	List<NoticeResponse> findMyArtistNotice(@Param("userSeq") Long userSeq);
}
