package com.mustache.bbs1.service;

import com.mustache.bbs1.domain.dto.VisitCreateRequest;
import com.mustache.bbs1.domain.dto.VisitCreateResponse;
import com.mustache.bbs1.domain.entity.Hospital;
import com.mustache.bbs1.domain.entity.User;
import com.mustache.bbs1.domain.entity.Visit;
import com.mustache.bbs1.repository.HospitalRepository;
import com.mustache.bbs1.repository.UserRepository;
import com.mustache.bbs1.repository.VisitRepository;
import com.mustache.bbs1.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VisitService {

    private final VisitRepository visitRepository;

    private final HospitalRepository hospitalRepository;

    private final UserRepository userRepository;

    @Value("${jwt.token.secret}")
    private String secretKey;

    public VisitCreateResponse createVisit(VisitCreateRequest visitCreateRequest, Authentication authentication){
        Optional<Hospital> hospital = hospitalRepository.findById(visitCreateRequest.getHospitalId());
        User user = userRepository.findByUserName(authentication.getName());
        Visit visit = visitRepository.save(visitCreateRequest.toEntity(hospital.get(), user));
        return VisitCreateResponse.builder()
                .userName(visit.getUser().getUserName())
                .hospitalName(visit.getHospital().getHospitalName())
                .disease(visit.getDisease())
                .amount(visit.getAmount())
                .build();
    }

    // token 생성
    public String login() {
        return JwtUtil.createToken(secretKey);
    }

    public List<VisitCreateResponse> getAll() {
        List<Visit> visitList = visitRepository.findAll();
        List<VisitCreateResponse> visitCreateResponseList = visitList.stream()
                .map(visit -> visit.toResponse()).collect(Collectors.toList());
        return visitCreateResponseList;
    }

}
