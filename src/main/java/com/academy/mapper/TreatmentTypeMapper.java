package com.academy.mapper;

import com.academy.dto.TreatmentTypeDto;
import com.academy.model.entity.TreatmentType;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TreatmentTypeMapper {
    TreatmentTypeDto toDto(TreatmentType treatmentType);
    TreatmentType toEntity(TreatmentTypeDto treatmentTypeDto);
}
