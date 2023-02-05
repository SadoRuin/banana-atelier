package com.ssafy.banana.util;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RedisUtil {

	private final RedisTemplate redisTemplate;

	public String getData(String key) { // key를 통해 value(데이터)를 얻는다.
		ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
		return valueOperations.get(key);
	}

	public void setDataExpire(String key, String value, long milliseconds) {
		//  duration 동안 (key, value)를 저장한다.
		ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
		valueOperations.set(key, value, milliseconds, TimeUnit.MILLISECONDS);
	}

	public void setDate(String key, String value) {
		ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
		valueOperations.set(key, value);
	}

	public void deleteData(String key) {
		// 데이터 삭제
		redisTemplate.delete(key);
	}
}