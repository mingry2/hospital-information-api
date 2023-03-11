package com.mustache.bbs1.domain.dto.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ReviewDeleteResponse {

	private Long id;

	private String msg;

	public static ReviewDeleteResponse toResponse(Long id, String message) {
		return ReviewDeleteResponse.builder()
				.id(id)
				.msg(message)
				.build();
	}
}
