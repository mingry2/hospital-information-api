package com.mustache.bbs1.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "nation_wide_hospitals")
@Getter
@NoArgsConstructor
public class Hospital {
    @Id
    private Integer id;
    private String roadNameAddress;
    private String hospitalName;

    public Hospital(Integer id, String roadNameAddress, String hospitalName) {
        this.id = id;
        this.roadNameAddress = roadNameAddress;
        this.hospitalName = hospitalName;
    }
}

