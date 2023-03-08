package com.mustache.bbs1.controller.rest;

import com.mustache.bbs1.domain.Response;
import com.mustache.bbs1.domain.dto.review.ReviewCreateRequest;
import com.mustache.bbs1.domain.dto.review.ReviewCreateResponse;
import com.mustache.bbs1.domain.dto.review.ReviewResponse;
import com.mustache.bbs1.service.ReviewService;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReviewRestController {
    private final ReviewService reviewService;

    //리뷰 등록
    @PostMapping(value = "/hospitals/{hospitalId}/reviews")
    public ResponseEntity<Response<ReviewCreateResponse>> createReview(@RequestBody ReviewCreateRequest reviewCreateRequest, @PathVariable Long hospitalId, @AuthenticationPrincipal UserDetails user) {
        ReviewCreateResponse reviewCreateResponse = reviewService.create(reviewCreateRequest, hospitalId, user.getUsername());

        return ResponseEntity.created(URI.create("api/v1/reviews" + reviewCreateResponse.getId())).body(Response.success(reviewCreateResponse));
    }

//    //리뷰 상세 조회(단건)
//    @GetMapping(value = "/{id}")
//    public ResponseEntity<Response<ReviewResponse>> getReview(@PathVariable Long id, @AuthenticationPrincipal UserDetails user){
//        ReviewResponse reviewResponse = reviewService.get(id, user.getUsername());
//
//        return ResponseEntity.ok().body(Response.success(reviewResponse));
//    }
}
