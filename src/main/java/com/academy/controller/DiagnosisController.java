package com.academy.controller;

import com.academy.service.DiagnosisService;
import com.academy.service.JournalService;
import com.academy.service.TreatmentService;
import com.academy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final TreatmentService treatmentService;
    private final UserService userService;

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
        var user = newJournal.getPatient();
        user.setFinalDiagnosis(newJournal.getDiagnosis());
        journalService.save(newJournal);
        userService.save(user);
        return "diagnosis_saved";
    }
}
