package com.mustache.bbs1.domain.entity;

import com.mustache.bbs1.domain.dto.hospital.HospitalResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Hospital {
    @Id
    private Long id;

    private String roadNameAddress; //도로명 주소

    private String hospitalName; //병,의원명

    private Integer patientRoomCount; //병실수

    private Integer totalNumberOfBeds; //병실 내 침대수

    private String businessTypeName; //사업명(의원, 치과의원, 한의원...)

    private Integer businessStatusCode; //사업 상태 코드(영업, 폐업)

    private Float totalAreaSize; //총 면적

    public static HospitalResponse of(Hospital hospital) {
        return new HospitalResponse(hospital.getId(),
                                    hospital.getRoadNameAddress(),
                                    hospital.getHospitalName(),
                                    hospital.getPatientRoomCount(),
                                    hospital.getTotalNumberOfBeds(),
                                    hospital.getBusinessTypeName(),
                                    hospital.getTotalAreaSize());
    }

    public Hospital(Long id, String roadNameAddress, String hospitalName) {
        this.id = id;
        this.roadNameAddress = roadNameAddress;
        this.hospitalName = hospitalName;
    }

}

