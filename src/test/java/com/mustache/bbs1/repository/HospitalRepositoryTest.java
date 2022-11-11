package com.mustache.bbs1.repository;

import com.mustache.bbs1.domain.entity.Hospital;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HospitalRepositoryTest {

    @Autowired
    HospitalRepository hospitalRepository;

    @Test
    void get(){
        Optional<Hospital> optionalHospital = hospitalRepository.findById(1);
        Hospital hp = optionalHospital.get();
        System.out.println(hp.getId());
        assertEquals(1, hp.getId());
    }

}