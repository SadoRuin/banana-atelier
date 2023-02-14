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

	/**
	 * 공지사항 등록
	 * @param noticeRequest 공지사항 정보 (제목, 내용)
	 * @param userSeq 로그인 유저 pk
	 */
	@Transactional
	public void uploadNotice(NoticeRequest noticeRequest, long userSeq) {

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
	}

	/**
	 * 작가 본인의 공지사항 리스트
	 * @param userSeq 로그인 유저 pk
	 * @return 공지사항 내용 응답 DTO 리스트
	 */
	public List<NoticeResponse> getMyNoticeList(long userSeq) {

		List<NoticeResponse> myNoticeList = noticeRepository.findByUserSeq(userSeq);
		if (!CollectionUtils.isEmpty(myNoticeList)) {
			return myNoticeList;
		} else {
			throw new CustomException(CustomExceptionType.NO_CONTENT);
		}
	}

	/**
	 * 내가 팔로우한 작가들의 공지사항 리스트
	 * @param userSeq 로그인 유저 pk
	 * @return 공지사항 내용 응답 DTO 리스트
	 */
	public List<NoticeResponse> getMyArtistsNoticeList(long userSeq) {

		List<NoticeResponse> myArtistNoticeList = noticeRepository.findMyArtistNotice(userSeq);
		if (!CollectionUtils.isEmpty(myArtistNoticeList)) {
			return myArtistNoticeList;
		} else {
			throw new CustomException(CustomExceptionType.NO_CONTENT);
		}
	}

	/**
	 * 공지사항 상세
	 * @param noticeSeq 공지사항 pk
	 * @return 공지사항 정보
	 */
	public NoticeResponse getNotice(long noticeSeq) {

		Notice notice = noticeRepository.findById(noticeSeq)
			.orElseThrow(() -> new CustomException(CustomExceptionType.NO_CONTENT));

		return new NoticeResponse(notice);
	}

	/**
	 * 공지사항 수정
	 * @param noticeRequest 공지사항 정보 (공지사항 pk, 제목, 내용)
	 * @param userSeq 로그인 유저 pk
	 * @return 공지사항 내용 응답 DTO
	 */
	@Transactional
	public Notice updateNotice(NoticeRequest noticeRequest, long userSeq) {

		Notice notice = noticeRepository.findById(noticeRequest.getNoticeSeq())
			.orElseThrow(() -> new CustomException(CustomExceptionType.RUNTIME_EXCEPTION));

		if (userSeq == notice.getArtist().getId()) {
			notice.setNoticeTitle(noticeRequest.getNoticeTitle());
			notice.setNoticeContent(noticeRequest.getNoticeContent());
			noticeRepository.save(notice);

			return notice;
		} else {
			throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
		}
	}

	/**
	 * 공지사항 삭제
	 * @param noticeSeq 공지사항 pk
	 * @param userSeq 로그인 유저 pk
	 */
	public void deleteNotice(long noticeSeq, long userSeq) {

		Notice notice = noticeRepository.findById(noticeSeq)
			.orElseThrow(() -> new CustomException(CustomExceptionType.RUNTIME_EXCEPTION));

		if (notice.getArtist().getId() != userSeq) {
			throw new CustomException(CustomExceptionType.AUTHORITY_ERROR);
		}
		noticeRepository.deleteById(noticeSeq);
	}
}
