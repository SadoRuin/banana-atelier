package com.ssafy.banana.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.banana.db.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}