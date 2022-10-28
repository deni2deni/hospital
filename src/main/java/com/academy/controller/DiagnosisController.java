package com.academy.controller;

import com.academy.service.DiagnosisService;
import com.academy.service.JournalService;
import com.academy.service.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DiagnosisController {

    private final DiagnosisService diagnosisService;
    private final JournalService journalService;
    private final TreatmentService treatmentService;

    @Autowired
    public DiagnosisController(DiagnosisService diagnosisService, JournalService journalService, TreatmentService treatmentService) {
        this.diagnosisService = diagnosisService;
        this.journalService = journalService;
        this.treatmentService = treatmentService;
    }

    @GetMapping(value = "/diagnosis")
    public String makeDiagnosis(@RequestParam Integer id, Model model) {
        var diagnoses = diagnosisService.findAllDiagnosis();
        var treatments = treatmentService.findAllTreatments();
        model.addAttribute("patientId", id);
        model.addAttribute("diagnoses", diagnoses);
        model.addAttribute("treatments", treatments);
        return "make_diagnosis";
    }

    @PostMapping(value = "/diagnosis")
    public String saveDiagnosisInJournal(@RequestParam Integer diagnosisId, @RequestParam Integer patientId,@RequestParam Integer treatmentId, Model model) {
        var newJournal = journalService.buildJournal(patientId, 9, diagnosisId, treatmentId); //TODO need to change doctorId!!!
        journalService.save(newJournal);
        return "diagnosis_saved";
    }
}
