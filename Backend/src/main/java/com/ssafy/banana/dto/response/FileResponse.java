package com.ssafy.banana.dto.response;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Accessors(chain = true)
public class FileResponse {
	private HttpHeaders headers;
	private Resource resource;
}
