package com.ssafy.banana.api.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnails;

import com.ssafy.banana.config.FileConfig;
import com.ssafy.banana.dto.FileDto;
import com.ssafy.banana.exception.CustomException;
import com.ssafy.banana.exception.CustomExceptionType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileService {

	private final FileConfig fileConfig;
	private final static String UNDER_BAR = "_";
	private static final String DOT = ".";

	@Transactional
	public FileDto saveFile(FileDto fileDto, LocalDateTime artRegDate) {

		MultipartFile artFile = fileDto.getArtFile();
		Long userSeq = fileDto.getUserSeq();
		String originalArtName = fileDto.getOriginalArtName();
		String extension = fileDto.getExtension();

		/**
		 * 파일 저장명 : 원래파일명_저장시각_UUID.확장자명
		 */
		StringBuilder newArtname = new StringBuilder()
			.append(originalArtName)
			.append(UNDER_BAR)
			.append(artRegDate.format(DateTimeFormatter.ofPattern("yyyymmddHHmmss")))
			.append(UNDER_BAR)
			.append(UUID.randomUUID())
			.append(DOT)
			.append(extension);
		fileDto.setNewArtName(newArtname.toString());

		/**
		 * 파일 저장 경로 : path/(유저pk) 경로가 없다면 생성
		 */
		File savePath = new File(fileConfig.getArtImgPath(), userSeq.toString());
		if (!savePath.exists()) {
			savePath.mkdirs();
		}
		try {
			File artImg = new File(savePath, newArtname.toString());
			// 파일 저장
			artFile.transferTo(artImg);
			fileDto.setArtImg(artImg);

		} catch (IOException e) {
			throw new CustomException(CustomExceptionType.FILE_UPLOAD_ERROR);
		}
		return fileDto;
	}

	@Transactional
	public FileDto makeAndSaveThumbnail(FileDto fileDto) {

		Long userSeq = fileDto.getUserSeq();
		File artImg = fileDto.getArtImg();
		String newFilename = fileDto.getNewArtName();

		File savePath = new File(fileConfig.getArtThumbnailPath(), userSeq.toString());
		if (!savePath.exists()) {
			savePath.mkdirs();
		}

		try {
			String newThumbnailname = "s_" + newFilename;
			File artThumbnail = new File(savePath, newThumbnailname);
			BufferedImage originImg = ImageIO.read(artImg);

			double ratio = 3;
			int width = (int)(originImg.getWidth() / ratio);
			int height = (int)(originImg.getHeight() / ratio);

			Thumbnails.of(artImg)
				.size(width, height)
				.toFile(artThumbnail);

			fileDto.setNewThumbnailName(newThumbnailname);
			fileDto.setArtThumbnail(artThumbnail);

			return fileDto;

		} catch (IOException e) {
			throw new CustomException(CustomExceptionType.FILE_UPLOAD_ERROR);
		}
	}
}
