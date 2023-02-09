package com.ssafy.banana.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import com.ssafy.banana.util.FileUtil;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

	private final FileUtil fileUtil;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/images/default-profile/**")
			.addResourceLocations("file:" + fileUtil.getDefaultProfilePath() + "/**")
			.setCachePeriod(3600)
			.resourceChain(true)
			.addResolver(new PathResourceResolver());
	}
}
