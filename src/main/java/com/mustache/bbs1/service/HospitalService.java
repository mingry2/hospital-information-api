package com.mustache.bbs1.service;

import com.mustache.bbs1.domain.dto.hospital.HospitalListResponse;
import com.mustache.bbs1.domain.dto.hospital.HospitalResponse;
import com.mustache.bbs1.domain.entity.Hospital;
import com.mustache.bbs1.exception.AppException;
import com.mustache.bbs1.exception.ErrorCode;
import com.mustache.bbs1.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    //병원 정보 상세 조회(단건)
    public HospitalResponse get(Long id) {
        //병원이 존재하지 않으면 예외 발생
        Hospital hospital = hospitalRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.HOSPITAL_NOT_FOUND, ErrorCode.HOSPITAL_NOT_FOUND.getMessage()));

        HospitalResponse hospitalResponse = Hospital.from(hospital);

        if (hospital.getBusinessStatusCode() == 13) { //BusinessStatusCode가 13이라면 "영업중"
            hospitalResponse.setBusinessStatusName("영업중");
        } else if (hospital.getBusinessStatusCode() == 3) { //BusinessStatusCode가 3이라면 "폐업"
            hospitalResponse.setBusinessStatusName("폐업");
        } else {
            hospitalResponse.setBusinessStatusName(String.valueOf(hospital.getBusinessStatusCode()));
        }

        return hospitalResponse;
    }

    //병원 정보 전체 조회
    public Page<HospitalListResponse> list(Pageable pageable) {
        Page<Hospital> hospitals = hospitalRepository.findAll(pageable);

        return hospitals.map(h -> HospitalListResponse.toResponse(h));

    }

}
