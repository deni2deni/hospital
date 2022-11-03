package com.academy.service.impl;

import com.academy.enums.BillStatus;
import com.academy.model.entity.Bill;
import com.academy.model.entity.User;
import com.academy.model.repository.BillRepository;
import com.academy.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class BillServiceImpl implements BillService {

    private final BillRepository repository;

    @Override
    public void save(Bill bill) {
        repository.save(bill);
    }

    @Override
    public int calculateUserBills(User user) {
        return user.getBills().stream().mapToInt(Bill::getSum).sum();
    }

    @Override
    public Bill buildBill(Integer sum, User user) {
        return Bill.builder()
                .status(BillStatus.NEW.name())
                .sum(sum)
                .user(user)
                .build();
    }
}
