package com.academy.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.time.Instant;

@Data
public class JournalDto {
    private Integer id;
    private Instant date;
    private String treatmentStatus;
    private UserDto patientDto;
    private UserDto doctorDto;
    private DiagnosisDto diagnosisDto;
    private TreatmentDto treatmentDto;
}
