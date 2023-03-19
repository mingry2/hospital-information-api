package com.mustache.bbs1.domain.dto.review;

import com.mustache.bbs1.domain.entity.Hospital;
import com.mustache.bbs1.domain.entity.Review;
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

	private String hospitalName;

	private String disease;

	private float amount;

	private String title;

	private String content;

	private String userName;

	public static ReviewResponse toResponse(Review review) {
		return ReviewResponse.builder()
				.id(review.getId())
				.hospitalName(review.getHospital().getHospitalName())
				.disease(review.getDisease())
				.amount(review.getAmount())
				.title(review.getTitle())
				.content(review.getContent())
				.userName(review.getUser().getUserName())
				.build();
	}

}
