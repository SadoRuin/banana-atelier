package com.ssafy.banana.db.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.banana.db.entity.NoticeNotification;
import com.ssafy.banana.db.entity.User;

public interface NoticeNotificationRepository extends JpaRepository<NoticeNotification, Long> {

	List<NoticeNotification> findAllByUserAndNotificationIsRead(User user, boolean isRead, Sort sort);
}