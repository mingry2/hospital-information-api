package com.mustache.bbs1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mustache.bbs1.domain.dto.ArticleAddRequest;
import com.mustache.bbs1.domain.dto.ArticleAddResponse;
import com.mustache.bbs1.domain.dto.ArticleResponse;
import com.mustache.bbs1.service.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;

@WebMvcTest(ArticleRestController.class)
class ArticleRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ArticleService articleService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("해당 id의 글이 조회가 잘 되는지")
    void findSingle() throws Exception {
        ArticleResponse articleResponse = ArticleResponse.builder()
                .id(1L)
                .title("안녕")
                .content("안녕")
                .build();

        given(articleService.getArticleById(1L)).willReturn(articleResponse);
        mockMvc.perform(get("/api/v1/articles/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.content").exists())
                .andDo(print());

        verify(articleService).getArticleById(1L);
    }

    @Test
    @DisplayName("글 등록이 잘 되는지?")
    void add() throws Exception {
//        objectMapper.writerValueAsBytes(new ARticleAddRequest("title1","content1"));
        ArticleAddRequest dto = new ArticleAddRequest("title1","content1");

        given(articleService.add(any())).willReturn(new ArticleAddResponse(1L, dto.getTitle(), dto.getContent()));

        mockMvc.perform(post("/api/v1/articles")
                        .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath(("$.id")).exists())
                .andExpect(jsonPath(("$.title")).exists())
                .andExpect(jsonPath(("$.content")).exists())
                .andDo(print());

        verify(articleService).add(any());
    }
}