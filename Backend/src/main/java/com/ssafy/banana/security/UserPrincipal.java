package com.ssafy.banana.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.ssafy.banana.db.entity.User;
import com.ssafy.banana.db.entity.enums.Role;

// Authentication 객체에 저장할 수 있는 유일한 타입
public class UserPrincipal implements UserDetails, OAuth2User {
	private Long id;
	private String email;
	private String password;
	private String nickname;
	private String profileImg;
	private Role role;
	private Map<String, Object> attributes;

	// 일반 시큐리티 로그인시 사용
	public UserPrincipal(Long id, String email, String password, String nickname, String profileImg, Role role) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.profileImg = profileImg;
		this.role = role;
	}

	// OAuth2.0 로그인시 사용
	public UserPrincipal(Long id, String email, String password, String nickname, String profileImg, Role role,
		Map<String, Object> attributes) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.profileImg = profileImg;
		this.role = role;
		this.attributes = attributes;
	}

	public static UserPrincipal create(User user) {
		return new UserPrincipal(
			user.getId(),
			user.getEmail(),
			user.getPassword(),
			user.getNickname(),
			user.getProfileImg(),
			user.getRole()
		);
	}

	public String getNickname() {
		return nickname;
	}

	public String getProfileImg() {
		return profileImg;
	}

	public Role getRole() {
		return role;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getUsername() {
		return email;
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
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(() -> String.valueOf(role));
		return authorities;
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
		return id + "";
	}

}