package com.ssafy.banana.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.banana.db.entity.MyArt;

public interface MyArtRepository extends JpaRepository<MyArt, Long> {
}