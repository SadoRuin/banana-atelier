package com.ssafy.banana.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.ssafy.banana.security.jwt.JwtAccessDeniedHandler;
import com.ssafy.banana.security.jwt.JwtAuthenticationEntryPoint;
import com.ssafy.banana.security.jwt.JwtSecurityConfig;
import com.ssafy.banana.security.jwt.TokenProvider;
import com.ssafy.banana.util.RedisUtil;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@EnableMethodSecurity(
	securedEnabled = true,
	jsr250Enabled = true
)
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
	private final TokenProvider tokenProvider;
	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
	private final RedisUtil redisUtil;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			// 기본 REST API만 사용
			.httpBasic().disable()
			.cors().and()
			// token을 사용하는 방식이기 때문에 csrf를 disable합니다.
			.csrf().disable()

			.exceptionHandling()
			.authenticationEntryPoint(jwtAuthenticationEntryPoint)
			.accessDeniedHandler(jwtAccessDeniedHandler)

			// 세션을 사용하지 않기 때문에 STATELESS로 설정
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)

			.and()
			.authorizeRequests()
			.antMatchers(
				"/auth/login",
				"/auth/verify",
				"/auth/reissue",
				"/users/signup",
				"/users/verify/**",
				"/users/check/**",
				"/users/find-password",
				"/arts/all",
				"/arts/new",
				"/arts/category/*",
				"/arts/trend",
				"/arts/popular",
				"/curations/main",
				"/curations/details/**",
				"/notices/**",
				"/curation-art/list/**",
				"/auctions/**"
			).permitAll()
			.anyRequest().authenticated()

			.and()
			.apply(new JwtSecurityConfig(tokenProvider, redisUtil));

		return httpSecurity.build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();

		configuration.addAllowedOrigin("http://localhost:3000");
		configuration.addAllowedOrigin("https://i8a108.p.ssafy.io");
		configuration.addAllowedHeader("*");
		configuration.addAllowedMethod("*");
		configuration.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);

		return source;
	}

	@Bean
	public WebSecurityCustomizer configure() {
		return (web) -> web.ignoring().antMatchers(
			"/swagger-ui/**",
			"/swagger-resources/**",
			"/v3/api-docs",
			"/arts/download/**"
		);
	}
}
