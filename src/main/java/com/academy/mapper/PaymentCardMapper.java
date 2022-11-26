package com.academy.mapper;

import com.academy.dto.PaymentCardDto;
import com.academy.model.entity.PaymentCard;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentCardMapper {

    PaymentCard toEntity(PaymentCardDto paymentCardDto);
}
