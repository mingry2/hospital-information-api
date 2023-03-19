package com.mustache.bbs1.controller.rest;

import com.mustache.bbs1.domain.Response;
import com.mustache.bbs1.domain.dto.review.ReviewDeleteResponse;
import com.mustache.bbs1.domain.dto.review.ReviewListResponse;
import com.mustache.bbs1.domain.dto.review.ReviewModifyRequest;
import com.mustache.bbs1.domain.dto.review.ReviewModifyResponse;
import com.mustache.bbs1.domain.dto.review.ReviewCreateRequest;
import com.mustache.bbs1.domain.dto.review.ReviewCreateResponse;
import com.mustache.bbs1.domain.dto.review.ReviewResponse;
import com.mustache.bbs1.service.ReviewService;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/reviews")
@RequiredArgsConstructor
@Slf4j
public class ReviewRestController {

	private final ReviewService reviewService;

	//리뷰 등록
	@PostMapping(value = "/create")
	public ResponseEntity<Response<ReviewCreateResponse>> createReview(
			@RequestBody ReviewCreateRequest reviewCreateRequest, Authentication authentication) {
		ReviewCreateResponse reviewCreateResponse = reviewService.create(reviewCreateRequest,
				authentication.getName());

		return ResponseEntity.created(URI.create("api/v1/reviews" + reviewCreateResponse.getId()))
				.body(Response.success(reviewCreateResponse));
	}

	//리뷰 수정
	@PutMapping(value = "/{reviewId}")
	public ResponseEntity<Response<ReviewModifyResponse>> modifyReview(
			@RequestBody ReviewModifyRequest reviewModifyRequest, @PathVariable Long reviewId,
			Authentication authentication) {
		ReviewModifyResponse reviewModifyResponse = reviewService.modify(reviewModifyRequest,
				reviewId, authentication.getName());

		return ResponseEntity.created(URI.create("api/v1/reviews" + reviewModifyResponse.getId()))
				.body(Response.success(reviewModifyResponse));
	}

	//리뷰 삭제
	@DeleteMapping(value = "/{reviewId}")
	public ResponseEntity<Response<ReviewDeleteResponse>> deleteReview(@PathVariable Long reviewId,
			Authentication authentication) {
		ReviewDeleteResponse reviewDeleteResponse = reviewService.delete(reviewId,
				authentication.getName());

		return ResponseEntity.ok().body(Response.success(reviewDeleteResponse));
	}

	//리뷰 상세 조회(단건)
	@GetMapping(value = "/{reviewId}")
	public ResponseEntity<Response<ReviewResponse>> getReview(@PathVariable Long reviewId) {
		ReviewResponse reviewResponse = reviewService.get(reviewId);

		return ResponseEntity.ok().body(Response.success(reviewResponse));
	}

	//리뷰 전체 조회
	@GetMapping(value = "/list")
	public ResponseEntity<Response<Page<ReviewListResponse>>> listReview(
			@PageableDefault(size = 20) @SortDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<ReviewListResponse> reviewListResponses = reviewService.list(pageable);

		return ResponseEntity.ok().body(Response.success(reviewListResponses));
	}

}
