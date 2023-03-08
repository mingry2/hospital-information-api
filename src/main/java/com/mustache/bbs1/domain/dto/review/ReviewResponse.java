package com.mustache.bbs1.domain.dto.review;

import com.mustache.bbs1.domain.entity.Review;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ReviewResponse {
	private Long id;

	private Long hospitalId;

	private String disease;

	private float amount;

	private String title;

	private String content;

	private Long userId;

	public static ReviewResponse from(Optional<Review> review) {
		return ReviewResponse.builder()
				.id(review.get().getId())
				.hospitalId(review.get().getHospital().getId())
				.disease(review.get().getDisease())
				.amount(review.get().getAmount())

				.build();
	}
}
