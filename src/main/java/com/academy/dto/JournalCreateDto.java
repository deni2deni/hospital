package com.academy.dto;

import lombok.Data;

@Data
public class JournalCreateDto {
    private String patientsUsername;
    private Integer diagnosisId;
    private Integer treatmentTypeId;
}
