package com.academy.mapper;

import com.academy.dto.DiagnosisDto;
import com.academy.model.entity.Diagnosis;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DiagnosisMapper {
    DiagnosisDto toDto(Diagnosis diagnosis);
    Diagnosis toEntity(DiagnosisDto diagnosisDto);
}
