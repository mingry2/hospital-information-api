package com.mustache.bbs1.domain.dto.hospital;

import com.mustache.bbs1.domain.entity.Hospital;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HospitalListResponse {

	private Long id;

	private String roadNameAddress; //도로명 주소

	private String hospitalName; //병,의원명

	public static HospitalListResponse toResponse(Hospital hospital) {
		return HospitalListResponse.builder()
				.id(hospital.getId())
				.roadNameAddress(hospital.getRoadNameAddress())
				.hospitalName(hospital.getHospitalName())
				.build();

	}
}
