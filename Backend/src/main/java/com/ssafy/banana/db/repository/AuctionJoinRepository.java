package com.ssafy.banana.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ssafy.banana.db.entity.AuctionJoin;
import com.ssafy.banana.db.entity.AuctionJoinId;

@Repository
public interface AuctionJoinRepository extends JpaRepository<AuctionJoin, AuctionJoinId> {

	@Query("select count(aj) "
		+ "from AuctionJoin aj "
		+ "where aj.curationArt.id = :curationArtSeq")
	int countAuctionJoinPeople(Long curationArtSeq);
}
