package com.mustache.bbs1.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ArticleAddResponse {
    private Long id;
    private String title;
    private String content;

}
