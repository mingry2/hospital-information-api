package com.mustache.bbs1.exception;

import com.mustache.bbs1.domain.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionManager {

	@ExceptionHandler(AppException.class)
	public ResponseEntity<?> appExceptionHandler(AppException e) {
		ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode(), e.getMessage());

		return ResponseEntity.status(e.getErrorCode().getStatus())
				.body(Response.error(errorResponse));
	}

}
