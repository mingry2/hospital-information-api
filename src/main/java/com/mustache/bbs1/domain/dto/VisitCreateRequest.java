package com.mustache.bbs1.domain.dto;

import com.mustache.bbs1.domain.entity.Hospital;
import com.mustache.bbs1.domain.entity.User;
import com.mustache.bbs1.domain.entity.Visit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VisitCreateRequest {
    private int hospitalId; // 병원 id
    private String disease; // 병명
    private float amount; // 진료비

    public Visit toEntity(Hospital hospital, User user){
        return Visit.builder()
                .user(user)
                .hospital(hospital)
                .disease(this.disease)
                .amount(this.amount)
                .build();
    }
}
