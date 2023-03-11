package com.mustache.bbs1.domain.entity;

import com.mustache.bbs1.domain.dto.review.ReviewModifyRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import org.hibernate.annotations.Where;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Where(clause = "deleted_at is NULL")
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital; //리뷰할 병원 id

    private String disease; //질병명

    private float amount; //진료비

    private String title; //리뷰 제목

    private String content; //리뷰 내용

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; //리뷰 작성자

    public void changeToReview(ReviewModifyRequest reviewModifyRequest) {
        this.amount = reviewModifyRequest.getAmount();
        this.disease = reviewModifyRequest.getDisease();
        this.title = reviewModifyRequest.getTitle();
        this.content = reviewModifyRequest.getContent();

    }

}
