package com.mustache.bbs1.service;

import com.mustache.bbs1.domain.dto.review.ReviewCreateRequest;
import com.mustache.bbs1.domain.dto.review.ReviewCreateResponse;
import com.mustache.bbs1.domain.dto.review.ReviewResponse;
import com.mustache.bbs1.domain.entity.Hospital;
import com.mustache.bbs1.domain.entity.Review;
import com.mustache.bbs1.domain.entity.User;
import com.mustache.bbs1.exception.AppException;
import com.mustache.bbs1.exception.ErrorCode;
import com.mustache.bbs1.repository.HospitalRepository;
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

    private final HospitalRepository hospitalRepository;

    private final ReviewRepository reviewRepository;

//    //리뷰 상세 조회(단건)
//    public ReviewResponse get(Long id, String userName) {
//        Optional<Review> review = reviewRepository.findById(id);
//
//        return ReviewResponse.from(review);
//    }

    //리뷰 등록
    public ReviewCreateResponse create(ReviewCreateRequest reviewCreateRequest, Long hospitalId, String userName) {
        //userName이 존재하지 않으면 예외 발생
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND,
                        ErrorCode.USERNAME_NOT_FOUND.getMessage()));

        //병원이 존재하지 않으면 예외 발생
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new AppException(ErrorCode.HOSPITAL_NOT_FOUND, ErrorCode.HOSPITAL_NOT_FOUND.getMessage()));

        //리뷰 저장
        Review savedReview = reviewRepository.save(reviewCreateRequest.toEntity(user, hospital));
        String message = "리뷰 등록 완료";

        return ReviewCreateResponse.toResponse(message, savedReview);
    }
}
