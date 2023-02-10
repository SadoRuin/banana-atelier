package com.ssafy.banana.api.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import net.coobird.thumbnailator.Thumbnails;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.ssafy.banana.db.entity.User;
import com.ssafy.banana.db.repository.UserRepository;
import com.ssafy.banana.dto.DownloladFileDto;
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
	private final UserRepository userRepository;

	public String uploadArtThumbnail(long userSeq, MultipartFile multipartFile) {
		String fileName = "s_" + createFileName(multipartFile.getOriginalFilename());
		String folderName = artThumbnailFolder + userSeq + "/";
		try (InputStream inputStream = multipartFile.getInputStream()) {
			BufferedImage originalImage = ImageIO.read(inputStream);

			double ratio = 3;
			int width = (int)(originalImage.getWidth() / ratio);
			int height = (int)(originalImage.getHeight() / ratio);

			BufferedImage thumbImage = Thumbnails.of(originalImage)
				.size(width, height)
				.outputQuality(1.0f)
				.outputFormat("png")
				.asBufferedImage();

			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(thumbImage, "png", os);
			byte[] buffer = os.toByteArray();
			InputStream is = new ByteArrayInputStream(buffer);

			ObjectMetadata objectMetadata = new ObjectMetadata();
			objectMetadata.setContentLength(buffer.length);
			objectMetadata.setContentType("image/png");

			amazonS3.putObject(new PutObjectRequest(bucket, folderName + fileName, is, objectMetadata)
				.withCannedAcl(CannedAccessControlList.PublicRead));

			return fileName;

		} catch (IOException e) {
			throw new CustomException(CustomExceptionType.FILE_UPLOAD_ERROR);
		}
	}

	public String uploadArtImage(long userSeq, MultipartFile multipartFile) {
		String folderName = artImageFolder + userSeq + "/";
		return uploadImage(folderName, multipartFile);
	}

	public String uploadProfileImage(long userSeq, MultipartFile multipartFile) {
		String folderName = profileFolder + userSeq + "/";
		String fileName = uploadImage(folderName, multipartFile);

		String originalName = userRepository.findById(userSeq)
			.map(User::getProfileImg)
			.orElseThrow(() -> new CustomException(CustomExceptionType.USER_NOT_FOUND));

		deleteImage(folderName, originalName);

		return fileName;
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

	public DownloladFileDto downloadArtImage(long userSeq, String fileName) {
		String folderName = artImageFolder + userSeq + "/";
		return downloadImage(folderName, fileName);
	}

	public DownloladFileDto downloadProfileImage(long userSeq, String fileName) {
		String folderName;
		if (fileName.contains("default_profile")) {
			folderName = defaultProfileFolder;
		} else {
			folderName = profileFolder + userSeq + "/";
		}
		return downloadImage(folderName, fileName);
	}

	public DownloladFileDto downloadImage(String folderName, String fileName) {
		S3Object s3Object = amazonS3.getObject(new GetObjectRequest(bucket, folderName + fileName));
		S3ObjectInputStream objectInputStream = s3Object.getObjectContent();
		try {
			byte[] imageFile = IOUtils.toByteArray(objectInputStream);

			String urlFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8).replaceAll("\\+", "%20");
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			httpHeaders.setContentLength(imageFile.length);
			httpHeaders.setContentDispositionFormData("attachment", urlFileName);

			return DownloladFileDto.builder()
				.httpHeaders(httpHeaders)
				.imageFile(imageFile)
				.build();

		} catch (IOException e) {
			throw new CustomException(CustomExceptionType.FILE_DOWNLOAD_ERROR);
		}
	}

	public void deleteArtThumbnail(long userSeq, String fileName) {
		String folderName = artThumbnailFolder + userSeq + "/";
		amazonS3.deleteObject(new DeleteObjectRequest(bucket, folderName + fileName));
	}

	public void deleteArtImage(long userSeq, String fileName) {
		String folderName = artImageFolder + userSeq + "/";
		amazonS3.deleteObject(new DeleteObjectRequest(bucket, folderName + fileName));
	}

	public void deleteImage(String folderName, String fileName) {
		amazonS3.deleteObject(new DeleteObjectRequest(bucket, folderName + fileName));
	}

	private String createFileName(String fileName) {
		return UUID.randomUUID().toString().concat(getFileExtension(fileName));
	}

	private String getFileExtension(String fileName) {
		try {
			String extension = fileName.substring(fileName.lastIndexOf("."));
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