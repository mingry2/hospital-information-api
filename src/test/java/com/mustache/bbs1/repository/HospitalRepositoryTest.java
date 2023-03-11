package com.mustache.bbs1.repository;

import com.mustache.bbs1.domain.entity.Hospital;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class HospitalRepositoryTest {

	@Autowired
	HospitalRepository hospitalRepository;

	// 출력하는 코드 분리
	void printHospitalNameAndAddress(List<Hospital> hospitals) {
		for (var hospital : hospitals) {
			System.out.printf("%s | %s %f\n", hospital.getHospitalName(),
					hospital.getRoadNameAddress(), hospital.getTotalAreaSize());
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

}