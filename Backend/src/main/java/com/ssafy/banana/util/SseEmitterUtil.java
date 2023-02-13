package com.ssafy.banana.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.ssafy.banana.exception.CustomException;
import com.ssafy.banana.exception.CustomExceptionType;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SseEmitterUtil {
	private static final AtomicLong counter = new AtomicLong();
	private final Map<Long, List<SseEmitter>> sessionsMap = new ConcurrentHashMap<>();

	public SseEmitter add(long curationArtSeq, SseEmitter emitter) {
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

		return emitter;
	}

	public void count(long curationArtSeq) {
		long count = counter.incrementAndGet();
		List<SseEmitter> emitters = sessionsMap.get(curationArtSeq);
		emitters.forEach(emitter -> {
			try {
				emitter.send(SseEmitter.event()
					.name("count")
					.data(count));
			} catch (IOException e) {
				throw new CustomException(CustomExceptionType.RUNTIME_EXCEPTION);
			}
		});
	}
}
