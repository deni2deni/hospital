package com.academy.service;

import com.academy.dto.UserDto;
import com.academy.model.entity.Bill;
import com.academy.model.entity.User;

public interface BillService {

    void save(Bill bill);

    int calculateUserBills(UserDto userDto);

    Bill buildBill(Integer sum, User user);

    void payForBills(String username);
}
