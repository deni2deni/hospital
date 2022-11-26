package com.academy.service.impl;

import com.academy.dto.PaymentCardDto;
import com.academy.dto.UserDto;
import com.academy.exception.PaymentCardExistException;
import com.academy.mapper.PaymentCardMapper;
import com.academy.model.repository.PaymentCardRepository;
import com.academy.model.repository.UserRepository;
import com.academy.service.PaymentCardService;
import com.academy.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentCardServiceImpl implements PaymentCardService {

    private final UserRepository userRepository;
    private final PaymentCardMapper paymentCardMapper;
    private final SecurityUtil securityUtil;
    private final PaymentCardRepository paymentCardRepository;

    @Override
    public boolean hasNoCard(UserDto userDto) {
        return userRepository.getReferenceById(userDto.getId()).getCards().isEmpty();
    }

    @Override
    public void save(PaymentCardDto paymentCardDto) {
        if (paymentCardRepository.existsByNumber(paymentCardDto.getNumber())) {
            throw new PaymentCardExistException();
        }
        var paymentCard = paymentCardMapper.toEntity(paymentCardDto);
        paymentCard.setUser(userRepository.findByUsername(securityUtil.getUsername()));
        paymentCardRepository.save(paymentCard);
    }
}
