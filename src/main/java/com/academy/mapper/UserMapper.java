package com.academy.mapper;

import com.academy.dto.UserDto;
import com.academy.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    @Mapping(target = "roleDto", source = "role")
    UserDto toDto(User user);
    User toEntity(UserDto userDto);
}
