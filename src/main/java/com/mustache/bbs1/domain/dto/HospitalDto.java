package com.mustache.bbs1.domain.dto;

import com.mustache.bbs1.domain.entity.Hospital;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HospitalDto {

    private Integer id;
    private String roadNameAddress;
    private String hospitalName;

    public Hospital toEntity(){
        return new Hospital(this.id, this.roadNameAddress, this.hospitalName);
    }
}
