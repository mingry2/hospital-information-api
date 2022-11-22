package com.mustache.bbs1.domain.dto;

import com.mustache.bbs1.domain.entity.Article;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class ArticleAddRequest {
    private String title;
    private String content;

    public Article toEntity() {
        Article article = Article.builder()
                .title(this.title)
                .content(this.title)
                .build();
        return article;
    }
}
