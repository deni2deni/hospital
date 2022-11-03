package com.academy.service;

import com.academy.model.entity.Bill;
import com.academy.model.entity.User;

public interface BillService {

    void save(Bill bill);

    int calculateUserBills(User user);

    Bill buildBill(Integer sum, User user);
}
