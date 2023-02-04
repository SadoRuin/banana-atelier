package com.ssafy.banana.api.service;

import org.springframework.stereotype.Service;

import com.ssafy.banana.db.entity.Artist;
import com.ssafy.banana.db.entity.User;
import com.ssafy.banana.db.repository.ArtistRepository;
import com.ssafy.banana.db.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArtistService {

	private final ArtistRepository artistRepository;
	private final UserRepository userRepository;

	public Artist checkArtist(Long userSeq) {
		Artist artist = artistRepository.findById(userSeq).orElse(null);
		if (artist == null) {
			User user = userRepository.findById(userSeq).orElse(null);
			Artist enterArtist = Artist.builder()
				.user(user)
				.build();
			artistRepository.save(enterArtist);
			return enterArtist;
		}
		return artist;
	}
}
