package com.academy.service;

import com.academy.dto.PaymentCardDto;
import com.academy.dto.UserDto;

public interface PaymentCardService {

    boolean hasNoCard(UserDto userDto);

    void save(PaymentCardDto paymentCardDto);
}
