package com.academy.mapper;

import com.academy.dto.UserCreateDto;
import com.academy.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserCreateMapper {

    User toEntity(UserCreateDto userCreateDto);
}
