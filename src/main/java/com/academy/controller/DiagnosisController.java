package com.academy.controller;

import com.academy.dto.JournalCreateDto;
import com.academy.service.DiagnosisService;
import com.academy.service.JournalService;
import com.academy.service.TreatmentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class DiagnosisController {

    private final DiagnosisService diagnosisService;
    private final JournalService journalService;
    private final TreatmentTypeService treatmentTypeService;

    @GetMapping(value = "/diagnosis")
    public String makeDiagnosis(@RequestParam String patientsUsername, Model model) {
        model.addAttribute("journalCreateDto", new JournalCreateDto());
        model.addAttribute("patientsUsername", patientsUsername);
        model.addAttribute("diagnoses", diagnosisService.findAllDiagnosis());
        model.addAttribute("treatments", treatmentTypeService.findAllTreatments());
        return "make_diagnosis";
    }

    @PostMapping(value = "/diagnosis")
    public String saveDiagnosisInJournal(@ModelAttribute JournalCreateDto journalCreateDto, Model model) {
        journalService.saveDiagnosisInJournal(journalCreateDto);
        return "data_saved";
    }
}
