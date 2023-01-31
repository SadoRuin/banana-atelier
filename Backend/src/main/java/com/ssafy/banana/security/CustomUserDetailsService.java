package com.ssafy.banana.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.banana.db.repository.UserRepository;

@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	private final UserRepository userRepository;

	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(final String email) {
		return userRepository.findByEmailAndIsAuthorized(email, true)
			.map(user -> new UserPrincipal(user))
			.orElseThrow(() -> new UsernameNotFoundException(email + " -> 이메일 인증이 필요하거나 존재하지 않는 사용자입니다."));
	}

}