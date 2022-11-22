package com.mustache.bbs1.repository;

import com.mustache.bbs1.domain.entity.Hospital;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {

    Page<Hospital> findByRoadNameAddressContaining(String keyword, Pageable pageable); // list에 지역명으로 검색하기 기능 추가

    List<Hospital> findByRoadNameAddressContaining(String keyword);
    List<Hospital> findByBusinessTypeNameIn(List<String> businessTypes);
    List<Hospital> findByHospitalNameStartsWith(String keyword); // 시작
    List<Hospital> findByHospitalNameEndsWith(String keyword); // 끝남

    List<Hospital> findByPatientRoomCountGreaterThanAndPatientRoomCountLessThan(int var1, int var2); // 가독성, 사용성이 떨어짐
    List<Hospital> findByPatientRoomCountBetween(int a, int b); // 위를 대신하여 사용
    List<Hospital> findByPatientRoomCountBetweenOrderByPatientRoomCountDesc(int a, int b); // 정렬

}
