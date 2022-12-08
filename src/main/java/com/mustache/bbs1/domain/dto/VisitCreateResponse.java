package com.mustache.bbs1.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@Builder
@NoArgsConstructor
public class VisitCreateResponse {
    private String userName;
    private String hospitalName;
    private String disease;
    private float amount;
}
