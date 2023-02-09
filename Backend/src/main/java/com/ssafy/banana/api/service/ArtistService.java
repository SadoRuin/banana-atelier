package com.ssafy.banana.api.service;

import org.springframework.stereotype.Service;

import com.ssafy.banana.db.entity.Artist;
import com.ssafy.banana.db.entity.User;
import com.ssafy.banana.db.entity.enums.Role;
import com.ssafy.banana.db.repository.ArtistRepository;
import com.ssafy.banana.db.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArtistService {

	private final ArtistRepository artistRepository;
	private final UserRepository userRepository;

	public Artist checkArtist(Long userSeq) {
		User user = userRepository.findById(userSeq).orElse(null);
		Artist artist = artistRepository.findById(userSeq).orElse(null);
		if (user.getRole() == Role.ROLE_USER || artist == null) {
			user.setRole(Role.ROLE_ARTIST);
			artist.setUser(user);
			userRepository.save(user);
			artistRepository.save(artist);
			return artist;
		}
		return artist;
	}
}
