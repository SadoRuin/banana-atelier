package com.ssafy.banana.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.banana.db.entity.Artist;
import com.ssafy.banana.db.entity.MyArtist;
import com.ssafy.banana.db.entity.MyArtistId;
import com.ssafy.banana.db.entity.User;

public interface MyArtistRepository extends JpaRepository<MyArtist, MyArtistId> {
	List<MyArtist> findAllByUser_Id(Long userId);

	List<MyArtist> findAllByArtist(Artist artist);

	List<MyArtist> findAllByUser(User user);
}
