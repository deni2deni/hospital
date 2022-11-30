package com.academy.controller;

import com.academy.service.DiagnosisService;
import com.academy.service.JournalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ProcedureController {

    private final JournalService journalService;
    private final DiagnosisService diagnosisService;

    @GetMapping(value = "/procedure")
    public String doProcedure(@RequestParam Integer id, Model model) {
        journalService.doProcedure(id);
        return "redirect:/doctorPage";
    }

    @GetMapping(value = "/discharge")
    public String doDischarge(@RequestParam String patientsUsername, @RequestParam String diagnosisName) {
        journalService.discharge(patientsUsername, diagnosisName);
        return "redirect:/doctorPage";
    }

    @GetMapping(value = "/finalDiagnosis")
    public String selectFinalDiagnosis(@RequestParam String patientsUsername, Model model) {
        var diagnosis = diagnosisService.findAllDiagnosis();
        model.addAttribute("patientsUsername", patientsUsername);
        model.addAttribute("diagnosis", diagnosis);
        return "finalDiagnosis";
    }
}
