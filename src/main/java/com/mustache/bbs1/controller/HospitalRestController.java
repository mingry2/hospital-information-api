package com.mustache.bbs1.controller;

import com.mustache.bbs1.domain.dto.HospitalResponse;
import com.mustache.bbs1.domain.entity.Hospital;
import com.mustache.bbs1.service.HospitalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

// 데이터를 제공하는 것
@RestController
@RequestMapping("/api/v1/hospitals")
public class HospitalRestController {

    private final HospitalService hospitalService;

    public HospitalRestController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    @GetMapping("/{id}")
    // json 형식으로 return 을 하겠다는 것
    public ResponseEntity<HospitalResponse> get(@PathVariable Integer id){ // ResponseEntity DTO 타입
        return ResponseEntity.ok().body(hospitalService.getHospital(id)); // Return은 DTO로
    }
}
