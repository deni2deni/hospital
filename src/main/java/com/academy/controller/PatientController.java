package com.academy.controller;

import com.academy.enums.Roles;
import com.academy.enums.UserStatus;
import com.academy.service.BillService;
import com.academy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class PatientController {
    private final UserService userService;
    private final BillService billService;

    @GetMapping(value = "/patient")
    public String showPatientPage(@RequestParam Integer id, Model model) {
        var user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("sum", billService.calculateUserBills(user));
        return "patient";

    }

    @GetMapping(value = "/patients")
    public String showAllPatients(Model model) {
        ;
        model.addAttribute("patients", userService.findAllByRoleId(Roles.PATIENT.getRoleId()));
        model.addAttribute("status", UserStatus.values());
        return "patients";
    }

    @PostMapping(value = "/patients/filter")
    public String showPatientsWithFilter(@RequestParam String filter, Model model) {
        model.addAttribute("status", UserStatus.values());
        model.addAttribute("patients", userService.findAllByRoleIdAndStatus(Roles.PATIENT.getRoleId(), filter));
        return "patients";
    }

    @GetMapping(value = "/addcard")
    public String addPaymentCard(@RequestParam Integer id, Model model) {
        model.addAttribute("userId", id);
        return "addCard";
    }
}
