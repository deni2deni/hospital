package com.academy.controller;

import com.academy.enums.TreatmentStatus;
import com.academy.service.BillService;
import com.academy.service.JournalService;
import com.academy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ProcedureController {

    private final JournalService journalService;
    private final UserService userService;
    private final BillService billService;

    @GetMapping(value = "/procedure")
    public String doProcedure(@RequestParam Integer id, Model model) {
        var journal = journalService.findById(id);
        journal.setTreatmentDoctor(userService.findById(9)); //TODO change doctorID & need check doctor/nurse role
        journal.setTreatmentStatus(TreatmentStatus.DONE.name());
        journalService.save(journal);
        var bill = billService.buildBill(journal.getTreatment().getPrice(), journal.getPatient());
        billService.save(bill);
        return "procedure";
    }

    @GetMapping(value = "/admission")
    public String doAdmission(@RequestParam Integer id){
        var patient = userService.findById(id);
        userService.discharge(patient);
        return "data_saved";
    }
}
