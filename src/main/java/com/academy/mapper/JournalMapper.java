package com.academy.mapper;

import com.academy.dto.JournalDto;
import com.academy.dto.TreatmentTypeDto;
import com.academy.model.entity.Journal;
import com.academy.model.entity.TreatmentType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {TreatmentMapper.class})
public interface JournalMapper {

    @Mapping(source = "patient",target = "patientDto")
    @Mapping(source = "doctor",target = "doctorDto")
    @Mapping(source = "diagnosis",target = "diagnosisDto")
    @Mapping(source = "treatment",target = "treatmentDto")
    JournalDto toDto(Journal journal);
    Journal toEntity(JournalDto journalDto);
}
