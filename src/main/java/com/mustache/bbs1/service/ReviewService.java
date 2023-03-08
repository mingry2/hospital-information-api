package com.mustache.bbs1.service;

import com.mustache.bbs1.domain.dto.review.ReviewCreateRequest;
import com.mustache.bbs1.domain.dto.review.ReviewCreateResponse;
import com.mustache.bbs1.domain.dto.review.ReviewResponse;
import com.mustache.bbs1.domain.entity.Review;
import com.mustache.bbs1.domain.entity.User;
import com.mustache.bbs1.exception.AppException;
import com.mustache.bbs1.exception.ErrorCode;
import com.mustache.bbs1.repository.ReviewRepository;
import com.mustache.bbs1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final UserRepository userRepository;

    private final ReviewRepository reviewRepository;

    //리뷰 상세 조회(단건)
    public ReviewResponse get(Long id, String userName) {



        Optional<Review> review = reviewRepository.findById(id);

        return ReviewResponse.from(review);
    }

    //리뷰 등록
    public ReviewCreateResponse create(ReviewCreateRequest reviewCreateRequest) {



        //리뷰 저장
        Review savedReview = reviewRepository.save(reviewCreateRequest.toEntity());
        String message = "리뷰 등록 완료";

        return ReviewCreateResponse.toResponse(message, savedReview);
    }
}
