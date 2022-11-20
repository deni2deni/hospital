package com.academy.controller;

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

    @GetMapping(value = "/procedure")
    public String doProcedure(@RequestParam Integer id, Model model) {
        journalService.doProcedure(id, 9); //TODO change doctor id
        return "procedure";
    }

    @GetMapping(value = "/admission")
    public String doAdmission(@RequestParam Integer id){
        journalService.discharge(id, 9, 1); //TODO change doctor id & diagnosis id
        return "data_saved";
    }
}
