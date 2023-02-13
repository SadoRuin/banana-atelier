package com.ssafy.banana.db.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssafy.banana.db.entity.Auction;
import com.ssafy.banana.db.entity.enums.AuctionStatus;

public interface AuctionRepository extends JpaRepository<Auction, Long> {

	@Query("select min(a) "
		+ "from Auction a "
		+ "where a.auctionStatus = :auctionStatus "
		+ "and a.id "
		+ "in (select ca.id "
		+ "from CurationArt ca "
		+ "where ca.curation.id = :curationSeq "
		+ "and ca.isAuction <> 0) "
		+ "order by a.id")
	Optional<Auction> findAuctionInfo(
		@Param("curationSeq") Long curationSeq,
		@Param("auctionStatus") AuctionStatus auctionStatus);
}
