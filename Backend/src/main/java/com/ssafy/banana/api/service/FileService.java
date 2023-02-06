package com.ssafy.banana.api.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
			.append(date.format(artRegDate))
			.append(UNDER_BAR)
			.append(artFile.getOriginalFilename());

		String originalFileExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);    // 파일 확장자
		if (!originalFileExtension.equalsIgnoreCase(".jpg")
			&& !originalFileExtension.equalsIgnoreCase(".png")
			&& !originalFileExtension.equalsIgnoreCase(".jpeg")) {
			// new "jpg, jpeg, png의 이미지 파일만 업로드해주세요", HttpStatus.FORBIDDEN;
		}

		// Path filePath = Paths.get(artImgRealPath + File.separator + newFilename.toString());
		File savePath = new File(artImgRealPath, userSeq.toString());
		if (!savePath.exists()) {
			savePath.mkdirs();
		}
		File artImg = new File(savePath, newFilename.toString());

		// Files.write(filePath, artFile.getBytes());
		try {
			artFile.transferTo(artImg);
		} catch (IOException e) {
			throw new CustomException(CustomExceptionType.FILE_UPLOAD_ERROR);
		}
		return userSeq.toString() + File.separator + newFilename.toString();
	}

	// @Transactional
	// public void makeThumbnail(MultipartFile artFile) {
	//
	// 	Thumbnails.of(new File("original.jpg"))
	// 		.size(160, 160)
	// 		.toFile(new File("thumbnail.jpg"));
	//
	// 	BufferedImage originalImage = null;
	// 	try {
	// 		originalImage = ImageIO.read(new File(artImgRealPath));
	// 		int imgWidth = Math.min(originalImage.getHeight(), originalImage.getWidth());
	// 		int imgHeight = imgWidth;
	// 		BufferedImage scaledImage = Scalr.crop(originalImage, (originalImage.getWidth() - imgWidth) / 2,
	// 			(originalImage.getHeight() - imgHeight) / 2, imgWidth, imgHeight, null);
	// 		int thumbnailWidth = 300, thumbnailHeight = 300;
	// 		BufferedImage resizedImage = Scalr.resize(scaledImage, thumbnailWidth, thumbnailHeight, null);
	// 		String thumbName = artImgRealPath + "THUMB_" + fileName;
	// 		File thumbFile = new File(thumbName);
	// 		ImageIO.write(resizedImage, imageType, thumbFile);
	// 	} catch (IOException e) {
	// 		throw new RuntimeException(e);
	// 	}
	// }
}
