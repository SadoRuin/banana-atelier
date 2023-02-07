package com.ssafy.banana.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Configuration
@Getter
public class FileConfig {

	@Value("${images.default-profile}")
	private String defaultProfilePath;

	@Value("${images.profile}")
	private String profilePath;

	@Value("${images.art-image}")
	public String artImgPath;

	@Value("${images.art-thumbnail}")
	private String artThumbnailPath;

}
