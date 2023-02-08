package com.ssafy.banana.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.banana.db.entity.Notice;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {

}
