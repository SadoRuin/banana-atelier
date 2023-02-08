package com.ssafy.banana.exception;

import org.springframework.http.HttpStatus;

public enum CustomExceptionType {
	RUNTIME_EXCEPTION(HttpStatus.BAD_REQUEST, "E001", "잘못된 요청입니다."),
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "E002", "서버 오류 입니다."),
	USER_NOT_FOUND(HttpStatus.NOT_FOUND, "E003", "사용자 정보가 존재하지 않습니다."),
	LOGIN_FAIL(HttpStatus.UNAUTHORIZED, "E004", "이메일 또는 비밀번호를 확인해주세요."),
	ACCESS_TOKEN_ERROR(HttpStatus.UNAUTHORIZED, "E005", "액세스 토큰 오류입니다."),
	REFRESH_TOKEN_ERROR(HttpStatus.UNAUTHORIZED, "E006", "리프레쉬 토큰 오류입니다."),
	EMAIL_CODE_ERROR(HttpStatus.UNAUTHORIZED, "E007", "이메일 인증코드 오류입니다."),
	EXPIRED_AUTH_INFO(HttpStatus.NOT_FOUND, "E008", "인증정보가 만료되었습니다."),
	AUTHORITY_ERROR(HttpStatus.FORBIDDEN, "E009", "해당 기능을 요청할 권한이 없습니다."),
	USER_CONFLICT(HttpStatus.CONFLICT, "E010", "이미 가입된 사용자입니다."),
	NO_CONTENT(HttpStatus.NOT_FOUND, "E011", "데이터가 존재하지 않습니다."),
	DO_NOT_DELETE(HttpStatus.BAD_REQUEST, "E012", "삭제할 수 없습니다."),
	FILE_UPLOAD_ERROR(HttpStatus.BAD_REQUEST, "E013", "파일을 업로드 할 수 없습니다."),
	FILE_DOWNLOAD_ERROR(HttpStatus.CONFLICT, "E014", "파일을 다운로드 할 수 없습니다."),
	FILE_EXTENSION_ERROR(HttpStatus.FORBIDDEN, "E015", "jpg, jpeg, png의 이미지 파일만 업로드해주세요."),
	MAX_UPLOAD_SIZE_EXCEEDED(HttpStatus.BAD_REQUEST, "E016", "한번에 업로드 가능한 용량(10MB)을 초과했습니다."),
	PASSWORD_NOT_MATCHED(HttpStatus.UNAUTHORIZED, "E017", "비밀번호가 일치하지 않습니다."),
	ARTIST_FOLLOW_CONFLICT(HttpStatus.CONFLICT, "E018", "이미 팔로우한 작가입니다.");

	private final HttpStatus httpStatus;
	private final String code;
	private String message;

	CustomExceptionType(HttpStatus httpStatus, String code) {
		this.httpStatus = httpStatus;
		this.code = code;
	}

	CustomExceptionType(HttpStatus httpStatus, String code, String message) {
		this.httpStatus = httpStatus;
		this.code = code;
		this.message = message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
