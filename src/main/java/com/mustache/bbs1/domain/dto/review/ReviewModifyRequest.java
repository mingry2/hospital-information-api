package com.mustache.bbs1.domain.dto.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ReviewModifyRequest {

	private String disease;

	private float amount;

	private String title;

	private String content;

}
