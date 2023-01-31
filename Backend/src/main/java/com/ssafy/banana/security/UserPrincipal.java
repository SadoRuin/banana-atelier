package com.ssafy.banana.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.ssafy.banana.db.entity.User;

// Authentication 객체에 저장할 수 있는 유일한 타입
public class UserPrincipal implements UserDetails, OAuth2User {
	private User user;
	private Map<String, Object> attributes;

	// 일반 시큐리티 로그인시 사용

	public UserPrincipal(User user) {
		this.user = user;
	}

	// OAuth2.0 로그인시 사용
	public UserPrincipal(User user, Map<String, Object> attributes) {
		this.user = user;
		this.attributes = attributes;
	}

	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		return this.user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collet = new ArrayList<>();
		collet.add(() -> String.valueOf(this.user.getRole()));
		return collet;
	}

	// 리소스 서버로 부터 받는 회원정보
	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	// User의 PrimaryKey
	@Override
	public String getName() {
		return this.user.getId() + "";
	}

}