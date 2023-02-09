package com.ssafy.banana.api.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.ssafy.banana.db.entity.AuctionJoin;
import com.ssafy.banana.db.entity.AuctionJoinId;
import com.ssafy.banana.db.entity.CurationArt;
import com.ssafy.banana.db.entity.User;
import com.ssafy.banana.db.repository.AuctionJoinRepository;
import com.ssafy.banana.db.repository.CurationArtRepository;
import com.ssafy.banana.db.repository.UserRepository;
import com.ssafy.banana.exception.CustomException;
import com.ssafy.banana.exception.CustomExceptionType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuctionService {

	private final UserRepository userRepository;
	private final CurationArtRepository curationArtRepository;
	private final AuctionJoinRepository auctionJoinRepository;

	public AuctionJoin joinAuction(Long curationArtSeq, Long userSeq) {

		User user = userRepository.findById(userSeq)
			.orElseThrow(() -> new CustomException(CustomExceptionType.RUNTIME_EXCEPTION));
		CurationArt curationArt = curationArtRepository.findById(curationArtSeq)
			.orElseThrow(() -> new CustomException(CustomExceptionType.RUNTIME_EXCEPTION));
		LocalDateTime auctionJoinTime = LocalDateTime.now();

		AuctionJoinId auctionJoinId = AuctionJoinId.builder()
			.userSeq(user.getId())
			.curationArtSeq(curationArt.getId())
			.build();
		AuctionJoin auctionJoin = AuctionJoin.builder()
			.id(auctionJoinId)
			.user(user)
			.curationArt(curationArt)
			.auctionJoinTime(auctionJoinTime)
			.build();
		auctionJoinRepository.save(auctionJoin);

		return auctionJoin;
	}
}
