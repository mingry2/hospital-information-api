package com.mustache.bbs1.controller;

import com.mustache.bbs1.domain.dto.ArticleAddRequest;
import com.mustache.bbs1.domain.dto.ArticleAddResponse;
import com.mustache.bbs1.domain.dto.ArticleResponse;
import com.mustache.bbs1.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/articles")
public class ArticleRestController {

    private final ArticleService articleService;

    public ArticleRestController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponse> get(@PathVariable Long id){
        ArticleResponse articleResponse = articleService.getArticleById(id);
        return ResponseEntity.ok().body(articleResponse);
    }

    @PostMapping
    public ResponseEntity<ArticleAddResponse> addArticle(@RequestBody ArticleAddRequest dto) {
        ArticleAddResponse response = articleService.add(dto);
        return ResponseEntity.ok().body(response);
    }
}
