package com.mustache.bbs1.domain.dto.review;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReviewCreateByForm {

	private Long hospitalId;

	private String disease;

	private float amount;

	private String title;

	private String content;

	private Long userId;

	private String password;

}
