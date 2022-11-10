package com.mustache.bbs1.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity // Entity 선언 시 @Id 꼭 필요!
@Getter
@Table(name = "newarticle")
@NoArgsConstructor
@AllArgsConstructor // update 페이지를 위해 id 추가하고 all 추가
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
