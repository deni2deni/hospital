package com.academy.mapper;

import com.academy.dto.TreatmentDto;
import com.academy.dto.TreatmentTypeDto;
import com.academy.model.entity.Treatment;
import com.academy.model.entity.TreatmentType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {TreatmentTypeMapper.class})
public interface TreatmentMapper {
    @Mapping(target = "treatmentTypeDto", source = "treatmentType")
    TreatmentDto toDto(Treatment treatment);
    Treatment toEntity(TreatmentDto treatmentDto);
}
