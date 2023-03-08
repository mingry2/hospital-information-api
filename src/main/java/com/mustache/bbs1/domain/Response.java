package com.mustache.bbs1.domain;

import com.mustache.bbs1.exception.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Response<T> {
	private String resultCode;
	private T result;

	public static Response<ErrorResponse> error(ErrorResponse errorResponse){
		return new Response<>("ERROR", errorResponse);
	}

	public static <T> Response<T> success(T result){
		return new Response<>("SUCCESS", result);
	}
}
