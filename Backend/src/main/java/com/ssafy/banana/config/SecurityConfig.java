package com.ssafy.banana.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.cors()
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.csrf()
			.disable()
			.formLogin()
			.disable()
			.httpBasic()
			.disable();
		return http.build();
	}

	@Bean
	public WebSecurityCustomizer configure() {
		return (web) -> web.ignoring().antMatchers(
			"/static/js/**",
			"/static/css/**",
			"/static/img/**",
			"/static/frontend/**"
		);
	}
}
