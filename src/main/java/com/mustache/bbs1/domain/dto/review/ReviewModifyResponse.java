package com.mustache.bbs1.domain.dto.review;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mustache.bbs1.domain.entity.Review;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ReviewModifyResponse {
	private Long id;

	private String title;

	@LastModifiedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private LocalDateTime lastModifiedAt;

	public static ReviewModifyResponse toResponse(Review modifyReview) {
		return ReviewModifyResponse.builder()
				.id(modifyReview.getId())
				.title(modifyReview.getTitle())
				.lastModifiedAt(modifyReview.getLastModifiedAt())
				.build();
	}
}
