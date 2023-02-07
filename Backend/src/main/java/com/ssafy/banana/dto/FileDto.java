package com.ssafy.banana.dto;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Accessors(chain = true)
public class FileDto {
	Long userSeq;
	MultipartFile artFile;
	String originalArtName;
	String extension;
	String newArtName;
	File artImg;
	String newThumbnailName;
	File artThumbnail;
}
