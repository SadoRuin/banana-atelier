package com.ssafy.banana.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.banana.db.entity.Auction;

public interface AuctionRepository extends JpaRepository<Auction, Long> {

	Auction findByCurationArt_Id(Long id);
}
