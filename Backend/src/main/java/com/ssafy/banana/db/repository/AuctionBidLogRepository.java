package com.ssafy.banana.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.banana.db.entity.AuctionBidLog;

public interface AuctionBidLogRepository extends JpaRepository<AuctionBidLog, Long> {

	AuctionBidLog findTopByAuction_IdOrderByIdDesc(Long id);
}
