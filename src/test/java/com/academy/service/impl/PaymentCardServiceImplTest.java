package com.academy.service.impl;

import com.academy.dto.PaymentCardDto;
import com.academy.dto.UserDto;
import com.academy.mapper.PaymentCardMapper;
import com.academy.model.entity.PaymentCard;
import com.academy.model.entity.User;
import com.academy.model.repository.PaymentCardRepository;
import com.academy.model.repository.UserRepository;
import com.academy.service.PaymentCardService;
import com.academy.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RequiredArgsConstructor
class PaymentCardServiceImplTest {

    @Autowired
    private PaymentCardService paymentCardService;
    @MockBean
    private UserRepository userRepository;
    @Autowired
    private PaymentCardMapper paymentCardMapper;
    @MockBean
    private SecurityUtil securityUtil;
    @MockBean
    private PaymentCardRepository paymentCardRepository;
    private UserDto userDto;
    private User user;
    private PaymentCardDto paymentCardDto;


    @BeforeEach
    void setUp() {
        userDto = new UserDto();
        userDto.setId(1);

        user = new User();
        user.setCards(new ArrayList<>());

        paymentCardDto = new PaymentCardDto();

    }

    @Test
    void hasNoCard() {
        Mockito.doReturn(user).when(userRepository).getReferenceById(Mockito.isA(Integer.class));
        var test = paymentCardService.hasNoCard(userDto);
        assertTrue(test);

    }

    @Test
    void save() {
        paymentCardService = Mockito.mock(PaymentCardServiceImpl.class);
        Mockito.doReturn(false).when(paymentCardRepository).existsByNumber(Mockito.isA(String.class));
        Mockito.doReturn("test").when(securityUtil).getUsername();
        Mockito.doReturn(user).when(userRepository).findByUsername(Mockito.isA(String.class));
        Mockito.doReturn(null).when(paymentCardRepository).save(Mockito.isA(PaymentCard.class));
        paymentCardService.save(paymentCardDto);
        Mockito.verify(paymentCardService, Mockito.times(1)).save(Mockito.isA(PaymentCardDto.class));
    }
}