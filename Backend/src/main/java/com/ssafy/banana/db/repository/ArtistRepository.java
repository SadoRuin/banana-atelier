package com.ssafy.banana.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.banana.db.entity.Artist;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

}