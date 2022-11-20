package com.academy.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class JournalDto {
    private Integer id;
    private Timestamp date;
    private String treatmentStatus;
    private UserDto patientDto;
    private UserDto doctorDto;
    private DiagnosisDto diagnosisDto;
    private TreatmentDto treatmentDto;
}
