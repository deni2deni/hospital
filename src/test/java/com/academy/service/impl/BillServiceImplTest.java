package com.academy.service.impl;

import com.academy.dto.UserDto;
import com.academy.enums.BillStatus;
import com.academy.model.entity.Bill;
import com.academy.model.entity.PaymentCard;
import com.academy.model.entity.User;
import com.academy.model.repository.UserRepository;
import com.academy.service.BillService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RequiredArgsConstructor
public class BillServiceImplTest {

    @MockBean
    private UserRepository userRepository;
    @Autowired
    private BillService billService;
    private UserDto userDto;
    private User user;

    @BeforeEach
    void setUp() {
        userDto = new UserDto();
        userDto.setName("test");
        Bill bill = new Bill();
        bill.setSum(100);
        bill.setStatus(BillStatus.NEW.name());
        PaymentCard paymentCard = new PaymentCard();
        user = User.builder()
                .bills(Collections.singletonList(bill))
                .cards(Collections.singletonList(paymentCard))
                .name("testName")
                .build();

        Mockito.when(userRepository.findByUsername(userDto.getUsername())).thenReturn(user);
        Mockito.when(userRepository.save(user)).thenReturn(null);
    }

    @Test
    public void calculateUserBillsTest() {
        assertEquals(100, billService.calculateUserBills(userDto));
    }

    @Test
    public void buildBillTest() {
        var testBill = billService.buildBill(100, user);
        assertEquals("testName", testBill.getUser().getName());
    }

    @Test
    public void payForBillsTest() {
        billService.payForBills(userDto.getUsername());
        assertEquals(BillStatus.PAYED.name(), user.getBills().get(0).getStatus());
    }
}