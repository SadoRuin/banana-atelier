package com.ssafy.banana.dto.request;

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
public class ArtRequest {
	private Long artSeq;
	private String artImg;
	private String artName;
	private String artDescription;
	private Long artCategorySeq;
	private Long userSeq;
}
