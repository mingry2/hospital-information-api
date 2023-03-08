package com.mustache.bbs1.domain.dto.review;

import com.mustache.bbs1.domain.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
public class ReviewCreateRequest {
    private Long hospitalId;
    private String disease;
    private float amount;
    private String title;
    private String content;

	public Review toEntity() {
		return Review.builder()
				.hospital()
				.build();
	}
}
