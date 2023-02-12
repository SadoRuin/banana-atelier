package com.ssafy.banana.api.service;

import org.springframework.stereotype.Service;

import com.ssafy.banana.db.entity.Artist;
import com.ssafy.banana.db.entity.User;
import com.ssafy.banana.db.entity.enums.Role;
import com.ssafy.banana.db.repository.ArtistRepository;
import com.ssafy.banana.db.repository.UserRepository;
import com.ssafy.banana.dto.UpdateArtistRequest;
import com.ssafy.banana.exception.CustomException;
import com.ssafy.banana.exception.CustomExceptionType;
import com.ssafy.banana.security.jwt.TokenProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ArtistService {

	private final ArtistRepository artistRepository;
	private final UserRepository userRepository;
	private final TokenProvider tokenProvider;

	public Artist checkArtist(Long userSeq) {
		User user = userRepository.findById(userSeq)
			.orElseThrow(() -> new CustomException(CustomExceptionType.USER_NOT_FOUND));
		Artist artist = artistRepository.findById(userSeq).orElse(null);
		if (user.getRole() == Role.ROLE_USER || artist == null) {
			String intro = "안녕하세요. 작가 " + user.getNickname() + "입니다.";
			artist = Artist.builder()
				.user(user)
				.instagramLink("")
				.twitterLink("")
				.youtubeLink("")
				.blogLink("")
				.artistIntro(intro)
				.artistCommissionAvg((double)0)
				.build();
			user.setRole(Role.ROLE_ARTIST);
			userRepository.save(user);
			artistRepository.save(artist);
			return artist;
		}
		return artist;
	}

	public void updateUser(String token, UpdateArtistRequest updateArtistRequest) {
		long id = tokenProvider.getSubject(token);

		Artist artist = artistRepository.findById(id)
			.orElseThrow(() -> new CustomException(CustomExceptionType.ARTIST_NOT_FOUND));

		if (updateArtistRequest.getInstagramLink() != null) {
			artist.setInstagramLink(updateArtistRequest.getInstagramLink());
		}
		if (updateArtistRequest.getTwitterLink() != null) {
			artist.setTwitterLink(updateArtistRequest.getTwitterLink());
		}
		if (updateArtistRequest.getYoutubeLink() != null) {
			artist.setYoutubeLink(artist.getYoutubeLink());
		}
		if (updateArtistRequest.getBlogLink() != null) {
			artist.setBlogLink(updateArtistRequest.getBlogLink());
		}
		if (updateArtistRequest.getArtistIntro() != null) {
			artist.setArtistIntro(updateArtistRequest.getArtistIntro());
		}

		artistRepository.save(artist);
	}
}
