package com.ssafy.banana.util;

import java.io.UnsupportedEncodingException;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.ssafy.banana.exception.CustomException;
import com.ssafy.banana.exception.CustomExceptionType;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmailUtil {
	private final JavaMailSender javaMailSender;
	@Value("${spring.mail.username}")
	private String from;

	public void sendEmail(String to, String sub, String content) {
		MimeMessage message = javaMailSender.createMimeMessage();
		try {
			message.addRecipients(Message.RecipientType.TO, to);
			message.setSubject(sub);
			message.setText(content, "UTF-8", "html");
			message.setFrom(new InternetAddress(from, "바나나공방"));
		} catch (MessagingException e) {
			throw new CustomException(CustomExceptionType.RUNTIME_EXCEPTION);
		} catch (UnsupportedEncodingException e) {
			throw new CustomException(CustomExceptionType.RUNTIME_EXCEPTION);
		}

		javaMailSender.send(message);
	}
}
