package com.mustache.bbs1.controller;

import com.mustache.bbs1.domain.dto.ArticleDto;
import com.mustache.bbs1.domain.entity.Article;
import com.mustache.bbs1.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/articles")
@Slf4j
public class ArticleController {

    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    // 새 글 작성하는 page
    @GetMapping(value = "/new")
    public String createPage() {
        return "articles/new";
    }

    // url에 입력된 값을 가져와 findById 메서드 사용한다 -> id의 값찾기
    @GetMapping("/{id}")
    public String selectSingle(@PathVariable Long id, Model model) {
        Optional<Article> optArticle = articleRepository.findById(id);

        // Optional 사용하여 null 값의 예외처리를 해줌
        if (!optArticle.isEmpty()){
            model.addAttribute("article", optArticle.get());
            return "show";
        }else {
            return "error";
        }
    }

    // 글 수정하는 페이지
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Optional<Article> optionalArticle = articleRepository.findById(id);

        if (!optionalArticle.isEmpty()){
            model.addAttribute("article", optionalArticle.get());
            return "edit";
        }else {
            model.addAttribute("message", String.format("%d가 없습니다.", id));
            return "error";
        }
    }

    // 데이터베이스에 저장된 데이터 모두 테이블로 보는 페이지
    @GetMapping("/list")
    public String listAll(Model model){
        List<Article> articles = articleRepository.findAll();
        model.addAttribute("articles", articles);
        return "list";
    }

    // /articles만 있을 때 다시 list를 불러온다
    @GetMapping("")
    public String index() {
        return "redirect:/articles/list";
    }

    // 삭제하는
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id){
        articleRepository.deleteById(id);
        return "redirect:/articles";
    }

    // User가 작성한 새 글(title, content) 데이터 가져오기
    @PostMapping("")
    public String add(ArticleDto articleDto) {
        log.info(articleDto.toString());
        Article savedArticle = articleRepository.save(articleDto.toEntity());
        log.info("generatedId:{}", savedArticle.getTitle());
        // User가 입력한 id의 값을 가져옴
        return String.format("redirect:/articles/%d", savedArticle.getId());
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id, ArticleDto articleDto, Model model) {
        log.info("title:{} content:{}", articleDto.getTitle(), articleDto.getContent());
        Article article = articleRepository.save(articleDto.toEntity());
        model.addAttribute("article", article);
        return String.format("redirect:/articles/%d", article.getId());
    }
}
