package com.ssafy.banana.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class FileUtil {

	@Value("${images.default-profile}")
	private String defaultProfilePath;

	@Value("${images.profile}")
	private String profilePath;

	@Value("${images.art-image}")
	public String artImgPath;

	@Value("${images.art-thumbnail}")
	private String artThumbnailPath;

	public static final String EXTENSION_JPG = "jpg";
	public static final String EXTENSION_PNG = "png";
	public static final String EXTENSION_JPEG = "jpeg";

	private FileUtil() {
	}
	
}
