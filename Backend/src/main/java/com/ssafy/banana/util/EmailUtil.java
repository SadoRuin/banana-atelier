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

	public void sendEmail(String to, String sub, String value) {
		MimeMessage message = javaMailSender.createMimeMessage();
		String text = "";
		text += "<div style='margin:20px;'>";
		text += "<h1> 안녕하세요 바나나공방입니다. </h1>";
		text += "<br>";
		text += "<p>아래 코드를 복사해 입력해주세요<p>";
		text += "<br>";
		text += "<p>감사합니다.<p>";
		text += "<br>";
		text += "<div align='center' style='border:1px solid black; font-family:verdana';>";
		text += "<h3 style='color:blue;'>회원가입 인증 코드입니다.</h3>";
		text += "<div style='font-size:130%'>";
		text += "CODE : <strong>";
		text += value + "</strong><div><br/> ";
		text += "</div>";
		try {
			message.addRecipients(Message.RecipientType.TO, to);
			message.setSubject(sub);
			message.setText(text, "UTF-8", "html");
			message.setFrom(new InternetAddress(from, "바나나공방"));
		} catch (MessagingException e) {
			throw new CustomException(CustomExceptionType.RUNTIME_EXCEPTION);
		} catch (UnsupportedEncodingException e) {
			throw new CustomException(CustomExceptionType.RUNTIME_EXCEPTION);
		}

		javaMailSender.send(message);
	}
}
