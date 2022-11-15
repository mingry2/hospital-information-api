package com.mustache.bbs1.repository;

import com.mustache.bbs1.domain.entity.Hospital;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HospitalRepositoryTest {

    @Autowired
    HospitalRepository hospitalRepository;

    // 출력하는 코드 분리
    void printHospitalNameAndAddress(List<Hospital> hospitals) {
        for (var hospital : hospitals) {
            System.out.printf("%s | %s %f\n", hospital.getHospitalName(), hospital.getRoadNameAddress(), hospital.getTotalAreaSize());
        }
        System.out.println(hospitals.size());
    }

    @Test
    @DisplayName("BusinessTypeName이 보건소, 보건지소, 보건진료소인 데이터가 잘 나오는지")
    void findByBusinessTypeNameIn() {
        List<String> inClues = new ArrayList<>();
        inClues.add("보건소");
        inClues.add("보건지소");
        inClues.add("보건진료소");
        List<Hospital> hospitals = hospitalRepository.findByBusinessTypeNameIn(inClues);
        printHospitalNameAndAddress(hospitals);
    }

    @Test
    void containing() {
        // Containing -> %가 없어도 '송파구'가 포함된 것은 모두 찾아준다.
        List<Hospital> hospitals = hospitalRepository.findByRoadNameAddressContaining("송파구");
        printHospitalNameAndAddress(hospitals);
    }

    @Test
    void startsWith() {
        // StartWith -> '경희'로 시작하는
        List<Hospital> hospitals = hospitalRepository.findByHospitalNameStartsWith("경희");
        printHospitalNameAndAddress(hospitals);
    }

    @Test
    void endsWith() {
        // Containing -> %가 없어도 '송파구'가 포함된 것은 모두 찾아준다.
        List<Hospital> hospitals = hospitalRepository.findByHospitalNameEndsWith("병원");
        printHospitalNameAndAddress(hospitals);
    }

    @Test
    void findByPatientRoomCountAndPatientRoomCount() {
        List<Hospital> hospitals = hospitalRepository.findByPatientRoomCountGreaterThanAndPatientRoomCountLessThan(10, 20);
        printHospitalNameAndAddress(hospitals);
    }

    @Test
    void get(){
        Optional<Hospital> optionalHospital = hospitalRepository.findById(1);
        Hospital hp = optionalHospital.get();
        System.out.println(hp.getId());
        assertEquals(1, hp.getId());
    }

}