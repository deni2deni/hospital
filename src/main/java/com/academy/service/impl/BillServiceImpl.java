package com.academy.service.impl;

import com.academy.dto.UserDto;
import com.academy.enums.BillStatus;
import com.academy.model.entity.Bill;
import com.academy.model.entity.User;
import com.academy.model.repository.BillRepository;
import com.academy.model.repository.UserRepository;
import com.academy.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class BillServiceImpl implements BillService {

    private final BillRepository repository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void save(Bill bill) {
        repository.save(bill);
    }

    @Override
    public int calculateUserBills(UserDto userDto) {
        return userRepository.findByUsername(userDto.getUsername()).getBills().stream()
                .filter(bill -> bill.getStatus().equals(BillStatus.NEW.name()))
                .mapToInt(Bill::getSum)
                .sum();
    }

    @Override
    public Bill buildBill(Integer sum, User user) {
        return Bill.builder()
                .status(BillStatus.NEW.name())
                .sum(sum)
                .user(user)
                .build();
    }

    @Override
    @Transactional
    public void payForBills(String username) {
        var user = userRepository.findByUsername(username);
        if (!user.getCards().isEmpty()){
            var bills = user.getBills()
                    .stream()
                    .filter(bill -> bill.getStatus().equals(BillStatus.NEW.name()))
                    .peek(bill -> bill.setStatus(BillStatus.PAYED.name()))
                    .collect(Collectors.toList());
            user.setBills(bills);
            userRepository.save(user);
        }

    }
}
