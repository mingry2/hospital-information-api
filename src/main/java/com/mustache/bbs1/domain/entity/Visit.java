package com.mustache.bbs1.domain.entity;

import com.mustache.bbs1.domain.dto.VisitCreateResponse;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Visit extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hospital_id") // foreign key의 컬럼
    private Hospital hospital;

    @ManyToOne
    @JoinColumn(name = "user_id") // foreign key의 컬럼
    private User user;

    private String disease;

    private float amount;

    public VisitCreateResponse toResponse() {
        return VisitCreateResponse.builder()
                .userName(this.user.getUserName())
                .hospitalName(this.hospital.getHospitalName())
                .disease(this.disease)
                .amount(this.amount)
                .build();
    }
}
