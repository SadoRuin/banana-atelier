package com.ssafy.banana.util;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmailUtil {
	private final JavaMailSender javaMailSender;
	private final RedisUtil redisUtil;

	public void sendEmail(String to, String sub, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(sub);
		message.setText(text);
		javaMailSender.send(message);
	}
}
