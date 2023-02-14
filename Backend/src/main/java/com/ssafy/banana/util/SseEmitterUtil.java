package com.ssafy.banana.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.ssafy.banana.dto.request.CountdownRequest;
import com.ssafy.banana.exception.CustomException;
import com.ssafy.banana.exception.CustomExceptionType;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SseEmitterUtil {
	private final Map<Long, List<SseEmitter>> sessionsMap = new ConcurrentHashMap<>();

	public void connectSession(long curationArtSeq, SseEmitter emitter) {
		if (sessionsMap.get(curationArtSeq) == null) {
			sessionsMap.put(curationArtSeq, new CopyOnWriteArrayList<>());
		}
		List<SseEmitter> emitters = sessionsMap.get(curationArtSeq);
		emitters.add(emitter);
		log.info("new emitter added: {}", emitter);
		log.info("emitter list size: {}", emitters.size());
		emitter.onCompletion(() -> {
			log.info("onCompletion callback");
			emitters.remove(emitter);    // 만료되면 리스트에서 삭제
		});
		emitter.onTimeout(() -> {
			log.info("onTimeout callback");
			emitter.complete();
		});
	}

	public void closeAllSession(long curationArtSeq) {
		if (sessionsMap.get(curationArtSeq) == null) {
			throw new CustomException(CustomExceptionType.NO_CONTENT);
		}
		List<SseEmitter> emitters = sessionsMap.get(curationArtSeq);
		for (SseEmitter emitter : emitters) {
			emitter.complete();
		}
		log.info("emitters isEmpty: {}", emitters.isEmpty());
	}

	public void countdown(CountdownRequest countdownRequest) {
		long curationArtSeq = countdownRequest.getCurationArtSeq();
		long second = countdownRequest.getSecond();
		List<SseEmitter> emitters = sessionsMap.get(curationArtSeq);
		emitters.forEach(emitter -> {
			try {
				if (second == 0) {
					emitter.send(SseEmitter.event()
						.name("auctionHost")
						.data("TIME OVER"));
				} else {
					String message = second + "초 남았습니다.";
					emitter.send(SseEmitter.event()
						.name("auctionHost")
						.data(message));
				}
			} catch (IOException e) {
				throw new CustomException(CustomExceptionType.RUNTIME_EXCEPTION);
			}
		});
	}

	public void bidding(long curationArtSeq, String Nickname, int bidPrice) {
		String bidMessage = Nickname + " 님이 " + bidPrice + "원에 입찰하셨습니다.";
		List<SseEmitter> emitters = sessionsMap.get(curationArtSeq);
		emitters.forEach(emitter -> {
			try {
				emitter.send(SseEmitter.event()
					.name("auctionHost")
					.data(bidMessage));
			} catch (IOException e) {
				throw new CustomException(CustomExceptionType.RUNTIME_EXCEPTION);
			}
		});
	}
}
