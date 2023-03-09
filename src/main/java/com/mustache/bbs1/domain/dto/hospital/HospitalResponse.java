package com.mustache.bbs1.domain.dto.hospital;

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
public class HospitalResponse {

    private Long id;
    private String roadNameAddress; //도로명 주소
    private String hospitalName; //병,의원명
    private Integer patientRoomCount; //병실수
    private Integer totalNumberOfBeds; //병실 내 침대수
    private String businessTypeName; //사업명(의원, 치과의원, 한의원...)
    private Float totalAreaSize; //총 면적

    public HospitalResponse(Long id, String roadNameAddress, String hospitalName, Integer patientRoomCount, Integer totalNumberOfBeds, String businessTypeName, Float totalAreaSize) {
        this.id = id;
        this.roadNameAddress = roadNameAddress;
        this.hospitalName = hospitalName;
        this.patientRoomCount = patientRoomCount;
        this.totalNumberOfBeds = totalNumberOfBeds;
        this.businessTypeName = businessTypeName;
        this.totalAreaSize = totalAreaSize;
    }

    private String businessStatusName;

    public void setBusinessStatusName(String businessStatusName) {
        this.businessStatusName = businessStatusName;
    }
}
