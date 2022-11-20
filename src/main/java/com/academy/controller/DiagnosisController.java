package com.academy.controller;

import com.academy.service.DiagnosisService;
import com.academy.service.JournalService;
import com.academy.service.TreatmentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class DiagnosisController {

    private final DiagnosisService diagnosisService;
    private final JournalService journalService;
    private final TreatmentTypeService treatmentTypeService;

    @GetMapping(value = "/diagnosis")
    public String makeDiagnosis(@RequestParam Integer id, Model model) {
        model.addAttribute("patientId", id);
        model.addAttribute("diagnoses", diagnosisService.findAllDiagnosis());
        model.addAttribute("treatments", treatmentTypeService.findAllTreatments());
        return "make_diagnosis";
    }

    @PostMapping(value = "/diagnosis")
    public String saveDiagnosisInJournal(@RequestParam Integer diagnosisId, @RequestParam Integer patientId,@RequestParam Integer treatmentId, Model model) {
        journalService.saveDiagnosisInJournal(diagnosisId, patientId, treatmentId); //TODO need to change doctorId!!!
        return "data_saved";
    }
}
