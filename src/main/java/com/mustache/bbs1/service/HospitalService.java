package com.mustache.bbs1.service;

import com.mustache.bbs1.domain.dto.hospital.HospitalResponse;
import com.mustache.bbs1.domain.entity.Hospital;
import com.mustache.bbs1.repository.HospitalRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class HospitalService {
        private final HospitalRepository hospitalRepository;

        public HospitalService(HospitalRepository hospitalRepository) {
            this.hospitalRepository = hospitalRepository;
        }

        // HospitalResponse에 이 로직을 넣을 수도 있습니다.
        public HospitalResponse getHospital(Integer id) {
            Optional<Hospital> optHospital = hospitalRepository.findById(id); // Entity
            Hospital hospital = optHospital.get();
            HospitalResponse hospitalResponse = Hospital.of(hospital); // DTO
            if (hospital.getBusinessStatusCode() == 13) {
                hospitalResponse.setBusinessStatusName("영업중");
            } else if (hospital.getBusinessStatusCode() == 3) {
                hospitalResponse.setBusinessStatusName("폐업");
            } else {
                hospitalResponse.setBusinessStatusName(String.valueOf(hospital.getBusinessStatusCode()));
            }
            return hospitalResponse;
        }
}
