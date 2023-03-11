package com.mustache.bbs1.domain.dto.hospital;

import com.mustache.bbs1.domain.entity.Hospital;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HospitalCreateRequest {

	private Long id;
	private String roadNameAddress;
	private String hospitalName;


	public Hospital toEntity() {
		return new Hospital(this.id, this.roadNameAddress, this.hospitalName);
	}

}
