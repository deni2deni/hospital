package com.academy.controller;

import com.academy.enums.Roles;
import com.academy.model.entity.User;
import com.academy.service.BillService;
import com.academy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PatientController {
    private final UserService userService;
    private final BillService billService;

    @GetMapping(value = "/patient")
    public String showPatientPage(@RequestParam Integer id, Model model) {
        User user = userService.findById(id);
        if (user == null) {
            return "usernotfound";
        }
        int sumOfBills = billService.calculateUserBills(user);
        model.addAttribute("user", user);
        model.addAttribute("sum", sumOfBills);
        return "patient";

    }

    @GetMapping(value = "/patients")
    public String showAllPatients(Model model) {
        List<User> patients = userService.findAllByRoleId(Roles.PATIENT.getRoleId());
        model.addAttribute("patients", patients);
        return "patients";
    }
}
