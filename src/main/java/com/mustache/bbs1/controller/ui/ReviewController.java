package com.mustache.bbs1.controller.ui;

import com.mustache.bbs1.domain.dto.review.ReviewCreateRequest;
import com.mustache.bbs1.domain.entity.Review;
import com.mustache.bbs1.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/reviews")
@RequiredArgsConstructor
@Slf4j
public class ReviewController {
    private final ReviewRepository reviewRepository;

    //리뷰 작성 페이지
    @GetMapping(value = "/new")
    public String createReview() {
        return "reviews/new";
    }

//    //User가 작성한 리뷰 데이터 가져오기
//    @PostMapping("")
//    public String create(ReviewCreateRequest reviewCreateRequest) {
//        Review savedReview = reviewRepository.save(reviewCreateRequest.toEntity());
//
//        return String.format("redirect:/reviews/%d", savedReview.getId());
//    }

    //리뷰 조회(단건) 페이지
    @GetMapping("/{id}")
    public String selectSingle(@PathVariable Long id, Model model) {
        Optional<Review> optArticle = reviewRepository.findById(id);

        // Optional 사용하여 null 값의 예외처리를 해줌
        if (!optArticle.isEmpty()){
            model.addAttribute("article", optArticle.get());
            return "reviews/show";
        }else {
            return "reviews/error";
        }
    }

    //리뷰 전체 조회 페이지
    @GetMapping("/list")
    public String listAll(Model model){
        List<Review> reviews = reviewRepository.findAll();
        model.addAttribute("articles", reviews);
        return "reviews/list";
    }

    //리뷰 전체 조회 페이지(기본)
    @GetMapping("")
    public String index() {
        return "redirect:/reviews/list";
    }

    //리뷰 수정 페이지
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Optional<Review> optionalArticle = reviewRepository.findById(id);

        if (!optionalArticle.isEmpty()){
            model.addAttribute("article", optionalArticle.get());
            return "reviews/edit";
        }else {
            model.addAttribute("message", String.format("%d가 없습니다.", id));
            return "reviews/error";
        }
    }

    //리뷰 삭제 페이지
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id){
        reviewRepository.deleteById(id);
        return "redirect:/articles";
    }

//    @PostMapping("/{id}/update")
//    public String update(@PathVariable Long id, ArticleDto articleDto, Model model) {
//        log.info("title:{} content:{}", articleDto.getTitle(), articleDto.getContent());
//        Review review = reviewRepository.save(articleDto.toEntity());
//        model.addAttribute("article", review);
//        return String.format("redirect:/reviews/%d", review.getId());
//    }
}
