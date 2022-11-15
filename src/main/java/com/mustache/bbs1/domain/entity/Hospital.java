package com.mustache.bbs1.domain.entity;

import com.mustache.bbs1.domain.dto.HospitalResponse;
import lombok.Getter;
import javax.persistence.*;

@Entity
@Table(name = "nation_wide_hospitals")
@Getter
public class Hospital {
    @Id
    private Integer id;

    @Column(name = "road_name_address")
    private String roadNameAddress;

    @Column(name = "hospital_name")
    private String hospitalName;

    private Integer patientRoomCount;
    private Integer totalNumberOfBeds;
    private String businessTypeName;
    private Float totalAreaSize;

    public static HospitalResponse of(Hospital hospital) {
        return new HospitalResponse(hospital.getId(),
                                    hospital.getRoadNameAddress(),
                                    hospital.getHospitalName(),
                                    hospital.getPatientRoomCount(),
                                    hospital.getTotalNumberOfBeds(),
                                    hospital.getBusinessTypeName(),
                                    hospital.getTotalAreaSize());


    }
}

