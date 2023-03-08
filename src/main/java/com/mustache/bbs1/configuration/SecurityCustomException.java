package com.mustache.bbs1.configuration;

import com.mustache.bbs1.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SecurityCustomException extends RuntimeException {
	private ErrorCode errorCode;
	private String message;

	@Builder
	public SecurityCustomException(ErrorCode errorCode){
		this.errorCode = errorCode;
	}

	@Override
	public String toString() {
		if (message == null) {
			return errorCode.getMessage();
		}
		return String.format("%s %s", errorCode.getMessage(), message);
	}

}
