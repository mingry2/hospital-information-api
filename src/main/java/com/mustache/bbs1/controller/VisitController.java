package com.mustache.bbs1.controller;

import com.mustache.bbs1.domain.dto.VisitCreateRequest;
import com.mustache.bbs1.domain.dto.VisitCreateResponse;
import com.mustache.bbs1.service.VisitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/visits")
@Slf4j
@RequiredArgsConstructor
public class VisitController {

    private final VisitService visitService;

    @PostMapping("/login")
    public ResponseEntity<String> login(){
        String token = visitService.login();
        return ResponseEntity.ok().body(token);
    }

    @PostMapping
    public ResponseEntity<VisitCreateResponse> createVisitLog(@RequestBody VisitCreateRequest visitCreateRequest, Authentication authentication) {
        log.info("Request, authentication 잘 넘어오는지 : {}", visitCreateRequest, authentication.getName());
        VisitCreateResponse visitCreateResponse = visitService.createVisit(visitCreateRequest, authentication);
        return ResponseEntity.ok().body(visitCreateResponse);
    }

    @GetMapping
    public ResponseEntity<List<VisitCreateResponse>> visitLogList(){
        List<VisitCreateResponse> list = visitService.getAll();
        return ResponseEntity.ok().body(list);
    }

}
