package com.mustache.bbs1.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

	INVALID_PERMISSION(HttpStatus.UNAUTHORIZED, "사용자가 권한이 없습니다."),
	INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "잘못된 토큰입니다."),
	USERNAME_NOT_FOUND(HttpStatus.NOT_FOUND, "Not founded"),
	;

	private final HttpStatus status;
	private final String message;
}
