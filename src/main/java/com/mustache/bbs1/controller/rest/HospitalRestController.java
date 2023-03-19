package com.mustache.bbs1.controller.rest;

import com.mustache.bbs1.domain.Response;
import com.mustache.bbs1.domain.dto.hospital.HospitalListResponse;
import com.mustache.bbs1.domain.dto.hospital.HospitalResponse;
import com.mustache.bbs1.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/hospitals")
@RequiredArgsConstructor
public class HospitalRestController {

	private final HospitalService hospitalService;

	//병원 정보 상세 조회(단건)
	@GetMapping(value = "/{hospitalId}")
	public ResponseEntity<Response<HospitalResponse>> getHospital(@PathVariable Long hospitalId) {
		HospitalResponse hospitalResponse = hospitalService.get(hospitalId);

		return ResponseEntity.ok().body(Response.success(hospitalResponse));
	}

	//병원 정보 전체 조회
	@GetMapping(value = "/list")
	public ResponseEntity<Response<Page<HospitalListResponse>>> listHospital(
			@PageableDefault(size = 20) @SortDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<HospitalListResponse> hospitalListResponse = hospitalService.list(pageable);

		return ResponseEntity.ok().body(Response.success(hospitalListResponse));
	}

}
