package com.ssafy.banana.api.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.ssafy.banana.db.entity.Artist;
import com.ssafy.banana.db.entity.Notice;
import com.ssafy.banana.db.repository.ArtistRepository;
import com.ssafy.banana.db.repository.NoticeRepository;
import com.ssafy.banana.dto.request.NoticeRequest;
import com.ssafy.banana.dto.response.NoticeResponse;
import com.ssafy.banana.exception.CustomException;
import com.ssafy.banana.exception.CustomExceptionType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeService {

	private final NoticeRepository noticeRepository;
	private final ArtistRepository artistRepository;

	@Transactional
	public Notice uploadNotice(NoticeRequest noticeRequest, Long userSeq) {

		if (noticeRequest.getUserSeq() != userSeq) {
			throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
		}
		Artist artist = artistRepository.findById(userSeq)
			.orElseThrow(() -> new CustomException(CustomExceptionType.RUNTIME_EXCEPTION));

		LocalDateTime artRegDate = LocalDateTime.now();

		Notice notice = Notice.builder()
			.artist(artist)
			.noticeTitle(noticeRequest.getNoticeTitle())
			.noticeContent(noticeRequest.getNoticeContent())
			.noticeTime(artRegDate)
			.build();
		noticeRepository.save(notice);

		return notice;
	}

	public List<NoticeResponse> getMyNoticeList(Long userSeq, Long tokenUserSeq) {

		if (userSeq != tokenUserSeq) {
			throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
		}
		List<NoticeResponse> myNoticeList = noticeRepository.findByUserSeq(userSeq);
		if (!CollectionUtils.isEmpty(myNoticeList)) {
			return myNoticeList;
		} else {
			throw new CustomException(CustomExceptionType.NO_CONTENT);
		}
	}

	public List<NoticeResponse> getMyArtistsNoticeList(Long userSeq, Long tokenUserSeq) {

		if (userSeq != tokenUserSeq) {
			throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
		}
		List<NoticeResponse> myArtistNoticeList = noticeRepository.findMyArtistNotice(userSeq);
		if (!CollectionUtils.isEmpty(myArtistNoticeList)) {
			return myArtistNoticeList;
		} else {
			throw new CustomException(CustomExceptionType.NO_CONTENT);
		}
	}

	public NoticeResponse getNotice(Long noticeSeq) {

		Notice notice = noticeRepository.findById(noticeSeq)
			.orElseThrow(() -> new CustomException(CustomExceptionType.NO_CONTENT));

		return new NoticeResponse(notice);
	}

	@Transactional
	public Notice updateNotice(NoticeRequest noticeRequest, Long userSeq) {

		if (noticeRequest.getUserSeq() != userSeq) {
			throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
		}
		Notice notice = noticeRepository.findById(noticeRequest.getNoticeSeq())
			.orElseThrow(() -> new CustomException(CustomExceptionType.RUNTIME_EXCEPTION));

		if (notice.getArtist().getId() == userSeq) {
			notice.setNoticeTitle(noticeRequest.getNoticeTitle());
			notice.setNoticeContent(noticeRequest.getNoticeContent());
			noticeRepository.save(notice);

			return notice;
		} else {
			throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
		}
	}

	public Long deleteNotice(Long noticeSeq, Long userSeq) {

		Notice notice = noticeRepository.findById(noticeSeq)
			.orElseThrow(() -> new CustomException(CustomExceptionType.RUNTIME_EXCEPTION));

		if (notice.getArtist().getId() != userSeq) {
			throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
		}
		noticeRepository.deleteById(noticeSeq);

		return noticeSeq;
	}

}
