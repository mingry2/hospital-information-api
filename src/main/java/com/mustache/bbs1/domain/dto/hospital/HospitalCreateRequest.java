package com.mustache.bbs1.domain.dto.hospital;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HospitalCreateRequest {

	private Long id;

	private String roadNameAddress;

	private String hospitalName;

}
