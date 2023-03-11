package com.mustache.bbs1.service;

import com.mustache.bbs1.domain.dto.review.ReviewCreateRequest;
import com.mustache.bbs1.domain.dto.review.ReviewCreateResponse;
import com.mustache.bbs1.domain.dto.review.ReviewDeleteResponse;
import com.mustache.bbs1.domain.dto.review.ReviewListResponse;
import com.mustache.bbs1.domain.dto.review.ReviewModifyRequest;
import com.mustache.bbs1.domain.dto.review.ReviewModifyResponse;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {

	private final UserRepository userRepository;

	private final HospitalRepository hospitalRepository;

	private final ReviewRepository reviewRepository;

	//리뷰 등록
	@Transactional
	public ReviewCreateResponse create(ReviewCreateRequest reviewCreateRequest, Long hospitalId,
			String userName) {
		//userName이 존재하지 않으면 예외 발생
		User user = userRepository.findByUserName(userName)
				.orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND,
						ErrorCode.USERNAME_NOT_FOUND.getMessage()));

		//병원이 존재하지 않으면 예외 발생
		Hospital hospital = hospitalRepository.findById(hospitalId)
				.orElseThrow(() -> new AppException(ErrorCode.HOSPITAL_NOT_FOUND,
						ErrorCode.HOSPITAL_NOT_FOUND.getMessage()));

		//리뷰 저장
		Review savedReview = reviewRepository.save(reviewCreateRequest.toEntity(user, hospital));
		String message = "리뷰 등록 완료";

		return ReviewCreateResponse.toResponse(message, savedReview);
	}

	//리뷰 수정
	@Transactional
	public ReviewModifyResponse modify(ReviewModifyRequest reviewModifyRequest, Long hospitalId,
			Long reviewId, String userName) {
		//userName이 존재하지 않으면 예외 발생
		User user = userRepository.findByUserName(userName)
				.orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND,
						ErrorCode.USERNAME_NOT_FOUND.getMessage()));

		//병원이 존재하지 않으면 예외 발생
		Hospital hospital = hospitalRepository.findById(hospitalId)
				.orElseThrow(() -> new AppException(ErrorCode.HOSPITAL_NOT_FOUND,
						ErrorCode.HOSPITAL_NOT_FOUND.getMessage()));

		//리뷰가 존재하지 않으면 예외 발생
		Review review = reviewRepository.findById(reviewId)
				.orElseThrow(() -> new AppException(ErrorCode.REVIEW_NOT_FOUND,
						ErrorCode.REVIEW_NOT_FOUND.getMessage()));

		//로그인유저 != 일정작성유저일 경우 예외발생
		Long loginUserId = user.getId();
		Long reviewWriteUserId = review.getUser().getId();

		if (!loginUserId.equals(reviewWriteUserId)) {
			throw new AppException(ErrorCode.INVALID_PERMISSION,
					ErrorCode.INVALID_PERMISSION.getMessage());
		}

		//수정된 리뷰 저장
		review.changeToReview(reviewModifyRequest);
		Review modifyReview = reviewRepository.saveAndFlush(review);

		return ReviewModifyResponse.toResponse(modifyReview);

	}

	@Transactional
	public ReviewDeleteResponse delete(Long hospitalId, Long reviewId, String userName) {
		//userName이 존재하지 않으면 예외 발생
		User user = userRepository.findByUserName(userName)
				.orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND,
						ErrorCode.USERNAME_NOT_FOUND.getMessage()));

		//병원이 존재하지 않으면 예외 발생
		Hospital hospital = hospitalRepository.findById(hospitalId)
				.orElseThrow(() -> new AppException(ErrorCode.HOSPITAL_NOT_FOUND,
						ErrorCode.HOSPITAL_NOT_FOUND.getMessage()));

		//리뷰가 존재하지 않으면 예외 발생
		Review review = reviewRepository.findById(reviewId)
				.orElseThrow(() -> new AppException(ErrorCode.REVIEW_NOT_FOUND,
						ErrorCode.REVIEW_NOT_FOUND.getMessage()));

		//로그인유저 != 일정작성유저일 경우 예외발생
		Long loginUserId = user.getId();
		Long reviewWriteUserId = review.getUser().getId();

		if (!loginUserId.equals(reviewWriteUserId)) {
			throw new AppException(ErrorCode.INVALID_PERMISSION,
					ErrorCode.INVALID_PERMISSION.getMessage());
		}

		//리뷰 삭제
		review.deleteSoftly();

		String message = "리뷰가 삭제되었습니다.";

		return ReviewDeleteResponse.toResponse(review.getId(), message);
	}

	//리뷰 상세 조회(단건)
	public ReviewResponse get(Long hospitalId, Long reviewId) {
		//병원이 존재하지 않으면 예외 발생
		Hospital hospital = hospitalRepository.findById(hospitalId)
				.orElseThrow(() -> new AppException(ErrorCode.HOSPITAL_NOT_FOUND,
						ErrorCode.HOSPITAL_NOT_FOUND.getMessage()));

		//리뷰가 존재하지 않으면 예외 발생
		Review review = reviewRepository.findById(reviewId)
				.orElseThrow(() -> new AppException(ErrorCode.REVIEW_NOT_FOUND,
						ErrorCode.REVIEW_NOT_FOUND.getMessage()));

		return ReviewResponse.toResponse(hospital, review);
	}

	//리뷰 전체 조회
	public Page<ReviewListResponse> list(Pageable pageable) {
		Page<Review> reviews = reviewRepository.findAll(pageable);

		return reviews.map(r -> ReviewListResponse.toResponse(r));
	}
}
