package com.academy.dto;

import lombok.Data;

@Data
public class TreatmentDto {
    private TreatmentTypeDto treatmentTypeDto;
    private String treatmentStatus;
}
