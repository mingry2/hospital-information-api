package com.mustache.bbs1.domain.dto.review;

import com.mustache.bbs1.domain.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ReviewCreateResponse {

	private Long id;

	private String msg;

	public static ReviewCreateResponse toResponse(String message, Review savedReview) {
		return ReviewCreateResponse.builder()
				.msg(message)
				.id(savedReview.getId())
				.build();
	}
}
