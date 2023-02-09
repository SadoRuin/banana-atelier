package com.ssafy.banana.api.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.ssafy.banana.exception.CustomException;
import com.ssafy.banana.exception.CustomExceptionType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AwsS3Service {

	@Value("${cloud.aws.s3.bucket}")
	private String bucket;
	@Value("${cloud.aws.s3.folder.default-profile}")
	private String defaultProfileFolder;
	@Value("${cloud.aws.s3.folder.profile}")
	private String profileFolder;
	@Value("${cloud.aws.s3.folder.art-image}")
	private String artImageFolder;
	@Value("${cloud.aws.s3.folder.art-thumbnail}")
	private String artThumbnailFolder;
	private static final String EXTENSION_JPG = ".jpg";
	private static final String EXTENSION_PNG = ".png";
	private static final String EXTENSION_JPEG = ".jpeg";

	private final AmazonS3 amazonS3;

	public String uploadProfileImage(long userSeq, MultipartFile multipartFile) {
		return uploadImage(profileFolder + userSeq + "/", multipartFile);
	}

	public String uploadImage(String folderName, MultipartFile multipartFile) {
		String fileName = createFileName(multipartFile.getOriginalFilename());
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentLength(multipartFile.getSize());
		objectMetadata.setContentType(multipartFile.getContentType());

		try (InputStream inputStream = multipartFile.getInputStream()) {
			amazonS3.putObject(new PutObjectRequest(bucket, folderName + fileName, inputStream, objectMetadata)
				.withCannedAcl(CannedAccessControlList.PublicRead));
		} catch (IOException e) {
			throw new CustomException(CustomExceptionType.FILE_UPLOAD_ERROR);
		}

		return fileName;
	}

	public void deleteImage(String fileName) {
		amazonS3.deleteObject(new DeleteObjectRequest(bucket, fileName));
	}

	private String createFileName(String fileName) {
		return UUID.randomUUID().toString().concat(getFileExtension(fileName));
	}

	private String getFileExtension(String fileName) {
		try {
			String extension = fileName.substring(fileName.lastIndexOf("."));
			System.out.println("extension = " + extension);
			if (!extension.equalsIgnoreCase(EXTENSION_JPG)
				&& !extension.equalsIgnoreCase(EXTENSION_JPEG)
				&& !extension.equalsIgnoreCase(EXTENSION_PNG)) {
				throw new CustomException(CustomExceptionType.FILE_EXTENSION_ERROR);
			}
			return extension;
		} catch (StringIndexOutOfBoundsException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 형식의 파일(" + fileName + ") 입니다.");
		}
	}
}