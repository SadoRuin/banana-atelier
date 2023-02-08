package com.ssafy.banana.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.banana.db.entity.MyArtist;
import com.ssafy.banana.db.entity.MyArtistId;

public interface MyArtistRepository extends JpaRepository<MyArtist, MyArtistId> {
}