package com.academy.controller;

import com.academy.dto.DiagnosisDto;
import com.academy.service.DiagnosisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final DiagnosisService diagnosisService;

    @GetMapping(value = "/adminPage")
    public String showAdminPage(Model model) {
        return "adminPage";
    }

    @GetMapping(value = "/showDiagnosis")
    public String showAllDiagnosis(Model model) {
        var diagnosis = diagnosisService.findAllDiagnosis();
        model.addAttribute("diagnosis", diagnosis);
        model.addAttribute("diagnosisDto", new DiagnosisDto());
        return "showDiagnosis";
    }

    @PostMapping(value = "/addDiagnosis")
    public String addNewDiagnosis(@ModelAttribute DiagnosisDto diagnosisDto, Model model){
        diagnosisService.save(diagnosisDto);
        return "redirect:/adminPage";
    }
}