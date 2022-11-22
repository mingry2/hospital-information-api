package com.mustache.bbs1.service;

import com.mustache.bbs1.domain.dto.HospitalResponse;
import com.mustache.bbs1.domain.entity.Hospital;
import com.mustache.bbs1.repository.HospitalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class HospitalServiceTest {

    private HospitalRepository hospitalRepository = Mockito.mock(HospitalRepository.class);

    private HospitalService hospitalService;

    @BeforeEach
    void setUp(){
        hospitalService = new HospitalService(hospitalRepository);
    }

    @Test
    @DisplayName("13일때 영업중, 3일때 폐업")
    void businessStatusName3Or13() {
        Hospital hospital3 = Hospital.builder()
                .id(718457)
                .businessStatusCode(3)
                .build();
        Hospital hospital13 = Hospital.builder()
                .id(4)
                .businessStatusCode(13)
                .build();

        Mockito.when(hospitalRepository.findById(any()))
                .thenReturn(Optional.of(hospital3));
        HospitalResponse closeHospitalResponse = hospitalService.getHospital(71857);

        assertEquals("폐업", closeHospitalResponse.getBusinessStatusName());

        Mockito.when(hospitalRepository.findById(any()))
                .thenReturn(Optional.of(hospital13));
        HospitalResponse openHospitalResponse = hospitalService.getHospital(4);

        assertEquals("영업중", openHospitalResponse.getBusinessStatusName());
    }
}