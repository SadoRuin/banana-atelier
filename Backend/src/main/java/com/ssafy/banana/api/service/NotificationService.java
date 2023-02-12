package com.ssafy.banana.api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ssafy.banana.db.entity.Artist;
import com.ssafy.banana.db.entity.Curation;
import com.ssafy.banana.db.entity.CurationNotification;
import com.ssafy.banana.db.entity.MyArtist;
import com.ssafy.banana.db.entity.Notice;
import com.ssafy.banana.db.entity.NoticeNotification;
import com.ssafy.banana.db.entity.User;
import com.ssafy.banana.db.repository.ArtistRepository;
import com.ssafy.banana.db.repository.CurationNotificationRepository;
import com.ssafy.banana.db.repository.CurationRepository;
import com.ssafy.banana.db.repository.MyArtistRepository;
import com.ssafy.banana.db.repository.NoticeNotificationRepository;
import com.ssafy.banana.db.repository.NoticeRepository;
import com.ssafy.banana.db.repository.UserRepository;
import com.ssafy.banana.dto.NotificationDto;
import com.ssafy.banana.dto.enums.NotificationType;
import com.ssafy.banana.dto.request.NotificationReadRequest;
import com.ssafy.banana.dto.request.NotificationRequest;
import com.ssafy.banana.exception.CustomException;
import com.ssafy.banana.exception.CustomExceptionType;
import com.ssafy.banana.security.jwt.TokenProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService {

	private final TokenProvider tokenProvider;
	private final NoticeNotificationRepository noticeNotificationRepository;
	private final CurationNotificationRepository curationNotificationRepository;
	private final MyArtistRepository myArtistRepository;
	private final ArtistRepository artistRepository;
	private final NoticeRepository noticeRepository;
	private final UserRepository userRepository;
	private final CurationRepository curationRepository;

	public List<NotificationDto> getAllNotification(String token) {
		long userSeq = tokenProvider.getSubject(token);
		User user = userRepository.findById(userSeq)
			.orElseThrow(() -> new CustomException(CustomExceptionType.USER_NOT_FOUND));

		List<NotificationDto> noticeNotification = noticeNotificationRepository.findAllByUserAndNotificationIsRead(
				user, false, Sort.by(Sort.Direction.DESC, "notificationTime"))
			.stream().map(notice -> NotificationDto.builder()
				.notificationSeq(notice.getId())
				.notificationContent(notice.getNotificationContent())
				.notificationTime(notice.getNotificationTime())
				.notificationIsRead(notice.isNotificationIsRead())
				.artistSeq(notice.getArtist().getId())
				.notificationType(NotificationType.NOTICE)
				.build()).collect(Collectors.toList());
		List<NotificationDto> curationNotification = curationNotificationRepository.findAllByUserAndNotificationIsRead(
				user, false, Sort.by(Sort.Direction.DESC, "notificationTime"))
			.stream().map(curation -> NotificationDto.builder()
				.notificationSeq(curation.getId())
				.notificationContent(curation.getNotificationContent())
				.notificationTime(curation.getNotificationTime())
				.notificationIsRead(curation.isNotificationIsRead())
				.artistSeq(curation.getArtist().getId())
				.notificationType(NotificationType.CURATION)
				.build()).collect(Collectors.toList());

		List<NotificationDto> list = Stream.concat(noticeNotification.stream(), curationNotification.stream())
			.collect(Collectors.toList());

		if (list.isEmpty())
			throw new CustomException(CustomExceptionType.NO_CONTENT);

		return list;
	}

	public void createNoticeNotification(String token, NotificationRequest notificationRequest) {
		long artistSeq = tokenProvider.getSubject(token);
		long noticeSeq = notificationRequest.getSeq();

		Artist artist = artistRepository.findById(artistSeq)
			.orElseThrow(() -> new CustomException(CustomExceptionType.ARTIST_NOT_FOUND));
		Notice notice = noticeRepository.findById(noticeSeq)
			.orElseThrow(() -> new CustomException(CustomExceptionType.NO_CONTENT));

		List<User> users = myArtistRepository.findAllByArtist(artist)
			.stream().map(MyArtist::getUser).collect(Collectors.toList());

		if (users.isEmpty())
			throw new CustomException(CustomExceptionType.FOLLOWER_NOT_FOUND);

		List<NoticeNotification> list = users.stream().map(user -> NoticeNotification.builder()
			.notificationContent(artist.getUser().getNickname() + " 작가가 새로운 공지를 게시했습니다.")
			.notificationTime(LocalDateTime.now())
			.notificationIsRead(false)
			.user(user)
			.artist(artist)
			.notice(notice)
			.build()).collect(Collectors.toList());

		noticeNotificationRepository.saveAll(list);
	}

	public List<NotificationDto> getNoticeNotifications(String token) {
		long userSeq = tokenProvider.getSubject(token);
		User user = userRepository.findById(userSeq)
			.orElseThrow(() -> new CustomException(CustomExceptionType.USER_NOT_FOUND));

		List<NotificationDto> list = noticeNotificationRepository.findAllByUserAndNotificationIsRead(
				user, false, Sort.by(Sort.Direction.DESC, "notificationTime"))
			.stream().map(notice -> NotificationDto.builder()
				.notificationSeq(notice.getId())
				.notificationContent(notice.getNotificationContent())
				.notificationTime(notice.getNotificationTime())
				.notificationIsRead(notice.isNotificationIsRead())
				.artistSeq(notice.getArtist().getId())
				.notificationType(NotificationType.NOTICE)
				.build()).collect(Collectors.toList());
		if (list.isEmpty())
			throw new CustomException(CustomExceptionType.NO_CONTENT);

		return list;
	}

	public void createCurationNotification(String token, NotificationRequest notificationRequest) {
		long artistSeq = tokenProvider.getSubject(token);
		long curationSeq = notificationRequest.getSeq();

		Artist artist = artistRepository.findById(artistSeq)
			.orElseThrow(() -> new CustomException(CustomExceptionType.ARTIST_NOT_FOUND));
		Curation curation = curationRepository.findById(curationSeq)
			.orElseThrow(() -> new CustomException(CustomExceptionType.NO_CONTENT));

		List<User> users = myArtistRepository.findAllByArtist(artist)
			.stream().map(MyArtist::getUser).collect(Collectors.toList());

		if (users.isEmpty())
			throw new CustomException(CustomExceptionType.FOLLOWER_NOT_FOUND);

		List<CurationNotification> list = users.stream().map(user -> CurationNotification.builder()
			.notificationContent(artist.getUser().getNickname() + " 작가가 새로운 큐레이션을 등록했습니다.")
			.notificationTime(LocalDateTime.now())
			.notificationIsRead(false)
			.user(user)
			.artist(artist)
			.curation(curation)
			.build()).collect(Collectors.toList());

		curationNotificationRepository.saveAll(list);
	}

	public List<NotificationDto> getCurationNotifications(String token) {
		long userSeq = tokenProvider.getSubject(token);
		User user = userRepository.findById(userSeq)
			.orElseThrow(() -> new CustomException(CustomExceptionType.USER_NOT_FOUND));

		List<NotificationDto> list = curationNotificationRepository.findAllByUserAndNotificationIsRead(
				user, false, Sort.by(Sort.Direction.DESC, "notificationTime"))
			.stream().map(notice -> NotificationDto.builder()
				.notificationSeq(notice.getId())
				.notificationContent(notice.getNotificationContent())
				.notificationTime(notice.getNotificationTime())
				.notificationIsRead(notice.isNotificationIsRead())
				.artistSeq(notice.getArtist().getId())
				.notificationType(NotificationType.CURATION)
				.build()).collect(Collectors.toList());
		if (list.isEmpty())
			throw new CustomException(CustomExceptionType.NO_CONTENT);

		return list;
	}

	public void readNotification(String token, NotificationReadRequest notificationReadRequest) {
		long userSeq = tokenProvider.getSubject(token);

		long notificationSeq = notificationReadRequest.getNotificationSeq();
		NotificationType type = notificationReadRequest.getNotificationType();
		if (type == NotificationType.NOTICE) {
			NoticeNotification noticeNotification = noticeNotificationRepository.findById(notificationSeq)
				.orElseThrow(() -> new CustomException(CustomExceptionType.NO_CONTENT));
			if (noticeNotification.getUser().getId() != userSeq) {
				throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
			}
			noticeNotification.setNotificationIsRead(true);
			noticeNotificationRepository.save(noticeNotification);
		} else if (type == NotificationType.CURATION) {
			CurationNotification curationNotification = curationNotificationRepository.findById(notificationSeq)
				.orElseThrow(() -> new CustomException(CustomExceptionType.NO_CONTENT));
			if (curationNotification.getUser().getId() != userSeq) {
				throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
			}
			curationNotification.setNotificationIsRead(true);
			curationNotificationRepository.save(curationNotification);
		}
	}
}
