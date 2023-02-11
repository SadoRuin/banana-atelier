package com.ssafy.banana.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ssafy.banana.db.entity.AuctionJoin;

@Repository
public interface AuctionJoinRepository extends JpaRepository<AuctionJoin, Long> {

	@Query("select count(aj) "
		+ "from AuctionJoin aj "
		+ "where aj.curationArt.id = :curationArtSeq")
	int countAuctionJoinPeople(Long curationArtSeq);
}
