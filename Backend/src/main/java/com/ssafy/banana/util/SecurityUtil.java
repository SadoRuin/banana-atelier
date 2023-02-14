package com.ssafy.banana.util;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.ssafy.banana.security.UserPrincipal;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SecurityUtil {

	public Optional<String> getCurrentUsername() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null) {
			log.debug("Security Context에 인증 정보가 없습니다.");
			return Optional.empty();
		}

		String email = null;
		if (authentication.getPrincipal() instanceof UserPrincipal) {
			UserPrincipal springSecurityUser = (UserPrincipal)authentication.getPrincipal();
			email = springSecurityUser.getUsername();
		} else if (authentication.getPrincipal() instanceof String) {
			email = (String)authentication.getPrincipal();
		}

		return Optional.ofNullable(email);
	}
}