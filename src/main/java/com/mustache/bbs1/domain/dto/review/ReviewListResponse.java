package com.mustache.bbs1.domain.dto.review;

import com.mustache.bbs1.domain.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewListResponse {

	private Long id;

	private String hospitalName;

	private String title;

	private String content;

	private String userName;

	public static ReviewListResponse toResponse(Review review) {
		return ReviewListResponse.builder()
				.id(review.getId())
				.hospitalName(review.getHospital().getHospitalName())
				.title(review.getTitle())
				.content(review.getContent())
				.userName(review.getUser().getUserName())
				.build();
	}

}
