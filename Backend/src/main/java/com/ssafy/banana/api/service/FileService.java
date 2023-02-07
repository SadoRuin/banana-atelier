package com.ssafy.banana.api.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnails;

import com.ssafy.banana.exception.CustomException;
import com.ssafy.banana.exception.CustomExceptionType;

@Service
public class FileService {

	@Value("${images.art-image}")
	private String artImgRealPath;

	private final static String UNDER_BAR = "_";

	// 파일 이름 만들기 ( UUID + 날짜 + 이름 )
	// 파일 저장 ( yml 경로 )
	// 파일 경로 저장 ( DB )

	@Transactional
	public String saveFile(MultipartFile artFile, Long userSeq, LocalDateTime artRegDate) {

		String originalFilename = artFile.getOriginalFilename();
		// 현재 시각
		SimpleDateFormat date = new SimpleDateFormat("yyyymmddHHmmss");            // 날짜 format
		StringBuilder newFilename = new StringBuilder()
			// .append(userSeq.toString())
			// .append(File.separator)
			.append(UUID.randomUUID().toString())
			.append(UNDER_BAR)
			.append(artRegDate.format(DateTimeFormatter.ofPattern("yyyymmddHHmmss")))
			.append(UNDER_BAR)
			.append(originalFilename);

		// String originalFileExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);    // 파일 확장자
		// if (!originalFileExtension.equalsIgnoreCase(".jpg")
		// 	&& !originalFileExtension.equalsIgnoreCase(".png")
		// 	&& !originalFileExtension.equalsIgnoreCase(".jpeg")) {
		// 	// new "jpg, jpeg, png의 이미지 파일만 업로드해주세요", HttpStatus.FORBIDDEN;
		// }

		// Path filePath = Paths.get(artImgRealPath + File.separator + newFilename.toString());
		File savePath = new File(artImgRealPath, userSeq.toString());
		if (!savePath.exists()) {
			savePath.mkdirs();
		}
		System.out.println(savePath);
		File artImg = new File(savePath, newFilename.toString());
		// Files.write(filePath, artFile.getBytes());
		try {
			artFile.transferTo(artImg);
			makeThumbnail(savePath, artImg, newFilename.toString());
		} catch (IOException e) {
			throw new CustomException(CustomExceptionType.FILE_UPLOAD_ERROR);
		}
		// newFilename = new StringBuilder()
		// 	.append(userSeq)
		// 	.append(File.separator)
		// 	.append(newFilename);
		return newFilename.toString();
	}

	@Transactional
	public File makeThumbnail(File savePath, File artImg, String newFilename) {

		try {
			File thumbnailFile = new File(savePath, "s_" + newFilename);
			BufferedImage originImg = ImageIO.read(artImg);
			double ratio = 3;
			int width = (int)(originImg.getWidth() / ratio);
			int height = (int)(originImg.getHeight() / ratio);

			Thumbnails.of(artImg)
				.size(width, height)
				.toFile(thumbnailFile);

			return thumbnailFile;

		} catch (IOException e) {
			throw new CustomException(CustomExceptionType.FILE_UPLOAD_ERROR);
		}
	}
}
