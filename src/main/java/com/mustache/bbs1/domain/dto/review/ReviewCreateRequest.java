package com.mustache.bbs1.domain.dto.review;

import com.mustache.bbs1.domain.entity.Hospital;
import com.mustache.bbs1.domain.entity.Review;
import com.mustache.bbs1.domain.entity.User;
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
    private String disease;
    private float amount;
    private String title;
    private String content;

	public Review toEntity(User user, Hospital hospital) {
		return Review.builder()
				.hospital(hospital)
				.disease(this.disease)
				.amount(this.amount)
				.title(this.title)
				.content(this.content)
				.user(user)
				.build();
	}
}
