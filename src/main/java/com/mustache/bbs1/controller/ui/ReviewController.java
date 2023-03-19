package com.mustache.bbs1.controller.ui;

import com.mustache.bbs1.domain.dto.review.ReviewCreateByForm;
import com.mustache.bbs1.domain.dto.review.ReviewModifyRequest;
import com.mustache.bbs1.domain.entity.Hospital;
import com.mustache.bbs1.domain.entity.Review;
import com.mustache.bbs1.domain.entity.User;
import com.mustache.bbs1.exception.AppException;
import com.mustache.bbs1.exception.ErrorCode;
import com.mustache.bbs1.repository.HospitalRepository;
import com.mustache.bbs1.repository.ReviewRepository;
import com.mustache.bbs1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/reviews")
@RequiredArgsConstructor
@Slf4j
public class ReviewController {

	private final UserRepository userRepository;

	private final HospitalRepository hospitalRepository;

	private final ReviewRepository reviewRepository;

	private final BCryptPasswordEncoder encoder;


	//리뷰 등록 페이지
	@GetMapping(value = "/create")
	public String create() {
		return "reviews/create";
	}

	//리뷰 등록
	@PostMapping(value = "/create")
	public String createReview(ReviewCreateByForm reviewCreateByForm, Model model) {
		//병원이 존재하지 않으면 예외 발생
		Hospital hospital = hospitalRepository.findById(reviewCreateByForm.getHospitalId())
				.orElseThrow(() -> new AppException(ErrorCode.HOSPITAL_NOT_FOUND,
						ErrorCode.HOSPITAL_NOT_FOUND.getMessage()));

		//userName이 존재하지 않으면 예외 발생
		User user = userRepository.findById(reviewCreateByForm.getUserId())
				.orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND,
						ErrorCode.USERNAME_NOT_FOUND.getMessage()));

		if (user == null) {
			model.addAttribute("message",
					String.format("%d번 사용자는 존재하지 않습니다.", reviewCreateByForm.getUserId()));
			return "reviews/error";
		}

		if (encoder.matches(reviewCreateByForm.getPassword(), user.getPassword())) {

			Review review = new Review(reviewCreateByForm, hospital, user);
			reviewRepository.save(review);

			return "redirect:/reviews/list";
		}

		return "redirect:/reviews/list";
	}

	//리뷰 수정 페이지
	@GetMapping(value = "/{reviewId}/edit")
	public String editReview(@PathVariable Long reviewId, Model model) {
		Optional<Review> optionalReview = reviewRepository.findById(reviewId);

		if (!optionalReview.isEmpty()) {
			model.addAttribute("review", optionalReview.get());
			return "reviews/edit";

		} else {
			model.addAttribute("message", String.format("%d번 리뷰를 찾을 수 없습니다!", reviewId));
			return "reviews/error";
		}
	}

	//리뷰 수정
	@PostMapping(value = "/{reviewId}/update")
	public String updateReview(@PathVariable Long reviewId,
			ReviewModifyRequest reviewModifyRequest, Model model) {
		//병원이 존재하지 않으면 예외 발생
		Hospital hospital = hospitalRepository.findById(reviewModifyRequest.getHospitalId())
				.orElseThrow(() -> new AppException(ErrorCode.HOSPITAL_NOT_FOUND,
						ErrorCode.HOSPITAL_NOT_FOUND.getMessage()));

		//리뷰가 존재하지 않으면 예외 발생
		Review review = reviewRepository.findById(reviewId)
				.orElseThrow(() -> new AppException(ErrorCode.REVIEW_NOT_FOUND,
						ErrorCode.REVIEW_NOT_FOUND.getMessage()));

		//수정된 리뷰 저장
		review.changeToReview(reviewModifyRequest, hospital);
		Review savedReview = reviewRepository.saveAndFlush(review);

		model.addAttribute("editedReview", savedReview);

		return String.format("redirect:/reviews/%d", review.getId());
	}

	//리뷰 삭제 페이지
	@GetMapping(value = "/{reviewId}/delete")
	public String delete(@PathVariable Long reviewId) {
		reviewRepository.deleteById(reviewId);
		return "redirect:reviews/list";
	}

	//리뷰 조회(단건) 페이지
	@GetMapping(value = "/{reviewId}")
	public String getReview(@PathVariable Long reviewId, Model model) {
		Optional<Review> optionalReview = reviewRepository.findById(reviewId);

		if (!optionalReview.isEmpty()) {
			model.addAttribute("review", optionalReview.get());
			return "reviews/detail";
		} else {
			return "reviews/error";
		}
	}

	//리뷰 전체 조회 페이지
	@GetMapping(value = "/list")
	public String listAll(Model model) {
		List<Review> reviews = reviewRepository.findAll();
		model.addAttribute("listReview", reviews);
		return "reviews/list";
	}

	//리뷰 전체 조회 페이지(기본)
	@GetMapping(value = "")
	public String index() {
		return "redirect:/reviews/list";
	}

}
