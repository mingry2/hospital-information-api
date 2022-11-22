package com.mustache.bbs1.service;

import com.mustache.bbs1.domain.dto.ArticleAddRequest;
import com.mustache.bbs1.domain.dto.ArticleAddResponse;
import com.mustache.bbs1.domain.dto.ArticleResponse;
import com.mustache.bbs1.domain.entity.Article;
import com.mustache.bbs1.repository.ArticleRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public ArticleResponse getArticleById(Long id) {
        Optional<Article> optArticle = articleRepository.findById(id); // Entity
        Article article = optArticle.get();
        ArticleResponse articleResponse = Article.of(article); // DTO

        return articleResponse;
    }

    public ArticleAddResponse add(ArticleAddRequest dto) {
        Article article = dto.toEntity();
        Article savedArticle = articleRepository.save(article);
        return new ArticleAddResponse(savedArticle.getId(), savedArticle.getTitle(), savedArticle.getContent());
    }
}
