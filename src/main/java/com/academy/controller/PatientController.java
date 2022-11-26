package com.academy.controller;

import com.academy.dto.PaymentCardDto;
import com.academy.enums.Roles;
import com.academy.enums.UserStatus;
import com.academy.service.BillService;
import com.academy.service.JournalService;
import com.academy.service.PaymentCardService;
import com.academy.service.UserService;
import com.academy.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class PatientController {
    private final UserService userService;
    private final BillService billService;
    private final SecurityUtil securityUtil;
    private final PaymentCardService paymentCardService;
    private final JournalService journalService;

    @GetMapping(value = "/patientPage")
    public String showPatientPage(Model model) {
        var user = userService.findByUsername(securityUtil.getUsername());
        model.addAttribute("user", user);
        model.addAttribute("sum", billService.calculateUserBills(user));
        model.addAttribute("hasNoCard", paymentCardService.hasNoCard(user));
        return "patientPage";

    }

    @GetMapping(value = "/addCard")
    public String addPaymentCard(Model model) {
        model.addAttribute("paymentCardDto", new PaymentCardDto());
        return "addCard";
    }

    @PostMapping(value = "/addCard")
    public String savePaymentCard(@ModelAttribute PaymentCardDto paymentCardDto, Model model){
        paymentCardService.save(paymentCardDto);
        return "redirect:/patientPage";
    }

    @GetMapping(value = "/moveToHospital")
    public String moveToHospital(){
        journalService.moveToHospital(securityUtil.getUsername());
        return "redirect:/patientPage";
    }

    @GetMapping(value = "/cancelAdmission")
    public String cancelAdmission(){
        userService.cancelAdmission(securityUtil.getUsername());
        return "redirect:/patientPage";
    }
}
