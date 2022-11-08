package com.mustache.bbs1.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // Entity 선언 시 @Id 꼭 필요!
@NoArgsConstructor
@Getter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id를 직접생성하지 않고 자동으로 생성하도록 하는 기능
    private Long id; // Primary key 를 의미

    private String title;
    private String content;

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
