package com.mustache.bbs1.repository;

import com.mustache.bbs1.domain.entity.Hospital;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
	Optional<Hospital> findById(Long hospitalId);

	Page<Hospital> findByRoadNameAddressContaining(String keyword,
			Pageable pageable); //list에 지역명으로 검색하기 기능 추가

	Page<Hospital> findAll(Pageable pageable); //병원 정보 전체 조회

	List<Hospital> findByBusinessTypeNameIn(List<String> businessTypes);

}
