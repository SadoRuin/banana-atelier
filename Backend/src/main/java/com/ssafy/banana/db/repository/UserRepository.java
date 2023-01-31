package com.ssafy.banana.db.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.banana.db.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);

	Optional<User> findByEmailAndIsAuthorized(String email, boolean isAuthorized);

}