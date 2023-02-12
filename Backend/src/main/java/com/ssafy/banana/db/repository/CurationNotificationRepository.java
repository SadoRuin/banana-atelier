package com.ssafy.banana.db.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.banana.db.entity.CurationNotification;
import com.ssafy.banana.db.entity.User;

public interface CurationNotificationRepository extends JpaRepository<CurationNotification, Long> {
	List<CurationNotification> findAllByUserAndNotificationIsRead(User user, boolean isRead, Sort sort);
}