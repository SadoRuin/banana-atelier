package com.ssafy.banana.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmailUtil {
	private final JavaMailSender javaMailSender;
	@Value("${email-verification}")
	private String SERVER_DOMAIN;

	public void sendEmail(String to, String sub, String uuid) {
		SimpleMailMessage message = new SimpleMailMessage();
		String text = SERVER_DOMAIN + "/auth/verify/" + uuid;
		message.setTo(to);
		message.setSubject(sub);
		message.setText(text);
		javaMailSender.send(message);
	}
}
