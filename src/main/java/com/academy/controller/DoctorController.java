package com.academy.controller;

import com.academy.dto.JournalCreateDto;
import com.academy.enums.UserStatus;
import com.academy.model.entity.Role;
import com.academy.service.DiagnosisService;
import com.academy.service.JournalService;
import com.academy.service.UserService;
import com.academy.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class DoctorController {
    private final UserService userService;
    private final SecurityUtil securityUtil;
    private final JournalService journalService;
    private final DiagnosisService diagnosisService;

    @GetMapping(value = "/doctorPage")
    public String showDoctorPage(Model model) {
        var user = userService.findByUsername(securityUtil.getUsername());
        var countPatientsWaitingForAdmission = userService.countPatientsWaitingForAdmission();
        model.addAttribute("user", user);
        model.addAttribute("count", countPatientsWaitingForAdmission);
        return "doctorPage";
    }

    @GetMapping(value = "/admissionConfirm")
    public String admissionConfirm(Model model) {
        var patients = userService.findAllByStatus(UserStatus.WAITING_FOR_ADMISSION.name());
        var diagnosis = diagnosisService.findAllDiagnosis();
        model.addAttribute("patients", patients);
        model.addAttribute("diagnosis", diagnosis);
        model.addAttribute("journalCreateDto", new JournalCreateDto());
        return "admissionConfirm";
    }

    @PostMapping(value = "/admissionConfirm")
    public String admissionConfirmProceed(@ModelAttribute JournalCreateDto journalCreateDto) {
        journalService.saveAdmission(journalCreateDto);
        return "redirect:/doctorPage";
    }

    @GetMapping(value = "/patients")
    public String showAllPatients(@RequestParam(required = false) String filter, Model model) {
        if (filter == null) {
            model.addAttribute("patients", userService.findAllByRole(Role.ROLE_USER));
        } else {
            model.addAttribute("patients", userService.findAllByRoleAndStatus(Role.ROLE_USER, filter));
        }
        model.addAttribute("status", UserStatus.values());
        return "patients";
    }
}
